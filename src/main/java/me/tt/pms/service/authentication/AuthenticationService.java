package me.tt.pms.service.authentication;

import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.dto.UserLoginDto;

/**
 * @ClassName: AuthenticationService
 * @Description: 认证服务接口
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/14 16:29
 */
public interface AuthenticationService {

    /**
     * 登录
     * @param loginDto 用户登录dto
     */
    void signIn(UserLoginDto loginDto);

    /**
     * 登出
     */
    void signOut();

    /**
     * 获取已认证用户
     * @return 用户
     */
    User getAuthenticatedUser();

    /**
     * 获取已认证用户名称
     * @return 用户名称
     */
    String getAuthenticatedUsername();
}
