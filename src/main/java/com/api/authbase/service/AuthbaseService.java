package com.api.authbase.service;


import com.api.authbase.configuration.KeyCloakAuthClient;
import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.domain.dto.KeyCloakAuthbase;
import com.api.authbase.domain.dto.TokenDTO;
import com.api.authbase.event.UserSocialLoginTokenRequested;
import com.api.authbase.repository.UserAuthRepository;
import com.api.authbase.repository.entity.UserAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthbaseService {

    private KeyCloakAuthClient keyCloakAuthClient;

    private UserAuthRepository userAuthRepository;

    private ApplicationEventPublisher eventPublisher;

    private ObjectMapper mapper;

    public ResponseEntity<String> userAuthentication(AuthbaseDTO authbaseDTO) {
        KeyCloakAuthbase auth = null;

        Optional<UserAuth> userAuthFound = authbaseDTO.isUserPasswordFlow()?
                        userAuthRepository.findUserAuthByApplicationIdAndUserLogin(authbaseDTO.getClientId(), authbaseDTO.getUsername())
                :Optional.empty();

        if (userAuthFound.isPresent()){
            authbaseDTO.setUsername(userAuthFound.get().getKey().toString());
        }


        auth = KeyCloakAuthbase.builder()
                .grant_type(authbaseDTO.getGranttype())
                .client_id(authbaseDTO.getClientId())
                .client_secret(authbaseDTO.getSecret())
                .password(authbaseDTO.getPassword())
                .username(authbaseDTO.getUsername())
                .code(authbaseDTO.getCode())
                .redirect_uri(authbaseDTO.getRedirectURI())
                .scope(authbaseDTO.getScope())
                .build();

        ResponseEntity<String> credentials = this.keyCloakAuthClient.processAuthMicrosservices(auth);

        if (auth.isCodeFlow()){
            TokenDTO token = TokenDTO.transform(mapper, credentials.getBody());
            eventPublisher.publishEvent(UserSocialLoginTokenRequested.newInstance(this, token));
        }


        return credentials;
    }



    public ResponseEntity<String> processRefreshToken(AuthbaseDTO authbaseDTO) {
        KeyCloakAuthbase auth = null;

        auth = KeyCloakAuthbase.builder()
                .grant_type(authbaseDTO.getGranttype())
                .client_id(authbaseDTO.getClientId())
                .client_secret(authbaseDTO.getSecret())
                .refresh_token(authbaseDTO.getRefreshToken())
                .build();


        ResponseEntity<String> responseEntity = this.keyCloakAuthClient.processAuthMicrosservices(auth);

        return responseEntity;
    }

    public ResponseEntity<String> getSession(HttpHeaders headers) {
        return this.keyCloakAuthClient.getSession(headers);
    }
}