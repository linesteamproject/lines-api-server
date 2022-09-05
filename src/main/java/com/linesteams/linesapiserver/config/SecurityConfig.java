package com.linesteams.linesapiserver.config;

import com.linesteams.linesapiserver.member.service.OAuth2MemberService;
import com.linesteams.linesapiserver.member.service.OAuth2UserSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final OAuth2MemberService oAuth2MemberService;
    private final OAuth2UserSuccessHandler oAuth2UserSuccessHandler;

    public SecurityConfig(OAuth2MemberService oAuth2MemberService, OAuth2UserSuccessHandler oAuth2UserSuccessHandler) {
        this.oAuth2MemberService = oAuth2MemberService;
        this.oAuth2UserSuccessHandler = oAuth2UserSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login().userInfoEndpoint() // oauth2 로그인 성공 후 가져올 때의 설정
                .userService(oAuth2MemberService) // 소셜로그인 성공 후 후속 조치를 진행할 UserService 인터페이스 구현체
                .and().successHandler(oAuth2UserSuccessHandler);
//                .exceptionHandling()
    }
}
