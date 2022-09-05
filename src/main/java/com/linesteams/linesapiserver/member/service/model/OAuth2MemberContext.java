package com.linesteams.linesapiserver.member.service.model;

import com.linesteams.linesapiserver.member.domain.OAuthType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class OAuth2MemberContext implements OAuth2User {
    private final OAuthType oAuthType;
    private final String attributeName;
    private final OAuthAttribute attributes;

    public OAuth2MemberContext(OAuthType oAuthType, String attributeName, OAuthAttribute attributes) {
        this.oAuthType = oAuthType;
        this.attributeName = attributeName;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.getAttributes().get(attributeName));
    }

    public OAuthType getOAuthType() {
        return oAuthType;
    }
}
