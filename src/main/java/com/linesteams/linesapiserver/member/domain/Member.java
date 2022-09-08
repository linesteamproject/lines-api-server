package com.linesteams.linesapiserver.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_id")
    private String oauthId;

    @Column(name = "oauth_type")
    private OAuthType oauthType;

    @Column(name = "refresh_token")
    private String refreshToken;

    public Member(String oauthId, OAuthType oauthType) {
        this.oauthId = oauthId;
        this.oauthType = oauthType;
    }

    public Member() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getOauthId() {
        return oauthId;
    }

    public OAuthType getOauthType() {
        return oauthType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isNotEqualsRefreshToken(String refreshToken) {
        return !Objects.equals(this.refreshToken, refreshToken);
    }
}
