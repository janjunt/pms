package me.tt.pms.web.datatable;

import me.tt.pms.core.paging.PageSearchParameter;

/**
 * @ClassName: DataSourceRequest
 * @Description: datatable请求
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 14:58
 */
public class DataSourceRequest {
    /**
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private Integer draw;
    /**
     * 第一条数据的起始位置，比如0代表第一条数据
     */
    private Integer start;
    /**
     * 每页显示的条数
     */
    private Integer length;

    /**
     * 根据查询参数，转换为分页查询参数
     * @param searchParameter 查询参数
     * @param <T> 查询参数类型
     * @return 分页查询参数
     */
    public <T> PageSearchParameter<T> toPageSearchParameter(T searchParameter){
        PageSearchParameter<T> pageSearchParameter = new PageSearchParameter<>(getStart(), getLength(), searchParameter);

        return pageSearchParameter;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}