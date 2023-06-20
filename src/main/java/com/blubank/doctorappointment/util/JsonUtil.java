package com.blubank.doctorappointment.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Set;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
//        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm a z"));
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        OBJECT_MAPPER.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
    }
    public static String writeValueAsString(Object val) throws Exception {
        return OBJECT_MAPPER.writeValueAsString(val);
    }
    public static <T> T readValue(String val, Class<T> clazz) throws Exception {
        return OBJECT_MAPPER.readValue(val, clazz);
    }
    public static <T> Set<T> readValueToSet(String val, Class<T> clazz) throws Exception {
        return OBJECT_MAPPER.readValue(val, OBJECT_MAPPER.getTypeFactory().constructCollectionType(Set.class, clazz));
    }
}
