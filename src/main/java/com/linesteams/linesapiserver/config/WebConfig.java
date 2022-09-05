package com.linesteams.linesapiserver.config;

import com.linesteams.linesapiserver.resolver.MemberIdHandlerMethodResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MemberIdHandlerMethodResolver memberIdHandlerMethodResolver;

    public WebConfig(MemberIdHandlerMethodResolver memberIdHandlerMethodResolver) {
        this.memberIdHandlerMethodResolver = memberIdHandlerMethodResolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(memberIdHandlerMethodResolver);
    }
}
