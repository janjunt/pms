package me.tt.pms.core.domain;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 用户标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 系统名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * 真实名称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    @Column(name = "password_salt")
    private String passwordSalt;

    /**
     * 已激活
     */
    private Boolean activated;

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
     * 访问时间
     */
    @Column(name = "access_time")
    private Date accessTime;

    /**
     * 访问ip地址
     */
    @Column(name = "access_ip")
    private String accessIp;

    /**
     * 获取用户标识符
     *
     * @return id - 用户标识符
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户标识符
     *
     * @param id 用户标识符
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
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
     * 获取真实名称
     *
     * @return full_name - 真实名称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置真实名称
     *
     * @param fullName 真实名称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码盐
     *
     * @return password_salt - 密码盐
     */
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * 设置密码盐
     *
     * @param passwordSalt 密码盐
     */
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    /**
     * 获取已激活
     *
     * @return activated - 已激活
     */
    public Boolean getActivated() {
        return activated;
    }

    /**
     * 设置已激活
     *
     * @param activated 已激活
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
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

    /**
     * 获取访问时间
     *
     * @return access_time - 访问时间
     */
    public Date getAccessTime() {
        return accessTime;
    }

    /**
     * 设置访问时间
     *
     * @param accessTime 访问时间
     */
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    /**
     * 获取访问ip地址
     *
     * @return access_ip - 访问ip地址
     */
    public String getAccessIp() {
        return accessIp;
    }

    /**
     * 设置访问ip地址
     *
     * @param accessIp 访问ip地址
     */
    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }
}