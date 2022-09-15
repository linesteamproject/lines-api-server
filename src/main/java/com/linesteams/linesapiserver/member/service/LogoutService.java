package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.member.domain.Logout;
import com.linesteams.linesapiserver.member.repository.LogoutRepository;
import com.linesteams.linesapiserver.member.service.model.JwtToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutService {
    private final LogoutRepository logoutRepository;

    public LogoutService(LogoutRepository logoutRepository) {
        this.logoutRepository = logoutRepository;
    }

    public void logout(String accessToken) {
        JwtToken jwtToken = new JwtToken(accessToken);
        logoutRepository.save(new Logout(jwtToken.getToken()));
    }

    @Transactional(readOnly = true)
    public boolean isLogoutToken(String accessToken) {
        return logoutRepository.existsByAccessToken(accessToken);
    }
}
