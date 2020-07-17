
package com.ttrung.supershop.product.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTokenService extends RemoteTokenServices {

    @Setter
    private String tokenCheckUri;
    private RestTemplate restTemplate;
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    public CustomTokenService(String tokenCheckUri) {
        this.tokenCheckUri = tokenCheckUri;
        this.restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    super.handleError(response);
                }
            }
        });
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        try {
            Map<String, Object> checkTokenResponse = checkToken(tokenCheckUri, accessToken);
            if (!isValidToken(checkTokenResponse)) {
                throw new InvalidTokenException(accessToken);
            }

            return tokenConverter.extractAuthentication(checkTokenResponse);
        } catch (Exception e) {
            throw new InvalidTokenException(accessToken);
        }

    }

    private Map<String, Object> checkToken(String tokenCheckUri, String accessToken) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenCheckUri)
                .queryParam("token", accessToken);

        ResponseEntity<Map> response = restTemplate.getForEntity(builder.toUriString(), Map.class);
        return response.getBody();
    }

    private boolean isValidToken(Map<String, Object> checkTokenResponse) {
        if (checkTokenResponse.containsKey("error")) {
            log.debug("check_token returned error: " + checkTokenResponse.get("error"));
            return false;
        }

        if (checkTokenResponse.containsKey("active") && !"true".equals(String.valueOf(checkTokenResponse.get("active")))) {
            log.debug("check_token returned active attribute: " + checkTokenResponse.get("active"));
            return false;
        }
        return true;
    }
}
