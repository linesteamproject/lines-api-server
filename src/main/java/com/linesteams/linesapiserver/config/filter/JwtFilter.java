package com.linesteams.linesapiserver.config.filter;

import com.linesteams.linesapiserver.member.service.JwtService;
import com.linesteams.linesapiserver.member.service.model.JwtToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION);

        JwtToken jwtToken = new JwtToken(authorization);
        if (jwtToken.getToken() == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtService.isNotValidToken(jwtToken.getToken())) {
            throw new RuntimeException("토큰 유효성 검증에 실패하였습니다.");
        }

        Long memberId = jwtService.getMemberId(jwtToken.getToken());
        SecurityContextHolder.getContext().setAuthentication(jwtToken.toAuthentication(memberId));

        filterChain.doFilter(request, response);
    }
}
