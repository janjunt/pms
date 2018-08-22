package me.tt.pms.service.authentication;

import me.tt.pms.core.domain.User;
import me.tt.pms.service.user.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @ClassName: DefaultUserRealm
 * @Description: shiro Realm默认实现
 * @author: tongjianjun@itiaoling.com
 * @date 2018/7/6 14:26
 */
public class DefaultUserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByUsername(username);
        if(user == null){
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}