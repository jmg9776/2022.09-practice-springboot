package com.sandbox.server.api.auth;

import com.sandbox.server.model.param.CommerceParam;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.service.front.CommerceFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommerceApiAuth {
    private final CommerceFrontService commerceFrontService;
    @PostMapping("/buy")
    public RestResult buy(@RequestBody CommerceParam commerceParam) {
        return commerceFrontService.buy(commerceParam);
    }
}
