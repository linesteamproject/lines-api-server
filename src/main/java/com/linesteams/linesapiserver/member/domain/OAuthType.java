package com.linesteams.linesapiserver.member.domain;

import java.util.Locale;

public enum OAuthType {
    NAVER,
    KAKAO,
    APPLE;

    public static OAuthType of(String authType) {
        return OAuthType.valueOf(authType.toUpperCase(Locale.ROOT));
    }
}
