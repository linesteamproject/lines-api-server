package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.member.domain.Member;
import com.linesteams.linesapiserver.member.domain.OAuthType;
import com.linesteams.linesapiserver.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(String oauthId, OAuthType oauthType) {
        return memberRepository.save(new Member(oauthId, oauthType));
    }

    public Member createOrUpdate(String oauthId, OAuthType oauthType) {
        return memberRepository.findByOauthIdAndOauthType(oauthId, oauthType)
                .orElseGet(() -> createMember(oauthId, oauthType));
    }
}
