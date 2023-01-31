package com.sandbox.server.config.security;

import com.sandbox.server.config.filter.JwtFilter;
import com.sandbox.server.config.filter.LoggingFilter;
import com.sandbox.server.config.security.jwt.JwtImpl;
import com.sandbox.server.config.security.jwt.JwtProvider;
import com.sandbox.server.service.persist.FilterLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile("jwt")
public class SecurityConfigJwt {

    private final CorsFilter corsFilter;
    private final JwtImpl jwtImpl;
    private final FilterLogService filterLogService;

    @Bean
    public UserDetailsService userDetailsService() {
        return jwtImpl;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // Session Store !

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtFilter(new JwtProvider(jwtImpl)), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new LoggingFilter(filterLogService),UsernamePasswordAuthenticationFilter.class);
        http.addFilter(corsFilter);
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/public-api/**").permitAll();


        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS)); //세션사용안함.
        return http.build();
    }
}
