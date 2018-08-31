package me.tt.pms.web.controller;

import com.github.pagehelper.PageInfo;
import me.tt.pms.core.domain.Menu;
import me.tt.pms.core.domain.dto.MenuAddDto;
import me.tt.pms.core.domain.dto.MenuDto;
import me.tt.pms.core.domain.dto.MenuEditDto;
import me.tt.pms.core.domain.dto.MenuSearchDto;
import me.tt.pms.service.authentication.AuthenticationService;
import me.tt.pms.service.resource.MenuService;
import me.tt.pms.web.AjaxResult;
import me.tt.pms.web.datatable.DataSourceRequest;
import me.tt.pms.web.datatable.DataSourceResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private AuthenticationService authenticationService;


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
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/add");

        List<MenuDto> menus = menuService.getAllMenusTree();
        mv.addObject("menusTree", menus);

        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(MenuAddDto addDto) {
        addDto.setOperator(authenticationService.getAuthenticatedUsername());
        menuService.add(addDto);

        return AjaxResult.success();
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult enable(Long id) {
        menuService.modifyEnabled(id, true, authenticationService.getAuthenticatedUsername());

        return AjaxResult.success();
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult disable(Long id) {
        menuService.modifyEnabled(id, false, authenticationService.getAuthenticatedUsername());

        return AjaxResult.success();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/edit");

        List<MenuDto> menus = menuService.getAllMenusTree();
        mv.addObject("menusTree", menus);

        Menu model = menuService.getById(id);
        mv.addObject("model", model);

        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult edit(MenuEditDto editDto) {
        editDto.setOperator(authenticationService.getAuthenticatedUsername());
        menuService.modify(editDto);

        return AjaxResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delete(@PathVariable Long id){
        menuService.delete(id, authenticationService.getAuthenticatedUsername());

        return AjaxResult.success();
    }
}