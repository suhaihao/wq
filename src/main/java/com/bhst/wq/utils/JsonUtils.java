package com.bhst.wq.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * All rights Reserved, Designed By www.hebeiwanteng.com
 *
 * @author jack
 * @Package com.hebeiwanteng.member.Utils
 * @ClassName:JsonXMLUtils
 * @Descrption:
 * @date 下午4:44 18-9-11
 * @Modify By :
 * @Copyright: 2018 www.hebeiwanteng.com Inc. All rights reserved.
 * 注意：本内容仅限于河北万腾科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JsonUtils {
    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 字段值为null时不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略未知字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T parseJson(String json, Class<T> clz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 字段值为null时不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略未知字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.readValue(json, clz);

    }
}