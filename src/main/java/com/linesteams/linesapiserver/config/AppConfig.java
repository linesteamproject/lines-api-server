package com.linesteams.linesapiserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties("lines")
public class AppConfig {
    private Naver naver;
    private Ncp ncp;

    @Getter
    @Setter
    public static class Naver {
        private String url;
        private String clientId;
        private String clientSecret;
    }

    @Getter
    @Setter
    public static class Ncp {
        private String endPoint;
        private String regionName;
        private String accessKey;
        private String secretKey;
        private String imageBucketName;
    }
}
