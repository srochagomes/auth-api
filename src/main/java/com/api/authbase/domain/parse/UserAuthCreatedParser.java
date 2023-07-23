package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.AccountCreatedDTO;
import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.repository.entity.UserAuth;

import java.util.UUID;

public class UserAuthCreatedParser extends Parser<UserAccountCreatedDTO, UserAuth> {

    public UserAuthCreatedParser(UserAccountCreatedDTO dtoOut){
        this.dto = dtoOut;
        this.entity = this.transformDTO(dtoOut);
    }
    public UserAuthCreatedParser(UserAuth userOut){
        this.entity = userOut;
        this.dto = this.transformEntity(userOut);
    }

    @Override
    protected UserAuth transformDTO(UserAccountCreatedDTO userDto) {
        AccountCreatedDTO account = userDto.getAccount();
        return UserAuth.builder()
                .key(UUID.fromString(userDto.getKey()))
                .userLogin(userDto.getEmail())
                .applicationId(account.getApplication())
                .email(userDto.getEmail())
                .accountId(UUID.fromString(account.getKey()))
                .termAccept(account.getTermAccept())
                .createdAt(userDto.getDateCreated())
                .emailVerified(userDto.getEmailVerified())
                .status(userDto.getStatus())
                .build();
    }

    @Override
    protected UserAccountCreatedDTO transformEntity(UserAuth userAuth) {
        return UserAccountCreatedDTO.builder()
                .key(userAuth.getKey().toString())
                .email(userAuth.getUserLogin())
                .userLogin(userAuth.getUserLogin())
                .account(AccountCreatedDTO.builder()
                        .key(userAuth.getAccountId().toString())
                        .application(userAuth.getApplicationId())
                        .build())
                .dateCreated(userAuth.getCreatedAt())
                .termAccept(userAuth.getTermAccept())
                .emailVerified(userAuth.getEmailVerified())
                .status(userAuth.getStatus())
                .build();
    }
}
