package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.config.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class JwtService {
    private static final String ID = "id";
    private static final Integer ACCESS_TOKEN_EXPIRED_DAYS = 5;
    private static final Integer REFRESH_TOKEN_EXPIRED_DAYS = 30;

    private final AppConfig appConfig;
    private final LogoutService logoutService;

    public JwtService(AppConfig appConfig, LogoutService logoutService) {
        this.appConfig = appConfig;
        this.logoutService = logoutService;
    }

    public String createAccessToken(Long id) {
        Claims claims = Jwts.claims();
        claims.put(ID, id);

        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, appConfig.getJwtSecretKey())
                .setClaims(claims)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(now.plusDays(ACCESS_TOKEN_EXPIRED_DAYS)))
                .compact();
    }

    public String createRefreshToken() {
        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, appConfig.getJwtSecretKey())
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(now.plusDays(REFRESH_TOKEN_EXPIRED_DAYS)))
                .compact();
    }

    public boolean isNotValidToken(String token) {
        if (logoutService.isLogoutToken(token)) {
            return true;
        }

        try {
            parse(token);
            return false;
        } catch (JwtException e) {
            return true;
        }
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser()
                .setSigningKey(appConfig.getJwtSecretKey())
                .parseClaimsJws(token);
    }

    public Long getMemberId(String token) {
        Jws<Claims> claims = parse(token);

        String memberIdStr = String.valueOf(claims.getBody().get(ID));
        return Long.valueOf(memberIdStr);
    }
}
