package me.tt.pms.core.domain;

import java.util.Date;
import javax.persistence.*;

public class Menu {
    /**
     * 菜单标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父标识符
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 父菜单路径
     */
    @Column(name = "parent_path")
    private String parentPath;

    /**
     * 层级（从0开始）
     */
    private Integer level;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单样式名称
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 系统名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示顺序
     */
    @Column(name = "display_order")
    private Integer displayOrder;

    /**
     * 已启用
     */
    private Boolean enabled;

    /**
     * 已删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 获取菜单标识符
     *
     * @return id - 菜单标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置菜单标识符
     *
     * @param id 菜单标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父标识符
     *
     * @return parent_id - 父标识符
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父标识符
     *
     * @param parentId 父标识符
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父菜单路径
     *
     * @return parent_path - 父菜单路径
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * 设置父菜单路径
     *
     * @param parentPath 父菜单路径
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * 获取层级（从0开始）
     *
     * @return level - 层级（从0开始）
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置层级（从0开始）
     *
     * @param level 层级（从0开始）
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单url
     *
     * @return url - 菜单url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单url
     *
     * @param url 菜单url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取菜单样式名称
     *
     * @return class_name - 菜单样式名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置菜单样式名称
     *
     * @param className 菜单样式名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取系统名称
     *
     * @return system_name - 系统名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置系统名称
     *
     * @param systemName 系统名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取显示顺序
     *
     * @return display_order - 显示顺序
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置显示顺序
     *
     * @param displayOrder 显示顺序
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 获取已启用
     *
     * @return enabled - 已启用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置已启用
     *
     * @param enabled 已启用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取已删除
     *
     * @return deleted - 已删除
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置已删除
     *
     * @param deleted 已删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}