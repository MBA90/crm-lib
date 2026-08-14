package com.crm.lib.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class OAuth2FeignInterceptor implements RequestInterceptor {

    private final OAuth2AuthorizedClientManager clientManager;

    public OAuth2FeignInterceptor(OAuth2AuthorizedClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void apply(RequestTemplate template) {

        String token = getUserToken();

        if (token == null) {
            token = resolveServiceToServiceToken(template);
        }

        if (token != null) {
            // removes any previously set value first — guarantees a single header
            template.removeHeader(HttpHeaders.AUTHORIZATION);
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
    }

    private String getUserToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getTokenValue();
        }
        return null;
    }

    private String  resolveServiceToServiceToken(RequestTemplate template) {
        String registrationId = template.feignTarget().name();

        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId(registrationId)
                .principal(registrationId)
                .build();

        OAuth2AuthorizedClient client = clientManager.authorize(request);
        return client != null ? client.getAccessToken().getTokenValue() : null;
    }
}
