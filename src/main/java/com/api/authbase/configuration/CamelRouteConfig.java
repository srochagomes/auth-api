package com.api.authbase.configuration;


import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.listener.UserCreatedEventListener;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CamelRouteConfig extends RouteBuilder {

    private UserCreatedEventListener userCreatedEventListener;

    @Override
    public void configure() throws Exception {
        var jacksonDataFormat = new JacksonDataFormat(UserAccountCreatedDTO.class);
        jacksonDataFormat.addModule(new JavaTimeModule());

        from("{{events.origin.rabbit-mq.user-created}}")
                .log("recebendo mensagem do RabbitMQ: ${body}")
                .unmarshal(jacksonDataFormat)
                .bean(userCreatedEventListener,"processEvent")
                                .end();

        from("{{command.origin.user-auth-send-email}}")
                .marshal(jacksonDataFormat)
                .log("Enviando mensagem para o RabbitMQ: ${body}")
                .to("{{command.destiny.rabbit-mq.user-auth-send-email")
                .end();
    }
}
