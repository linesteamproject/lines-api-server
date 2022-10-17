package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.member.domain.Member;
import com.linesteams.linesapiserver.member.domain.OAuthType;
import com.linesteams.linesapiserver.member.dto.LoginRequest;
import com.linesteams.linesapiserver.member.dto.LoginResponse;
import com.linesteams.linesapiserver.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public MemberService(MemberRepository memberRepository, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    public Member createMember(String oauthId, OAuthType oauthType) {
        return memberRepository.save(new Member(oauthId, oauthType));
    }

    public Member createOrUpdate(String oauthId, OAuthType oauthType) {
        Member member = memberRepository.findByOauthIdAndOauthTypeAndDeletedFalse(oauthId, oauthType)
                .orElseGet(() -> createMember(oauthId, oauthType));

        member.updateRefreshToken(jwtService.createRefreshToken());

        return member;
    }

    @Transactional(readOnly = true)
    public Member getMemberRaiseIfNotFound(Long memberId) {
        return memberRepository.findByIdAndDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
    }

    public LoginResponse createAccessToken(LoginRequest request) {
        Member member = createOrUpdate(request.getOauthId(), request.getOauthType());
        String accessToken = jwtService.createAccessToken(member.getId());

        return new LoginResponse(accessToken, member.getRefreshToken(), member.getIsCreated(request.getRequestAt()));
    }

    public LoginResponse refreshToken(Long memberId, String refreshToken) {
        Member member = getMemberRaiseIfNotFound(memberId);

        if (member.isNotEqualsRefreshToken(refreshToken) && jwtService.isNotValidToken(refreshToken)) {
            throw new RuntimeException("리프레시 토큰값이 일치하지 않거나 토큰이 유효하지 않습니다.");
        }
        return new LoginResponse(jwtService.createAccessToken(member.getId()), member.getRefreshToken(), false);
    }

    public void deleteMember(Long memberId) {
        Member member = getMemberRaiseIfNotFound(memberId);
        member.delete();
    }
}
