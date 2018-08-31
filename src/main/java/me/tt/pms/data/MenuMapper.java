package me.tt.pms.data;

import me.tt.pms.core.domain.Menu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface MenuMapper extends Mapper<Menu> {

    /**
     * 获取数据库当前时间
     * @return 数据库当前时间
     */
    @Select("SELECT NOW() FROM DUAL")
    Date selectCurrentDateTime();
}