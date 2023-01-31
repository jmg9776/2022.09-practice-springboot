package com.sandbox.server.Interceptor;

import com.sandbox.server.service.persist.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class IpInterceptor implements HandlerInterceptor {
    @Autowired
    IpService ipService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        ipService.UpdateIp(clientIp);
        return true;
    }
}

