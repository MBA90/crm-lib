package com.crm.lib.config;

import com.crm.lib.interceptor.OAuth2FeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class FeignOAuth2Config {

    @Bean
    public RequestInterceptor oAuth2FeignInterceptor(
            OAuth2AuthorizedClientManager clientManager) {
        return new OAuth2FeignInterceptor(clientManager);
    }
}
