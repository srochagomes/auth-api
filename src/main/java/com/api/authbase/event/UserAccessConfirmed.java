package com.api.authbase.event;

import com.api.authbase.repository.entity.AccessConfirm;
import org.springframework.context.ApplicationEvent;


public class UserAccessConfirmed extends ApplicationEvent {

    private AccessConfirm payload;


    public UserAccessConfirmed(Object source, AccessConfirm payload) {
        super(source);
        this.payload = payload;
    }

    public AccessConfirm getPayload(){
        return this.payload;
    }

    public static UserAccessConfirmed newInstance(Object source, AccessConfirm payload){
        return new UserAccessConfirmed(source, payload);
    }

}
