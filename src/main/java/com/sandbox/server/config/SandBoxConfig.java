package com.sandbox.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SandBoxConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
