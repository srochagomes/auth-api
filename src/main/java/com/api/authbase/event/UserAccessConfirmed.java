package com.api.authbase.event;

import com.api.authbase.domain.dto.CredentialDTO;
import com.api.authbase.repository.entity.AccessConfirm;
import org.springframework.context.ApplicationEvent;


public class UserAccessConfirmed extends ApplicationEvent {

    private AccessConfirm accessConfirm;
    private CredentialDTO credential;


    public UserAccessConfirmed(Object source, AccessConfirm payload, CredentialDTO pCredential) {
        super(source);
        this.accessConfirm = payload;
        this.credential = pCredential;
    }

    public AccessConfirm getAccessConfirm(){
        return this.accessConfirm;
    }

    public CredentialDTO getCredential() {
        return credential;
    }

    public static UserAccessConfirmed newInstance(Object source, AccessConfirm payload, CredentialDTO pCredential){
        return new UserAccessConfirmed(source, payload, pCredential);
    }

}
