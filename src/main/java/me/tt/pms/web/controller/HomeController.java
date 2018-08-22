package me.tt.pms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: HomeController
 * @Description: 默认页面前端控制器
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/22 14:06
 */
@Controller
public class HomeController {

    /**
     * 首页
     * @return 首页路径
     */
    @RequestMapping(value = {"/dashboard", "/index", "/"}, method = RequestMethod.GET)
    public String dashboard() {
        return "home/dashboard";
    }

}