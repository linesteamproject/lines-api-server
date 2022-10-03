package com.linesteams.linesapiserver.member.domain;

import com.linesteams.linesapiserver.config.jpa.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_id", columnDefinition = "varchar(32)")
    private String oauthId;

    @Column(name = "oauth_type", columnDefinition = "varchar(16)")
    private OAuthType oauthType;

    @Column(name = "refresh_token", columnDefinition = "varchar(128)")
    private String refreshToken;

    @Column(name = "deleted", columnDefinition = "boolean")
    private boolean deleted;

    public Member(String oauthId, OAuthType oauthType) {
        this.oauthId = oauthId;
        this.oauthType = oauthType;
        this.deleted = false;
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

    public void delete() {
        deleted = true;
    }
}
