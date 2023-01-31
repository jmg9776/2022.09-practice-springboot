package com.sandbox.server.config.security;

import com.sandbox.server.service.persist.FilterLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final CorsFilter corsFilter;
    private final FilterLogService filterLogService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new LoggingFilter(filterLogService),UsernamePasswordAuthenticationFilter.class);
        http.addFilter(corsFilter);
        http.csrf().disable()
                .authorizeHttpRequests()
                    .antMatchers("/bootstrap/*").permitAll()
                    .antMatchers("/*").permitAll()
                    //.antMatchers("/*").authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .permitAll()
                    .failureUrl("/login?error=ture")
                    .and()
                .logout()
                    .permitAll()
                    .and();

        return http.build();
    }
}
