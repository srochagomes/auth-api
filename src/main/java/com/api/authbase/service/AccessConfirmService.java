package com.api.authbase.service;

import com.api.authbase.domain.dto.CredentialDTO;
import com.api.authbase.domain.dto.UserAccountDTO;
import com.api.authbase.domain.parse.UserAuthParser;
import com.api.authbase.event.UserAccessConfirmed;
import com.api.authbase.exception.BusinessException;
import com.api.authbase.exception.NotFoundException;
import com.api.authbase.repository.AccessConfirmRepository;
import com.api.authbase.repository.entity.AccessConfirm;
import com.api.authbase.repository.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AccessConfirmService {

    private AccessConfirmRepository repository;

    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public AccessConfirm create(UserAuth userAuh){

        var accessConfirm = AccessConfirm.builder()
                .key(UUID.randomUUID())
                .userAuth(userAuh)
                .build();

        var accessConfirmCreated = repository.save(accessConfirm);

        return accessConfirmCreated;
    }

    @Transactional
    public UserAccountDTO processUserConfirm(UUID key, CredentialDTO newCredential) {
        AccessConfirm accessConfirm = repository.findById(key).orElseThrow(() -> NotFoundException.builder().description("Key not found").build());

        if (accessConfirm.isConfirmed()){
            throw BusinessException.builder()
                    .httpStatusCode(HttpStatus.CONFLICT)
                    .code("001")
                    .description("Key has already utilized")
                    .build();
        }

        accessConfirm.registerConfirmation();
        eventPublisher.publishEvent(UserAccessConfirmed.newInstance(this, accessConfirm, newCredential));
        return UserAuthParser.newInstance(accessConfirm.getUserAuth()).asDTO();
    }
}
