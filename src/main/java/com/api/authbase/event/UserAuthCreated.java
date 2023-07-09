package com.api.authbase.event;

import com.api.authbase.repository.entity.UserAuth;
import org.springframework.context.ApplicationEvent;


public class UserAuthCreated extends ApplicationEvent {

    private UserAuth payload;

    public UserAuthCreated(Object source, UserAuth payload) {
        super(source);
        this.payload = payload;
    }

    public UserAuth getPayload(){
        return this.payload;
    }


}
