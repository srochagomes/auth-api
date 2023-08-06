package com.api.authbase.event;

import com.api.authbase.domain.dto.TokenDTO;
import org.springframework.context.ApplicationEvent;


public class UserSocialLoginTokenRequested extends ApplicationEvent {

    private TokenDTO tokenPayload;


    public UserSocialLoginTokenRequested(Object source, TokenDTO payload) {
        super(source);
        this.tokenPayload = payload;

    }

    public TokenDTO getTokenPayload(){
        return this.tokenPayload;
    }

    public static UserSocialLoginTokenRequested newInstance(Object source, TokenDTO payload){
        return new UserSocialLoginTokenRequested(source, payload);
    }

}
