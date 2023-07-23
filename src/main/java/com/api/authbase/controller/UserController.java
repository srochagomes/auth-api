package com.api.authbase.controller;


import com.api.authbase.service.AccessConfirmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
@Tag(name = "User-API")
@AllArgsConstructor
public class UserController {


    private AccessConfirmService accessConfirmService;


    @Operation(
            description = "Autenticação do token API - Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Confirmação do email realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Informações inválidas para o processo"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "500", description = "Erro desconhecido")})
    @PutMapping(value = "/emails/{key}/verified", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> userConfirm(
            @PathVariable(name = "key") UUID key
    ) {
        log.info("Chamada de refresh token");
        accessConfirmService.processUserConfirm(key);

        return ResponseEntity.ok().build();
    }

}