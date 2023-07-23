package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.UserAccessConfirmedDTO;
import com.api.authbase.event.UserAccessConfirmed;
import com.api.authbase.repository.entity.AccessConfirm;
import com.api.authbase.repository.entity.UserAuth;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserAccessConfirmParser extends Parser<UserAccessConfirmedDTO, UserAccessConfirmed>{

    public UserAccessConfirmParser(UserAccessConfirmedDTO out){
        this.dto = out;
        this.entity = this.transformDTO(out);
    }
    public UserAccessConfirmParser(UserAccessConfirmed out){
        this.entity = out;
        this.dto = this.transformEntity(out);
    }


    @Override
    protected UserAccessConfirmed transformDTO(UserAccessConfirmedDTO userAccessConfirmedDTO) {
        return null;
    }

    @Override
    protected UserAccessConfirmedDTO transformEntity(UserAccessConfirmed accessConfirmedEvent) {
        AccessConfirm accessConfirm = accessConfirmedEvent.getAccessConfirm();
        LocalDateTime confirmedAt = null;
        UserAuth userAuth = null;

        if (Objects.nonNull(accessConfirm)){
            userAuth = accessConfirm.getUserAuth();
            confirmedAt = accessConfirm.getConfirmedAt();
        }
        if(Objects.isNull(userAuth)){
            userAuth = UserAuth.builder().build();
        }


        return UserAccessConfirmedDTO.builder()
                .accountId(userAuth.getAccountId())
                .confirmedAt(confirmedAt)
                .key(userAuth.getKey())
                .email(userAuth.getEmail())
                .applicationId(userAuth.getApplicationId())
                .createdAt(userAuth.getCreatedAt())
                .status(userAuth.getStatus())
                .userLogin(userAuth.getUserLogin())
                .userProviderId(userAuth.getUserProviderId())
                .credential(accessConfirmedEvent.getCredential())
                .build();
    }
}
