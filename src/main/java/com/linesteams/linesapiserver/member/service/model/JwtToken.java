package com.linesteams.linesapiserver.member.service.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class JwtToken {
    private static final String TOKEN_TYPE = "bearer";

    private final String token;

    public JwtToken(String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith(TOKEN_TYPE)) {
            this.token = parseToken(tokenHeader);
        } else {
            this.token = null;
        }
    }

    private String parseToken(String authorization) {
        return authorization.substring(TOKEN_TYPE.length() + 1);
    }

    public String getToken() {
        return token;
    }

    public Authentication toAuthentication(Long memberId) {
        return new UsernamePasswordAuthenticationToken(memberId, null);
    }
}
