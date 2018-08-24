package me.tt.pms.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @ClassName: ListUtils
 * @Description: 列表方法操作集
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/24 14:39
 */
public final class ListUtils {
    /**
     * 判断列表是null或者为空字符串
     * @param list 需要判断的列表
     * @return true:是，false:否
     */
    public static <T> boolean isNullOrEmpty(List<T> list){
        return null == list || list.size() == 0;
    }

    /**
     * 根据过滤方法，过滤指定列表
     * @param list 指定列表
     * @param filter 过滤方法
     * @param <T> 列表项类型
     * @return 过滤结果列表
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> filter){
        List<T> filterResult = new ArrayList<>();
        if(isNullOrEmpty(list)){
            return filterResult;
        }

        list.forEach(item -> {
            if(filter == null || filter.test(item)){
                filterResult.add(item);
            }
        });

        return filterResult;
    }

    /**
     * 根据过滤方法，判断指定列表是否存在任何符合条件的
     * @param list 指定列表
     * @param filter 过滤方法
     * @param <T> 列表项类型
     * @return true:存在，false:不存在
     */
    public static <T> boolean any(List<T> list, Predicate<T> filter){
        if(isNullOrEmpty(list)){
            return false;
        }

        for (T item : list) {
            if(filter == null || filter.test(item)){
                return true;
            }
        }

        return false;
    }


    /**
     * 根据过滤方法，过滤指定列表
     * @param list 指定列表
     * @param filter 过滤方法
     * @param <T> 列表项类型
     * @return 过滤结果列表
     */
    public static <T> T find(List<T> list, Predicate<T> filter){
        if(isNullOrEmpty(list)){
            return null;
        }

        for (T item : list) {
            if(filter == null || filter.test(item)){
                return item;
            }
        }

        return null;
    }


    /**
     * 列表映射
     * @param list 指定列表
     * @param mapper 映射器
     * @param <T> 列表项类型
     * @return 映射结果
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper){
        List<R> result = new ArrayList<>();
        if(isNullOrEmpty(list) || mapper == null){
            return result;
        }

        list.forEach(tItem -> result.add(mapper.apply(tItem)));

        return result;
    }



    private ListUtils(){}
}