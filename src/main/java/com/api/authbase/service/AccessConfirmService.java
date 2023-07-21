package com.api.authbase.service;

import com.api.authbase.repository.AccessConfirmRepository;
import com.api.authbase.repository.entity.AccessConfirm;
import com.api.authbase.repository.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AccessConfirmService {

    private AccessConfirmRepository repository;

    @Transactional
    public AccessConfirm create(UserAuth userAuh){

        var accessConfirm = AccessConfirm.builder()
                .key(UUID.randomUUID())
                .userAuth(userAuh)
                .build();

        var accessConfirmCreated = repository.save(accessConfirm);

        return accessConfirmCreated;
    }
}
