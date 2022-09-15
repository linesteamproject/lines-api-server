package com.linesteams.linesapiserver.member.repository;

import com.linesteams.linesapiserver.member.domain.Logout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogoutRepository extends JpaRepository<Logout, Long> {
    boolean existsByAccessToken(String accessToken);
}
