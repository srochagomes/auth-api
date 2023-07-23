package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.UserAccessConfirmedDTO;
import com.api.authbase.repository.entity.AccessConfirm;
import com.api.authbase.repository.entity.UserAuth;

public class UserAccessConfirmParser extends Parser<UserAccessConfirmedDTO, AccessConfirm>{

    public UserAccessConfirmParser(UserAccessConfirmedDTO out){
        this.dto = out;
        this.entity = this.transformDTO(out);
    }
    public UserAccessConfirmParser(AccessConfirm out){
        this.entity = out;
        this.dto = this.transformEntity(out);
    }


    @Override
    protected AccessConfirm transformDTO(UserAccessConfirmedDTO userAccessConfirmedDTO) {
        return null;
    }

    @Override
    protected UserAccessConfirmedDTO transformEntity(AccessConfirm accessConfirm) {
        UserAuth userAuth = accessConfirm.getUserAuth();
        return UserAccessConfirmedDTO.builder()
                .accountId(userAuth.getAccountId())
                .confirmedAt(accessConfirm.getConfirmedAt())
                .key(userAuth.getKey())
                .email(userAuth.getEmail())
                .applicationId(userAuth.getApplicationId())
                .createdAt(userAuth.getCreatedAt())
                .status(userAuth.getStatus())
                .userLogin(userAuth.getUserLogin())
                .userProviderId(userAuth.getUserProviderId())
                .build();
    }
}
