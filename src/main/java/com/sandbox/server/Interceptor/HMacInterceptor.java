package com.sandbox.server.Interceptor;

import com.sandbox.server.config.redis.RedisLimit;
import com.sandbox.server.config.security.HMacSHA256;
import com.sandbox.server.service.persist.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class HMacInterceptor implements HandlerInterceptor {
    @Autowired
    HMacSHA256 hMacSHA256;
    @Autowired
    RedisLimit redisLimit;
    @Autowired
    AccountService accountService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        String serverTime =
                localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        //getHeaders
        Date date = new Date();
        date.setTime(Long.parseLong(request.getHeader("SANDBOX-API-TIMESTAMP")));
        String requestTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String cat = "";
        String requestKey = request.getHeader("SANDBOX-API-DIGEST");
        String accessKey = request.getHeader("SANDBOX-API-ACCESS-KEY");
        cat = cat.concat(accessKey);
        cat = cat.concat(requestTime);
        cat = cat.concat(request.getRequestURL().toString());
        cat = cat.concat(request.getMethod());

        String serverKey = hMacSHA256.generate(cat);

        if (serverTime.equals(requestTime) && serverKey.equals(requestKey)) {
            if (redisLimit.limitCheck(accessKey) == false)
                return true;
        }
        return false;
    }
}
