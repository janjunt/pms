package me.tt.pms.core.paging;

/**
 * @ClassName: PageSearchParameter
 * @Description: 分页查询参数
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/30 15:00
 */
public class PageSearchParameter<T> {
    /**
     * 分页索引，从0开始
     */
    private Integer pageIndex;
    /**
     * 分页号，从1开始
     */
    private Integer pageNum;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 查询参数对象
     */
    private T searchParameter;

    /**
     * 分页索引构造函数
     *
     * @param pageIndex       分页索引
     * @param pageSize        每页大小
     * @param searchParameter 查询参数对象
     */
    public PageSearchParameter(Integer pageIndex, Integer pageSize, T searchParameter) {
        this.pageIndex = pageIndex;
        this.pageNum = pageIndex + 1;
        this.pageSize = pageSize;
        this.searchParameter = searchParameter;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(T searchParameter) {
        this.searchParameter = searchParameter;
    }
}