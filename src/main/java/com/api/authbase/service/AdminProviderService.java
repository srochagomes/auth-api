package com.api.authbase.service;

import com.api.authbase.configuration.KeyCloakAdminClient;
import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.domain.parse.UserAuthParser;
import com.api.authbase.domain.parse.UserProviderParser;
import com.api.authbase.repository.UserAuthRepository;
import com.api.authbase.repository.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AdminProviderService {

    private KeyCloakAdminClient keyCloakAdminClient;

    private UserAuthRepository repository;


    @Transactional(rollbackFor = Throwable.class)
    public void registerUserAuth(UserAccountCreatedDTO userCreatedDTO){

        Optional<UserAuth> userFound = repository.findById(UUID.fromString(userCreatedDTO.getKey()));

        if (userFound.isEmpty()){
            var parser = new UserAuthParser(userCreatedDTO);
            UserAuth userRegistered = repository.save(parser.asEntity());

            var parserProvider = new UserProviderParser(userCreatedDTO);

            ResponseEntity<String> userResponde = keyCloakAdminClient.createUser(parserProvider.asEntity());
            Optional<String> location = userResponde.getHeaders().get("location").stream().findFirst();
            if (location.isPresent()){
                userRegistered.setUserProviderUrl(location.get());
            }

            if (!userResponde.getStatusCode().is2xxSuccessful()){
                log.error("Erro ao registrar o usuario ",userCreatedDTO);
                log.error(userResponde.getBody());
                throw new RuntimeException("Erro ao criar usuário "+ userResponde.toString());
            }
        }

    }
}