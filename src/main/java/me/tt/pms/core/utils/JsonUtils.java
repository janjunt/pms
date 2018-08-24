package me.tt.pms.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @ClassName: JsonUtils
 * @Description: json处理方法集
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/24 13:55
 */
public final class JsonUtils {
    /**
     * 把对象序列化为字符串
     * @param obj 对象
     * @return json字符串
     * @throws JsonProcessingException 序列化失败
     */
    public static String serialize(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }

    /**
     * 把json字符串解析为对象
     * @param json json字符串
     * @param t 对象类型
     * @param <T> 对象类型class
     * @return 对象
     * @throws IOException 解析失败
     */
    public static <T> T deserialize(String json, Class<T> t) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, t);
    }


    private JsonUtils(){}
}