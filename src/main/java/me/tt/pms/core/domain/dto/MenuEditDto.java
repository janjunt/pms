package me.tt.pms.core.domain.dto;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: MenuEditDto
 * @Description: 菜单修改dto
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/31 13:42
 */
public class MenuEditDto extends MenuAddDto {
    /**
     * 菜单标识符
     */
    @NotNull(message = "'菜单id'不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}