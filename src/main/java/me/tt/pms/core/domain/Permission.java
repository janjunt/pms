package me.tt.pms.core.domain;

import javax.persistence.*;

public class Permission {
    /**
     * 权限标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源标识符
     */
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 资源动作标识符
     */
    @Column(name = "action_id")
    private Long actionId;

    /**
     * 获取权限标识符
     *
     * @return id - 权限标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置权限标识符
     *
     * @param id 权限标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源标识符
     *
     * @return resource_id - 资源标识符
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源标识符
     *
     * @param resourceId 资源标识符
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取资源动作标识符
     *
     * @return action_id - 资源动作标识符
     */
    public Long getActionId() {
        return actionId;
    }

    /**
     * 设置资源动作标识符
     *
     * @param actionId 资源动作标识符
     */
    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}