package com.api.authbase.listener;

import com.api.authbase.domain.parse.UserAuthCreatedMessageParser;
import com.api.authbase.event.UserAuthCreated;
import lombok.AllArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Collections;

@Component
@AllArgsConstructor
public class UserEmailCommandListener implements TypeEventListener<UserAuthCreated> {

    @Produce("{{command.origin.user-auth-send-email}}")
    private ProducerTemplate template;

    @TransactionalEventListener
    @Override
    public void processEvent(UserAuthCreated event) {
        template.sendBodyAndHeaders(template.getDefaultEndpoint(),
                new UserAuthCreatedMessageParser(event).asDTO(),
                Collections.emptyMap());
    }
}
