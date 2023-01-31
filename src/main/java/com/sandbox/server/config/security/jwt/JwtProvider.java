package com.sandbox.server.config.security.jwt;

import com.sandbox.server.model.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Getter
@RequiredArgsConstructor
public class JwtProvider {
    private final String key = "test";

    @Value("${sandbox.jwt-ttl}")
    private Long accessTokenLimit;

    @Value("${sandbox.jwt-refresh-ttl}")
    private Long refreshTokenLimit;
    private final UserDetailsService userDetailsService;

    public JwtParam creatToken(String accessToken, String refreshToken) {
        return JwtParam.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new RuntimeException("Token Error");
        }
    }

    public UsernamePasswordAuthenticationToken getAuth(String token) {
        String id = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(userDetails, "" , userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String createAccessToken(Account account) {
        return createJwt(account, accessTokenLimit);
    }

    public String createRefreshToken(Account account) {
        return createJwt(account, accessTokenLimit);
    }

    private String createJwt(Account account, Long timeLimit) {

        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(account.getId());
        String Jwt = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + timeLimit))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return Jwt;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }
}
