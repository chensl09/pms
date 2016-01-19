package com.mmd.pms.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 把json返回值中的null替换成""
 */
public class MyJsonObjectMapper extends ObjectMapper {

    public MyJsonObjectMapper() {
        //this(Include.NON_EMPTY);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(
                new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator jgen,
                                          SerializerProvider provider) throws IOException,
                            JsonProcessingException {
                        jgen.writeString("");
                    }
                });
    }

}
