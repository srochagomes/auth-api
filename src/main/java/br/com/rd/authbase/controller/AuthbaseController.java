package br.com.rd.authbase.controller;

import java.util.Optional;

import br.com.rd.authbase.configuration.KeyCloakAuthClient;
import br.com.rd.authbase.domain.dto.AuthbaseDTO;
import br.com.rd.authbase.domain.dto.KeyCloakAuthbase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
    @RequestMapping("/authbase")
@Slf4j
@Api(tags = {"Authbase"})
@AllArgsConstructor
public class AuthbaseController {

    private KeyCloakAuthClient keyCloakAuthClient;

    @ApiOperation(
        value = "Exemplo de API - Get",
        authorizations = {@Authorization(value="OAuth2")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Chamada realizada com sucesso"),
        @ApiResponse(code = 401, message = "Acesso não autorizado"),
        @ApiResponse(code = 500, message = "Erro desconhecido")})
    @GetMapping
    public ResponseEntity<String> getExemplo() {
        log.info("Exemplo de API - Get. application=Hello World!");
        return ResponseEntity.ok("Get: Hello World!");
    }

    @ApiOperation(
        value = "Exemplo de API - Post",
        authorizations = {@Authorization(value="OAuth2")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Chamada realizada com sucesso"),
        @ApiResponse(code = 401, message = "Acesso não autorizado"),
        @ApiResponse(code = 500, message = "Erro desconhecido")})
    @PostMapping
    public ResponseEntity<String> postExemplo(
        @ApiParam(value = "Name - Digite um nome...")
        @RequestParam("name") Optional<String> name
    ) {
        log.info("Exemplo de API - Post. application=Hello World!");
        return ResponseEntity.ok("Post: Hello World!");
    }

    @ApiOperation(
            value = "Exemplo de API - Post",
            authorizations = {@Authorization(value="OAuth2")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Chamada realizada com sucesso"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 500, message = "Erro desconhecido")})
    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postLogin(
            @RequestBody @Valid @ApiParam(value = "AuthData", required = true, name =  "Authbase") AuthbaseDTO authbaseDTO
    ) {
        log.info("Chamada de login");


        KeyCloakAuthbase  auth =  KeyCloakAuthbase.builder()
                            .client_id("login-poc")
                            .grant_type("password")
                            .username(authbaseDTO.getClientId())
                            .password(authbaseDTO.getSecrete())
                            .build();
        ResponseEntity<String> stringResponseEntity = this.keyCloakAuthClient.processAuthMicrosservices(
                auth
        );

        return stringResponseEntity;
    }

}