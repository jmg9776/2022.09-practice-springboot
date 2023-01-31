package com.sandbox.server.config.filter;

import com.sandbox.server.config.redis.RedisLimit;
import com.sandbox.server.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Profile("jwt")
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter
{
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RedisLimit redisLimit = new RedisLimit();
        if (jwtProvider.resolveToken(request) != null) {
            String token = jwtProvider.resolveToken(request);
            try {
                if (jwtProvider.validateToken(token)) {
                    if (redisLimit.limitCheck(token) == false) {
                        UsernamePasswordAuthenticationToken auth = jwtProvider.getAuth(token);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } catch (RuntimeException e) {
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
