package me.tt.pms.service.resource.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.tt.pms.core.AdviceException;
import me.tt.pms.core.domain.Menu;
import me.tt.pms.core.domain.constants.ApplicationNames;
import me.tt.pms.core.domain.dto.MenuDto;
import me.tt.pms.core.utils.JsonUtils;
import me.tt.pms.core.utils.ListUtils;
import me.tt.pms.core.utils.StringUtils;
import me.tt.pms.data.MenuMapper;
import me.tt.pms.service.resource.MenuService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单服务实现
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/24 9:51
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    /**
     * 菜单路径分隔符
     */
    private static final String MENU_PATH_SEPARATOR = ",";

    @Resource
    private MenuMapper menuMapper;

    /**
     * 获取指定系统名称的菜单
     * @param systemName 菜单系统名称
     * @return 菜单
     */
    @Override
    public Menu getMenuBySystemName(final String systemName) {
        WeekendSqls<Menu> sqls = WeekendSqls.<Menu>custom()
                .andEqualTo(Menu::getDeleted, false)
                .andEqualTo(Menu::getSystemName, systemName);
        Example example = Example.builder(Menu.class)
                .where(sqls).build();

        return menuMapper.selectOneByExample(example);
    }

    /**
     * 根据系统名称，获取所有子菜单
     * @param systemName 菜单系统名称
     * @return 菜单列表
     */
    @Override
    public List<Menu> getChildMenusBySystemName(String systemName){
        return getChildMenusBySystemName(systemName, false);
    }

    /**
     * 根据系统名称，获取所有子菜单
     * @param systemName 菜单系统名称
     * @param showHidden 显示隐藏的菜单
     * @return 菜单列表
     */
    @Override
    public List<Menu> getChildMenusBySystemName(String systemName, boolean showHidden){
        Menu root = getMenuBySystemName(systemName);
        if(root == null){
            throw new AdviceException("没有找到系统名称为'%s'的菜单", systemName);
        }

        WeekendSqls<Menu> sqls = WeekendSqls.<Menu>custom()
                .andEqualTo(Menu::getDeleted, false)
                .andLike(Menu::getParentPath, generateMenuPath(root) + "%");
        if(!showHidden){
            sqls.andEqualTo(Menu::getEnabled, true);
        }
        Example example = Example.builder(Menu.class)
                .where(sqls)
                .orderByAsc("displayOrder")
                .build();

        List<Menu> menus = menuMapper.selectByExample(example);

        return menus;
    }

    /**
     * 根据用户id,获取菜单树
     * @param userId 用户id
     * @return 菜单树
     */
    @Override
    public List<MenuDto> getMenusTreeByUserId(Long userId){
        return getMenusTreeBySystemNameAndUserId(ApplicationNames.PMS, userId);
    }

    /**
     * 根据系统名称、用户id,获取菜单树
     * @param systemName 系统名称
     * @param userId 用户id
     * @return 菜单树
     */
    @Override
    public List<MenuDto> getMenusTreeBySystemNameAndUserId(String systemName, Long userId) {
        List<Menu> menus = getChildMenusBySystemName(systemName);

        return sortMenusForTree(menus);
    }


    /**
     * 生成菜单路径
     * @param menu 菜单
     * @return 菜单路径
     */
    private String generateMenuPath(Menu menu){
        StringBuilder path = new StringBuilder();

        if(!StringUtils.isNullOrEmpty(menu.getParentPath())){
            path.append(menu.getParentPath());
        }
        path.append(menu.getId());
        path.append(MENU_PATH_SEPARATOR);

        return path.toString();
    }

    /**
     * 用树方式对菜单排序
     * @param menus 菜单列表
     * @return 菜单树列表
     */
    private List<MenuDto> sortMenusForTree(List<Menu> menus){
        List<MenuDto> unprocessedMenus = ListUtils.map(menus, this::assembleMenuDto);
        List<MenuDto> menuTree = new ArrayList<>();
        List<MenuDto> currentMenus;
        List<MenuDto> lastMenus = new ArrayList<>();

        while (unprocessedMenus.size() > 0){
            currentMenus = ListUtils.filter(unprocessedMenus, m1 -> null == m1.getParentId() || !ListUtils.any(unprocessedMenus, m2 -> m1.getParentId().equals(m2.getId())));
            unprocessedMenus.removeIf(m1 -> null == m1.getParentId() || !ListUtils.any(unprocessedMenus, m2 -> m1.getParentId().equals(m2.getId())));

            for (MenuDto m1 : currentMenus) {
                MenuDto pm = ListUtils.find(lastMenus, m2 -> m2.getId().equals(m1.getParentId()));
                if(pm == null){
                    menuTree.add(m1);
                } else {
                    pm.getChilds().add(m1);
                }
            }

            lastMenus = currentMenus;
        }

        return menuTree;
    }


    /**
     * 根据 Entity，组装成 Dto
     * @param menu Entity
     * @return Dto
     */
    private MenuDto assembleMenuDto(Menu menu){
        MenuDto dto = null;
        try {
            String json = JsonUtils.serialize(menu);
            dto = JsonUtils.deserialize(json, MenuDto.class);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return dto;
    }

}