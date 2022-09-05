package com.linesteams.linesapiserver.member.repository;

import com.linesteams.linesapiserver.member.domain.Member;
import com.linesteams.linesapiserver.member.domain.OAuthType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthIdAndOauthType(String oauthId, OAuthType oAuthType);
}
