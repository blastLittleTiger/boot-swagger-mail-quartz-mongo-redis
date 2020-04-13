package com.zgy.learn.bootswaggermailquartzmongo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String getJsonFromObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
