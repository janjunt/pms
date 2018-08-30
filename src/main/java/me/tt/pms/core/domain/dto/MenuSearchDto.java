package me.tt.pms.core.domain.dto;

/**
 * @ClassName: MenuSearchDto
 * @Description: 菜单查询参数
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 15:13
 */
public class MenuSearchDto {
    /**
     * 名称
     */
    private String name;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 仅启用
     */
    private Boolean enabledOnly;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Boolean getEnabledOnly() {
        return enabledOnly;
    }

    public void setEnabledOnly(Boolean enabledOnly) {
        this.enabledOnly = enabledOnly;
    }
}