package me.tt.pms.core.domain;

import javax.persistence.*;

@Table(name = "menu_permission")
public class MenuPermission {
    /**
     * 菜单权限关系标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单标识符
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 权限标识符
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 获取菜单权限关系标识符
     *
     * @return id - 菜单权限关系标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置菜单权限关系标识符
     *
     * @param id 菜单权限关系标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取菜单标识符
     *
     * @return menu_id - 菜单标识符
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单标识符
     *
     * @param menuId 菜单标识符
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取权限标识符
     *
     * @return permission_id - 权限标识符
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限标识符
     *
     * @param permissionId 权限标识符
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}