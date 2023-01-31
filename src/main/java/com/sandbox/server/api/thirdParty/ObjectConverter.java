package com.sandbox.server.api.thirdParty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String objectToJson(Object obj) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    public Object jsonToObject(String json, Class target) {
        Object obj = null;
        try {
            obj = objectMapper.readValue(json, target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
