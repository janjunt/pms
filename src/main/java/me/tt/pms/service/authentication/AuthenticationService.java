package me.tt.pms.service.authentication;

import me.tt.pms.core.domain.User;

/**
 * @ClassName: AuthenticationService
 * @Description: 认证服务接口
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/14 16:29
 */
public interface AuthenticationService {

    /**
     * 登录
     * @param user 用户
     * @param rememberMe 记住我
     */
    void signIn(User user, boolean rememberMe);

    /**
     * 登出
     */
    void signOut();

    /**
     * 获取已认证用户
     * @return 用户
     */
    User getAuthenticatedUser();
}
