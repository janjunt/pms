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

    /**
     * 左补齐
     * @param value 源字符串值
     * @param width 宽度
     * @param paddingChar 用于补齐的字符
     * @return
     */
    public static String padLeft(String value, int width, Character paddingChar) {
        int diff = width - value.length();
        if (diff <= 0) {
            return value;
        }

        if(paddingChar == null){
            paddingChar = ' ';
        }
        char[] charr = new char[width];
        System.arraycopy(value.toCharArray(), 0, charr, diff, value.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = paddingChar;
        }

        return new String(charr);
    }
    /**
     * 右补齐
     * @param value 源字符串值
     * @param width 宽度
     * @param paddingChar 用于补齐的字符
     * @return
     */
    public static String padRight(String value, int width, Character paddingChar) {
        int diff = width - value.length();
        if (diff <= 0) {
            return value;
        }

        if(paddingChar == null){
            paddingChar = ' ';
        }
        char[] charr = new char[width];
        System.arraycopy(value.toCharArray(), 0, charr, 0, value.length());
        for (int i = value.length(); i < width; i++) {
            charr[i] = paddingChar;
        }

        return new String(charr);
    }


    private StringUtils(){}
}