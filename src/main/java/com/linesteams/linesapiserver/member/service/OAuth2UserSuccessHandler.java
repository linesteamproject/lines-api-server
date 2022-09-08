package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.config.AppConfig;
import com.linesteams.linesapiserver.member.domain.Member;
import com.linesteams.linesapiserver.member.service.model.OAuth2MemberContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class OAuth2UserSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final MemberService memberService;
    private final AppConfig appConfig;

    public OAuth2UserSuccessHandler(JwtService jwtService, MemberService memberService, AppConfig appConfig) {
        this.jwtService = jwtService;
        this.memberService = memberService;
        this.appConfig = appConfig;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2MemberContext memberContext = (OAuth2MemberContext) authentication.getPrincipal();
        try {
            Member member = memberService.createOrUpdate(memberContext.getName(), memberContext.getOAuthType());
            String jwt = jwtService.createAccessToken(member.getId());
            redirectOnSuccess(response, jwt);
        } catch (Exception e) {
            redirectOnFail(response);
        }
    }

    private void redirectOnSuccess(HttpServletResponse response, String jwt) throws IOException {
        response.sendRedirect(appConfig.getLoginRedirectUrl() + "?access_token=" + jwt);
    }

    private void redirectOnFail(HttpServletResponse response) throws IOException {
        response.sendRedirect(appConfig.getLoginRedirectUrl() + "login=false");
    }
}
