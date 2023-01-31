package com.sandbox.server.model.result;
import java.util.HashMap;
import java.util.Map;
public class RestResultTemplate {
    public RestResult successEnqueue(){
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Successfully enqueued.");
        return new RestResult(data);
    }
}
