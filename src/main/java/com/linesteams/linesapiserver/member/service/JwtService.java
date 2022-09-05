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
    private static final Integer TOKEN_EXPIRED_DAYS = 30;

    private final AppConfig appConfig;

    public JwtService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public String create(Long id) {
        Claims claims = Jwts.claims();
        claims.put(ID, id);

        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, appConfig.getJwtSecretKey())
                .setClaims(claims)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(now.plusDays(TOKEN_EXPIRED_DAYS)))
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException e) {
            return false;
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
