package me.tt.pms.web.controller;

import com.github.pagehelper.PageInfo;
import me.tt.pms.core.domain.Menu;
import me.tt.pms.core.domain.dto.MenuSearchDto;
import me.tt.pms.service.resource.MenuService;
import me.tt.pms.web.datatable.DataSourceRequest;
import me.tt.pms.web.datatable.DataSourceResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: MenuController
 * @Description: 菜单管理前端控制器
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 13:46
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    @Resource
    private MenuService menuService;


    /**
     * 用户列表页面
     * @return 用户列表页面路径
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "menu/list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult<Menu> search(MenuSearchDto searchDto, DataSourceRequest pageParameter){
        PageInfo<Menu> equipments = menuService.searchPage(pageParameter.toPageSearchParameter(searchDto));

        return new DataSourceResult<>(equipments, pageParameter.getDraw());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "menu/add";
    }
}