package com.linesteams.linesapiserver.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", oauthId='" + oauthId + '\'' +
                ", oauthType=" + oauthType +
                '}';
    }
}
