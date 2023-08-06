package com.api.authbase.listener;

import com.api.authbase.event.UserSocialLoginTokenRequested;
import lombok.AllArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
@AllArgsConstructor
public class SocialLoginEventListener implements TypeEventListener<UserSocialLoginTokenRequested> {

    @Produce("{{events.origin.user-login-social-requested}}")
    private ProducerTemplate template;


    @Override
    @EventListener
    public void processEvent(UserSocialLoginTokenRequested userSocialLoginTokenRequested) {
        System.out.println("userSocialLoginTokenRequested = "+userSocialLoginTokenRequested.toString());
        template.sendBodyAndHeaders(template.getDefaultEndpoint(),
                userSocialLoginTokenRequested.getTokenPayload(),
                Collections.emptyMap());
    }
}
