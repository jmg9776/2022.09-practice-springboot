package com.sandbox.server.api.pub;

import com.sandbox.server.config.security.HMacSHA256;
import com.sandbox.server.model.result.RestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public-api/v1")
public class TestApiPub {
    final HMacSHA256 hMacSHA256;
    @GetMapping("/exception")
    public RestResult login(){
        throw new RuntimeException("Throw Exception Test");
    }
    @GetMapping("/getHmac")
    public RestResult hmac() {
        Map<String, Object> data = new HashMap<>();
        data.put("result", hMacSHA256.generate("ok"));
        return new RestResult(data);
    }
}
