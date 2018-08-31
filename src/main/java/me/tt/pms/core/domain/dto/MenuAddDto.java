package me.tt.pms.core.domain.dto;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: MenuAddDto
 * @Description: 菜单新增dto
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 17:31
 */
public class MenuAddDto {
    /**
     * 父标识符
     */
    private Long parentId;

    /**
     * 父菜单路径
     */
    private String parentPath;

    /**
     * 层级（从0开始）
     */
    private Integer level;

    /**
     * 名称
     */
    @NotBlank(message = "'名称'不能为空")
    private String name;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单样式名称
     */
    private String className;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示顺序
     */
    @NotBlank(message = "'显示顺序'不能为空")
    private Integer displayOrder;

    /**
     * 已启用
     */
    private Boolean enabled;

    /**
     * 操作人
     */
    @NotBlank(message = "'操作人'不能为空")
    private String operator;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}