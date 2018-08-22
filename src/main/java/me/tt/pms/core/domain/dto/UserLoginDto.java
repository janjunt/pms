package me.tt.pms.core.domain.dto;

/**
 * @ClassName: UserLoginDto
 * @Description: 用户登录传输对象
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/14 15:08
 */
public class UserLoginDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住我
     */
    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}