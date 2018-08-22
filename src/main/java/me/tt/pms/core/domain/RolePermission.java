package me.tt.pms.core.domain;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    /**
     * 角色权限关系标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色标识符
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限标识符
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 获取角色权限关系标识符
     *
     * @return id - 角色权限关系标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置角色权限关系标识符
     *
     * @param id 角色权限关系标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色标识符
     *
     * @return role_id - 角色标识符
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色标识符
     *
     * @param roleId 角色标识符
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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