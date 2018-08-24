package me.tt.pms.core.utils;

/**
 * @ClassName: StringUtils
 * @Description: 字符串操作方法集
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/24 10:19
 */
public final class StringUtils {
    /**
     * 判断字符串是null或者为空字符串
     * @param value 需要判断的字符串值
     * @return true:是，false:否
     */
    public static boolean isNullOrEmpty(String value){
        return null == value || value.length() == 0;
    }


    private StringUtils(){}
}