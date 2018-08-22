package me.tt.pms.web.controller;

import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.constants.UserLoginResult;
import me.tt.pms.core.domain.dto.UserLoginDto;
import me.tt.pms.service.authentication.AuthenticationService;
import me.tt.pms.service.user.UserService;
import me.tt.pms.web.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @Description: 用户前端控制器
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/9 11:38
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AuthenticationService authenticationService;

    /**
     * 登录页面
     * @return 登录页面路径
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }


    /**
     * 登录
     * @param loginDto 用户登录对象
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(UserLoginDto loginDto) {
        if(loginDto.getRememberMe() == null){
            loginDto.setRememberMe(false);
        }

        UserLoginResult loginResult = userService.validateUser(loginDto.getUsername(), loginDto.getPassword());

        if(loginResult == UserLoginResult.Success){
            User user = userService.getUserByUsername(loginDto.getUsername());
            authenticationService.signIn(user, loginDto.getRememberMe());

            return AjaxResult.success();
        }else {
            return AjaxResult.error(loginResult.getName());
        }
    }

}