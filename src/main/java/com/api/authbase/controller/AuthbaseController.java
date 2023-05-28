package com.api.authbase.controller;

import com.api.authbase.configuration.ClientCredentialDefault;
import com.api.authbase.configuration.KeyCloakAuthClient;
import com.api.authbase.domain.Authbase;
import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.domain.dto.KeyCloakAuthbase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authbase")
@Slf4j
@Tag(name = "Auth-API")
@AllArgsConstructor
public class AuthbaseController {

    private KeyCloakAuthClient keyCloakAuthClient;

    private ClientCredentialDefault  clientCredentialDefault;

    @Operation(
            description = "Autenticação do token API - Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Informações inválidas para o processo"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Erro desconhecido")})
    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postLogin(
            @RequestBody @Valid @Parameter(description =  "AuthData", required = true, name =  "Authbase") AuthbaseDTO authbaseDTO
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

    @Operation(
            description = "Recupera a sessão do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Informações inválidas para o processo"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Erro desconhecido")})
    @GetMapping(value = "/sessions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Authbase> session(
            @RequestHeader HttpHeaders headers
    ) {
        log.info("Chamada de session");

        ResponseEntity<String> session = this.keyCloakAuthClient.getSession(headers);

        return ResponseEntity.ok( Authbase.builder().token(session.getBody()).build());

    }

}