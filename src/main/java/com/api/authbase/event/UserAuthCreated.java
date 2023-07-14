package com.api.authbase.event;

import com.api.authbase.domain.enums.DomainType;
import com.api.authbase.domain.enums.MessageType;
import com.api.authbase.repository.entity.UserAuth;

import org.springframework.context.ApplicationEvent;


public class UserAuthCreated extends ApplicationEvent {

    private UserAuth payload;
    private MessageType type;
    private DomainType domainType;
    private String name;
    private String urlConfirmation;


    public UserAuthCreated(Object source, UserAuth payload) {
        super(source);
        this.payload = payload;
    }

    public UserAuth getPayload(){
        return this.payload;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
    }

    public void setPayload(UserAuth payload) {
        this.payload = payload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlConfirmation() {
        return urlConfirmation;
    }

    public void setUrlConfirmation(String urlConfirmation) {
        this.urlConfirmation = urlConfirmation;
    }
}
