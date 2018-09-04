package me.tt.pms.service.authentication;

import me.tt.pms.core.AdviceException;
import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.constants.UserLoginResult;
import me.tt.pms.service.user.UserService;
import me.tt.pms.web.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        UserLoginResult loginResult = userService.validateUser(username, password);
        if(loginResult != UserLoginResult.Success){
            throw new AdviceException(loginResult.getName());
        }

        User user = userService.getUserByUsername(username);
        if(user == null){
            return null;
        }

        usernamePasswordToken.setPassword(user.getPassword().toCharArray());
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}