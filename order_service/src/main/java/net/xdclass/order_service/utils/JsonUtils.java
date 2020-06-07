package net.xdclass.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json 工具类
 */
public class JsonUtils {

    private static  final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Json 字符串转换对象方法
     */
    public static JsonNode StrToJsonNode(String string){

        try {
            return OBJECT_MAPPER.readTree(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
