package com.sandbox.server.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtParam {
    private String accessToken;
    private String refreshToken;
}
