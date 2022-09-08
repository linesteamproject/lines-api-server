package com.linesteams.linesapiserver.member.controller;

import com.linesteams.linesapiserver.member.dto.LoginRequest;
import com.linesteams.linesapiserver.member.dto.LoginResponse;
import com.linesteams.linesapiserver.member.service.MemberService;
import com.linesteams.linesapiserver.resolver.MemberId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/v1/member/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return memberService.createAccessToken(request);
    }

    @PostMapping("/v1/member/login/actions/refresh")
    public LoginResponse refreshToken(@MemberId Long memberId,
                                      @RequestHeader("X-AUTH-REFRESH-TOKEN") String refreshToken) {
        return memberService.refreshToken(memberId, refreshToken);
    }
}
