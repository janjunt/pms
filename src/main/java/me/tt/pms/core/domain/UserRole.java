package me.tt.pms.core.domain;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户角色关系标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户标识符
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色标识符
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 获取用户角色关系标识符
     *
     * @return id - 用户角色关系标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户角色关系标识符
     *
     * @param id 用户角色关系标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户标识符
     *
     * @return user_id - 用户标识符
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识符
     *
     * @param userId 用户标识符
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
}