package me.tt.pms.service.resource;

import com.github.pagehelper.PageInfo;
import me.tt.pms.core.domain.Menu;
import me.tt.pms.core.domain.dto.MenuAddDto;
import me.tt.pms.core.domain.dto.MenuDto;
import me.tt.pms.core.domain.dto.MenuEditDto;
import me.tt.pms.core.domain.dto.MenuSearchDto;
import me.tt.pms.core.paging.PageSearchParameter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
     * 根据菜单标识符，获取菜单
     * @param id 菜单标识符
     * @return 菜单
     */
    Menu getById(@NotNull(message = "'菜单id'不能为空") Long id);

    /**
     * 获取指定系统名称的菜单
     * @param systemName 菜单系统名称
     * @return 菜单
     */
    Menu getMenuBySystemName(@NotBlank(message = "'系统名称不能为空'") String systemName);


    /**
     * 根据系统名称，获取所有子菜单
     * @param systemName 菜单系统名称
     * @param showHidden 显示隐藏的菜单
     * @return 菜单列表
     */
    List<Menu> getChildMenusBySystemName(@NotBlank(message = "'系统名称不能为空'") String systemName, boolean showHidden);

    /**
     * 根据系统名称，获取所有子菜单
     * @param systemName 菜单系统名称
     * @return 菜单列表
     */
    List<Menu> getChildMenusBySystemName(@NotBlank(message = "'系统名称不能为空'") String systemName);

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
    /**
     * 获取所有菜单树
     * @return 菜单树
     */
    List<MenuDto> getAllMenusTree();

    /**
     * 分页查询
     * @param searchParameter 查询参数
     * @return 分页查询结果
     */
    PageInfo<Menu> searchPage(PageSearchParameter<MenuSearchDto> searchParameter);

    /**
     * 添加菜单
     * @param addDto 菜单新增dto
     */
    void add(@Valid MenuAddDto addDto);

    /**
     * 修改菜单启用状态
     * @param id 菜单标识符
     * @param enabled 启用状态
     * @param operator 操作人
     */
    void modifyEnabled(@NotNull(message = "'菜单id'不能为空") Long id,
                       @NotNull(message = "'启用状态'不能为空") Boolean enabled,
                       @NotBlank(message = "'操作人'不能为空") String operator);

    /**
     * 删除菜单
     * @param id 菜单标识符
     * @param operator 操作人
     */
    void delete(@NotNull(message = "参数'id'不能为空") Long id,
                @NotBlank(message = "'操作人'不能为空") String operator);

    /**
     * 修改菜单
     * @param editDto 菜单修改dto
     */
    void modify(MenuEditDto editDto);
}
