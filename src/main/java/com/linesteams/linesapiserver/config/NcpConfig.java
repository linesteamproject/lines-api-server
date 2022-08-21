package com.linesteams.linesapiserver.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NcpConfig {

    private final AppConfig appConfig;

    public NcpConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public AmazonS3 amazonS3() {
        AppConfig.Ncp ncp = appConfig.getNcp();
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ncp.getEndPoint(), ncp.getRegionName()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ncp.getAccessKey(), ncp.getSecretKey())))
                .build();
    }
}
