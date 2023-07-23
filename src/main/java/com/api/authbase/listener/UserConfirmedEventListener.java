package com.api.authbase.listener;

import com.api.authbase.domain.parse.UserAccessConfirmParser;
import com.api.authbase.event.UserAccessConfirmed;
import lombok.AllArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Collections;

@Component
@AllArgsConstructor
public class UserConfirmedEventListener implements EventListener<UserAccessConfirmed>{

    @Produce("{{command.origin.user-access-confirmed}}")
    private ProducerTemplate template;

    @TransactionalEventListener
    @Override
    public void processEvent(UserAccessConfirmed event) {
        template.sendBodyAndHeaders(template.getDefaultEndpoint(),
                new UserAccessConfirmParser(event.getPayload()).asDTO(),
                Collections.emptyMap());
    }
}
