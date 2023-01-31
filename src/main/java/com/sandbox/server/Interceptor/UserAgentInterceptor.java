package com.sandbox.server.Interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class UserAgentInterceptor implements HandlerInterceptor {
    private final UserAgentAnalyzer userAgentAnalyzer;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userAgentHeader = request.getHeader("USER-AGENT");
        UserAgent userAgent = userAgentAnalyzer.parse(userAgentHeader);
        Map<String, Object> data = new HashMap<>();

        for (String fieldName: userAgent.getAvailableFieldNamesSorted()){
            data.put(fieldName, userAgent.getValue(fieldName));
        }
        return true;
    }

}
