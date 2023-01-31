package com.sandbox.server.service.front;

import com.sandbox.server.config.security.jwt.JwtParam;
import com.sandbox.server.config.security.jwt.JwtProvider;
import com.sandbox.server.model.entity.Account;
import com.sandbox.server.model.param.AccountParam;
import com.sandbox.server.model.result.AccountFront;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.model.result.RestResultBuilder;
import com.sandbox.server.service.persist.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountFrontService {
    private final AccountService accountService;
    private final RestResultBuilder<Class>restResultBuilder = new RestResultBuilder<>(AccountFront.class,"account");
    private final JwtProvider jwtProvider;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public RestResult withdrawal(AccountParam accountParam) {
        Account account = accountService.withdrawal(accountParam.toEntity());
        return new RestResult(restResultBuilder.resultBuilder(account));
    }

    public RestResult sign(AccountParam accountParam) {
        Account account = accountService.sign(accountParam.toEntity());
        return new RestResult(restResultBuilder.resultBuilder(account));
    }

    public RestResult login(String id, String pw) {
        Account account = accountService.login(id, pw);
        if (bCryptPasswordEncoder.matches(pw, account.getPw()) && account.getUid()!=null) {
            JwtParam jwtParam = jwtProvider.creatToken(jwtProvider.createAccessToken(account),
                    jwtProvider.createRefreshToken(account));
            Map<String, Object> data = new HashMap<>();
            jwtParam.setAccessToken(jwtParam.getAccessToken());
            jwtParam.setRefreshToken(jwtParam.getRefreshToken());
            data.put("token", jwtParam);
            data.put("message", "Login Success");
            data.put("code", "1");
            return new RestResult(data);
        }
        else {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "아이디 또는 비밀번호가 틀립니다.");
            data.put("code", "0");
            return new RestResult(data);
        }
    }
}
