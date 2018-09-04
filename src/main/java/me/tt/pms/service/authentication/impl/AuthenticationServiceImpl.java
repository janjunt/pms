package me.tt.pms.service.authentication.impl;

import me.tt.pms.core.AdviceException;
import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.constants.UserLoginResult;
import me.tt.pms.core.domain.dto.MenuDto;
import me.tt.pms.core.domain.dto.UserLoginDto;
import me.tt.pms.service.authentication.AuthenticationService;
import me.tt.pms.service.resource.MenuService;
import me.tt.pms.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: AuthenticationServiceImpl
 * @Description: 认证服务实现
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/15 11:14
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * 菜单session名称
     */
    private static final String MENU_SESSION_NAME = "menus";
    /**
     * 用户session名称
     */
    private static final String USER_SESSION_NAME = "user";


    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;


    @Override
    public void signIn(UserLoginDto loginDto) {
        User user = userService.getUserByUsername(loginDto.getUsername());
        if(user == null){
            throw new AdviceException(UserLoginResult.NotExist.getName());
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword(), loginDto.getRememberMe());

        subject.login(token);
        List<MenuDto> menus = menuService.getMenusTreeByUserId(user.getId());
        subject.getSession().setAttribute(MENU_SESSION_NAME, menus);
        subject.getSession().setAttribute(USER_SESSION_NAME, user);
    }

    @Override
    public void signOut() {
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
    }

    @Override
    public User getAuthenticatedUser() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            if(subject.getSession().getAttribute(USER_SESSION_NAME) == null){
                String username = (String) subject.getPrincipal();
                User user = userService.getUserByUsername(username);
                if(user == null){
                    return null;
                }
                subject.getSession().setAttribute(USER_SESSION_NAME, user);

            }

            return (User) subject.getSession().getAttribute(USER_SESSION_NAME);
        }

        return null;
    }

    @Override
    public String getAuthenticatedUsername(){
        User user = getAuthenticatedUser();
        if(user != null){
            return user.getUsername();
        }

        return null;
    }
}