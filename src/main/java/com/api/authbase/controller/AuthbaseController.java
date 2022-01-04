package com.api.authbase.controller;

import com.api.authbase.configuration.ClientCredentialDefault;
import com.api.authbase.configuration.KeyCloakAuthClient;
import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.domain.dto.KeyCloakAuthbase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authbase")
@Slf4j
@Api(tags = {"Authbase"})
@AllArgsConstructor
public class AuthbaseController {

    private KeyCloakAuthClient keyCloakAuthClient;

    private ClientCredentialDefault  clientCredentialDefault;

    @ApiOperation(
            value = "Autenticação do token API - Post",
            authorizations = {@Authorization(value="OAuth2")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autenticação realizada com sucesso"),
            @ApiResponse(code = 400, message = "Informações inválidas para o processo"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Erro desconhecido")})
    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postLogin(
            @RequestBody @Valid @ApiParam(value = "AuthData", required = true, name =  "Authbase") AuthbaseDTO authbaseDTO
    ) {
        log.info("Chamada de login");
        KeyCloakAuthbase auth = null;

        if (authbaseDTO.isClientDefault()){
            auth = KeyCloakAuthbase.builder()
                    .grant_type(clientCredentialDefault.GRANT_TYPE)
                    .client_id(clientCredentialDefault.getId())
                    .client_secret(clientCredentialDefault.getSecret())
                    .password(authbaseDTO.getPassword())
                    .username(authbaseDTO.getUsername())
                    .scope(authbaseDTO.getScope())
                    .build();
        }else{
            auth = KeyCloakAuthbase.builder()
                    .grant_type(authbaseDTO.getGranttype())
                    .client_id(authbaseDTO.getClientId())
                    .client_secret(authbaseDTO.getSecret())
                    .password(authbaseDTO.getPassword())
                    .username(authbaseDTO.getUsername())
                    .scope(authbaseDTO.getScope())
                    .build();
        }


        ResponseEntity<String> stringResponseEntity = this.keyCloakAuthClient.processAuthMicrosservices(auth);
        return stringResponseEntity;
    }

}