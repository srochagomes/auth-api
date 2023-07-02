package com.api.authbase.service;


import com.api.authbase.configuration.KeyCloakAuthClient;
import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.domain.dto.KeyCloakAuthbase;
import com.api.authbase.exception.NotFoundException;
import com.api.authbase.repository.UserAuthRepository;
import com.api.authbase.repository.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthbaseService {
    private KeyCloakAuthClient keyCloakAuthClient;

    private UserAuthRepository userAuthRepository;


    public ResponseEntity<String> userAuthentication(AuthbaseDTO authbaseDTO) {
        KeyCloakAuthbase auth = null;


        UserAuth userAuthFound = userAuthRepository.findUserAuthByApplicationIdAndUserLogin(authbaseDTO.getClientId(), authbaseDTO.getUsername())
                .orElseThrow(()->NotFoundException.builder().build());

        auth = KeyCloakAuthbase.builder()
                .grant_type(authbaseDTO.getGranttype())
                .client_id(authbaseDTO.getClientId())
                .client_secret(authbaseDTO.getSecret())
                .password(authbaseDTO.getPassword())
                .username(userAuthFound.getKey().toString())
                .scope(authbaseDTO.getScope())
                .build();


        return this.keyCloakAuthClient.processAuthMicrosservices(auth);
    }

    public ResponseEntity<String> adminAuthentication(AuthbaseDTO authbaseDTO) {
        KeyCloakAuthbase auth = null;


        auth = KeyCloakAuthbase.builder()
                .grant_type(authbaseDTO.getGranttype())
                .client_id(authbaseDTO.getClientId())
                .client_secret(authbaseDTO.getSecret())
                .password(authbaseDTO.getPassword())
                .username(authbaseDTO.getUsername())
                .scope(authbaseDTO.getScope())
                .build();


        return this.keyCloakAuthClient.processAuthMicrosservices(auth);
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