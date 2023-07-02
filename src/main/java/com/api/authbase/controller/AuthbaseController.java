package com.api.authbase.controller;


import com.api.authbase.domain.Authbase;
import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.service.AuthbaseService;
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

    private AuthbaseService service;

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

        ResponseEntity<String> stringResponseEntity = service.userAuthentication(authbaseDTO);
        return stringResponseEntity;
    }

    @Operation(
            description = "Autenticação do token API - Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Informações inválidas para o processo"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Erro desconhecido")})
    @PostMapping(value = "/refresh", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> refreshToken(
            @RequestBody @Valid @Parameter(description =  "AuthData", required = true, name =  "Authbase") AuthbaseDTO authbaseDTO
    ) {
        log.info("Chamada de refresh token");
        return service.processRefreshToken(authbaseDTO);
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

        ResponseEntity<String> session = service.getSession(headers);

        return ResponseEntity.ok( Authbase.builder().token(session.getBody()).build());

    }

}