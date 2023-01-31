package com.sandbox.server.api.pub;

import com.sandbox.server.model.param.AccountParam;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.service.front.AccountFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public-api/v1")
public class AccountApiPub {
    private final AccountFrontService accountFrontService;
    @PostMapping("/login")
    public RestResult login(@RequestBody AccountParam accountParam){
        return accountFrontService.login(accountParam.getId(),accountParam.getPw());
    }
}
