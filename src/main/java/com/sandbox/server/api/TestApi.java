package com.sandbox.server.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class TestApi {
    @GetMapping("/test")
    public Object test() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("result", "ok");

        return data;
    }
}
