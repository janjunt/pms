package me.tt.pms.service.resource;

import me.tt.pms.core.domain.Menu;
import me.tt.pms.core.domain.dto.MenuDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: MenuService
 * @Description: 菜单服务接口
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/24 9:50
 */
public interface MenuService {

    /**
     * 获取指定系统名称的菜单
     * @param systemName 菜单系统名称
     * @return 菜单
     */
    Menu getMenuBySystemName(String systemName);


    /**
     * 获取指定系统名称的菜单和所有子菜单
     * @param systemName 菜单系统名称
     * @param showHidden 显示隐藏的菜单
     * @return 菜单列表
     */
    List<Menu> getAllMenusBySystemName(String systemName, boolean showHidden);

    /**
     * 获取指定系统名称的菜单和所有子菜单
     * @param systemName 菜单系统名称
     * @return 菜单列表
     */
    List<Menu> getAllMenusBySystemName(String systemName);

    /**
     * 根据用户id,获取菜单树
     * @param userId 用户id
     * @return 菜单树
     */
    List<MenuDto> getMenusTreeByUserId(@NotNull(message = "用户id不能为空") Long userId);

    /**
     * 根据系统名称、用户id,获取菜单树
     * @param systemName 系统名称
     * @param userId 用户id
     * @return 菜单树
     */
    List<MenuDto> getMenusTreeBySystemNameAndUserId(@NotEmpty(message = "系统名称不能为空") String systemName,
                                                    @NotNull(message = "用户id不能为空") Long userId);
}
