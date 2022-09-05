package com.linesteams.linesapiserver.member.service;

import com.linesteams.linesapiserver.member.domain.OAuthType;
import com.linesteams.linesapiserver.member.service.model.OAuth2MemberContext;
import com.linesteams.linesapiserver.member.service.model.OAuthAttribute;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2MemberService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2 서비스 id (네이버, 카카오, 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String attributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttribute oAuthAttribute = new OAuthAttribute(oAuth2User.getAttributes());

        return new OAuth2MemberContext(OAuthType.of(registrationId), attributeName, oAuthAttribute);
    }
}
