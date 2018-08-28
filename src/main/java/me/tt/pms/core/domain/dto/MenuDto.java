package me.tt.pms.core.domain.dto;

import me.tt.pms.core.domain.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuDto
 * @Description: 菜单传输对象
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/22 16:42
 */
public class MenuDto extends Menu {
    /**
     * 子菜单
     */
    private List<MenuDto> childs = new ArrayList<>();


    public List<MenuDto> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuDto> childs) {
        this.childs = childs;
    }
}