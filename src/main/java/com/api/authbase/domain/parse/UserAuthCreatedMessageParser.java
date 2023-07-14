package com.api.authbase.domain.parse;

import com.api.authbase.domain.dto.MessageDTO;
import com.api.authbase.domain.dto.SpecificationDTO;
import com.api.authbase.domain.dto.SpecificationDetailDTO;
import com.api.authbase.event.UserAuthCreated;

public class UserAuthCreatedMessageParser extends  Parser<MessageDTO, UserAuthCreated>{

    public UserAuthCreatedMessageParser(MessageDTO out){
        this.dto = out;
        this.entity = this.transformDTO(out);
    }
    public UserAuthCreatedMessageParser(UserAuthCreated out){
        this.entity = out;
        this.dto = this.transformEntity(out);
    }


    @Override
    protected UserAuthCreated transformDTO(MessageDTO messageDTO) {
        return null;
    }

    @Override
    protected MessageDTO transformEntity(UserAuthCreated userAuthCreated) {
        return MessageDTO
                .builder()
                .type(userAuthCreated.getType())
                .specification(SpecificationDTO
                        .builder()
                        .accountId(userAuthCreated.getPayload().getAccountId())
                        .domainType(userAuthCreated.getDomainType())
                        .detail(SpecificationDetailDTO.builder()
                                .emailTo(userAuthCreated.getPayload().getEmail())
                                .nameTo(userAuthCreated.getName())
                                .urlConfirmation(userAuthCreated.getUrlConfirmation())
                                .build())
                        .build())

                .build();

    }
}
