package com.linesteams.linesapiserver.member.domain;

import com.linesteams.linesapiserver.config.jpa.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logout")
public class Logout extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", unique = true, columnDefinition = "varchar(128)")
    private String accessToken;

    public Logout() {
    }

    public Logout(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
