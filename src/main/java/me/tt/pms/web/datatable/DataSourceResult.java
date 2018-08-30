package me.tt.pms.web.datatable;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ClassName: DataSourceResult
 * @Description: datatable请求结果
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 15:02
 */
    public class DataSourceResult<T> {
    /**
     * 绘制计数器，接收到是多少，就返回多少
     */
    private Integer draw;
    /**
     * 没有过滤的记录数
     */
    private Long recordsTotal;
    /**
     * 过滤后的记录数
     */
    private Long recordsFiltered;
    /**
     * 当页显示的数据
     */
    private List<T> data;
    /**
     * 错误消息
     */
    private String error;

    /**
     * 根据分页信息等参数的构造函数
     *
     * @param pageInfo 分页信息
     * @param draw     绘制计数器，接收到是多少，就返回多少
     */
    public DataSourceResult(PageInfo<T> pageInfo, Integer draw) {
        this.draw = draw;
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = this.recordsTotal;
        this.data = pageInfo.getList();
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}