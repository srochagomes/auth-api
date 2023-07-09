package com.api.authbase.listener;

import com.api.authbase.event.UserAuthCreated;
import lombok.AllArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Collections;

@Component
@AllArgsConstructor
public class UserAuthCreatedListener implements EventListener<UserAuthCreated>{

    @Produce("{{command.origin.user-auth-send-email}}")
    private ProducerTemplate template;

    @TransactionalEventListener
    @Override
    public void processEvent(UserAuthCreated event) {
        template.sendBodyAndHeaders(template.getDefaultEndpoint(),event.getPayload(), Collections.emptyMap());
    }
}
