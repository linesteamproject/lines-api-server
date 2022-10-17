package com.linesteams.linesapiserver.member.dto;

public class LoginResponse {
    private final String accessToken;
    private final String refreshToken;

    private final Boolean isCreated;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Boolean getCreated() {
        return isCreated;
    }

    public LoginResponse(String accessToken, String refreshToken, Boolean isCreated) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isCreated = isCreated;
    }
}
