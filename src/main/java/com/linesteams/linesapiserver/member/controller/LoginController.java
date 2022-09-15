package com.linesteams.linesapiserver.member.controller;

import com.linesteams.linesapiserver.dto.ResponseDto;
import com.linesteams.linesapiserver.member.dto.LoginRequest;
import com.linesteams.linesapiserver.member.dto.LoginResponse;
import com.linesteams.linesapiserver.member.service.LogoutService;
import com.linesteams.linesapiserver.member.service.MemberService;
import com.linesteams.linesapiserver.resolver.MemberId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class LoginController {
    private final MemberService memberService;
    private final LogoutService logoutService;

    public LoginController(MemberService memberService, LogoutService logoutService) {
        this.memberService = memberService;
        this.logoutService = logoutService;
    }

    @PostMapping("/v1/member/login")
    public ResponseDto<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseDto.of(memberService.createAccessToken(request));
    }

    @PostMapping("/v1/member/login/actions/refresh")
    public ResponseDto<LoginResponse> refreshToken(@MemberId Long memberId,
                                                   @RequestHeader("X-AUTH-REFRESH-TOKEN") String refreshToken) {
        return ResponseDto.of(memberService.refreshToken(memberId, refreshToken));
    }

    @PutMapping("/v1/member/logout")
    public ResponseDto<Void> logout(@MemberId Long memberId,
                                    @RequestHeader(AUTHORIZATION) String accessToken) {
        logoutService.logout(accessToken);
        return ResponseDto.of(null);
    }
}
