package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.domain.dto.provider.UserDTO;

public class UserProviderParser extends Parser<UserAccountCreatedDTO, UserDTO> {

    public UserProviderParser(UserAccountCreatedDTO dtoOut){
        this.dto = dtoOut;
        this.entity = this.transformDTO(dtoOut);
    }
    public UserProviderParser(UserDTO userOut){
        this.entity = userOut;
        this.dto = this.transformEntity(userOut);
    }

    @Override
    protected UserDTO transformDTO(UserAccountCreatedDTO userDto) {

        return UserDTO.builder()
                .enabled(true)
                .username(userDto.getKey())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstNameByName())
                .lastName(userDto.getLastNameByName())
                .serviceAccountClientId(userDto.getAccount().getApplication())
                .emailVerified(false)
                .build();
    }

    @Override
    protected UserAccountCreatedDTO transformEntity(UserDTO userAuth) {
        return null;
    }
}
