package me.tt.pms.service.authentication.impl;

import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.dto.MenuDto;
import me.tt.pms.core.domain.dto.UserLoginDto;
import me.tt.pms.service.authentication.AuthenticationService;
import me.tt.pms.service.resource.MenuService;
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
    @Resource
    private MenuService menuService;


    @Override
    public void signIn(UserLoginDto loginDto) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword(), loginDto.getRememberMe());

        subject.login(token);
        List<MenuDto> menus = menuService.getMenusTreeByUserId(0L);
        subject.getSession().setAttribute("menus", menus);
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
            return (User) subject.getPrincipal();
        }

        return null;
    }
}