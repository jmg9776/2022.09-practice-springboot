package com.sandbox.server.config.redis;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.redis.request.RedisRateLimiterFactory;
import io.lettuce.core.RedisClient;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

@Component
public class RedisLimit {
    public boolean limitCheck(String key) {
        RedisRateLimiterFactory factory = new RedisRateLimiterFactory(RedisClient.create("redis://localhost"));
        Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(Duration.ofMinutes(1), 5));
        RequestRateLimiter requestRateLimiter = factory.getInstance(rules);
        boolean overLimit = requestRateLimiter.overLimitWhenIncremented(key);
        return overLimit;
    }
}
