package com.sandbox.server.config;

import com.sandbox.server.Interceptor.HMacInterceptor;
import com.sandbox.server.Interceptor.IpInterceptor;
import com.sandbox.server.Interceptor.UserAgentInterceptor;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Value("${sandbox.use-hmac}")
    int useHMac;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(60 * 60 * 24 * 365);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //.excludePathPatterns("/**"); 해당 경로를 포함한것에는 인터셉터를 적용하지 않음.
        registry.addInterceptor(userAgentInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(ipInterceptor())
                .addPathPatterns("/**");

        if (useHMac == 1) {
            registry.addInterceptor(hMacInterceptor())
                    .addPathPatterns("/**");
        }
    }

    @Bean
    public UserAgentAnalyzer userAgentAnalyzer() {
        UserAgentAnalyzer uaa = UserAgentAnalyzer.newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();
        return uaa;
    }
    @Bean
    public UserAgentInterceptor userAgentInterceptor() {
        return new UserAgentInterceptor(userAgentAnalyzer());
    }
    @Bean
    public IpInterceptor ipInterceptor() { return new IpInterceptor(); }
    @Bean
    public HMacInterceptor hMacInterceptor() {
        return new HMacInterceptor();
    }
}
