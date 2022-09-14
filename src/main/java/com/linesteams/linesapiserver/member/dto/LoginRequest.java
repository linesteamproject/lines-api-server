package com.linesteams.linesapiserver.member.dto;

import com.linesteams.linesapiserver.member.domain.OAuthType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    private String id;
    @NotBlank
    private String oauthId;
    @NotNull
    private OAuthType oauthType;

    public LoginRequest() {
    }

    public LoginRequest(String id, String oauthId, OAuthType oauthType) {
        this.id = id;
        this.oauthId = oauthId;
        this.oauthType = oauthType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public OAuthType getOauthType() {
        return oauthType;
    }

    public void setOauthType(OAuthType oauthType) {
        this.oauthType = oauthType;
    }
}
