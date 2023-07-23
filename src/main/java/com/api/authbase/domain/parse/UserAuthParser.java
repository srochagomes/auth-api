package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.AccountCreatedDTO;
import com.api.authbase.domain.dto.UserAccountDTO;
import com.api.authbase.repository.entity.UserAuth;

import java.util.UUID;

public class UserAuthParser extends Parser<UserAccountDTO, UserAuth> {
    public static UserAuthParser newInstance(UserAccountDTO dtoOut){
        return new UserAuthParser(dtoOut);
    }
    public static UserAuthParser newInstance(UserAuth userOut){
        return new UserAuthParser(userOut);
    }
    public UserAuthParser(UserAccountDTO dtoOut){
        this.dto = dtoOut;
        this.entity = this.transformDTO(dtoOut);
    }
    public UserAuthParser(UserAuth userOut){
        this.entity = userOut;
        this.dto = this.transformEntity(userOut);
    }

    @Override
    protected UserAuth transformDTO(UserAccountDTO userDto) {
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
    protected UserAccountDTO transformEntity(UserAuth userAuth) {
        return UserAccountDTO.builder()
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
