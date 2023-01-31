package com.sandbox.server.api.auth;

import com.sandbox.server.api.thirdParty.RabbitMq;
import com.sandbox.server.model.param.AccountParam;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.model.result.RestResultTemplate;
import com.sandbox.server.service.front.AccountFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountApiAuth {
    private final AccountFrontService accountFrontService;
    private final RabbitMq rabbitMq;
    private RestResultTemplate restResultTemplate = new RestResultTemplate();
    @GetMapping ("/runtimeExceptionTest")
    RestResult runtimeExceptionTest() {
        throw new RuntimeException("Runtime Exception Test");
    }
    @PostMapping("/sign")
    public RestResult sign(@RequestBody AccountParam accountParam){
        return accountFrontService.sign(accountParam);
    }
    @PostMapping("/withdrawal")
    public RestResult withdrawal(@RequestBody AccountParam accountParam){
        return rabbitMq.withdrawal(accountParam);
    }
}
