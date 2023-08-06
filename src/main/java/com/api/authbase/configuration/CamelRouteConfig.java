package com.api.authbase.configuration;


import com.api.authbase.domain.dto.TokenDTO;
import com.api.authbase.domain.dto.UserAccessConfirmedDTO;
import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.listener.UserCreatedEventListener;
import com.api.authbase.service.AdminProviderService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CamelRouteConfig extends RouteBuilder {

    private UserCreatedEventListener userCreatedEventListener;
    private AdminProviderService adminProviderService;

    @Override
    public void configure() throws Exception {
        var userAccountCreatedDataFormat = new JacksonDataFormat(UserAccountCreatedDTO.class);
        var userAccessConfirmedDataFormat = new JacksonDataFormat(UserAccessConfirmedDTO.class);
        var userLoginSocialDataFormat = new JacksonDataFormat(TokenDTO.class);
        userAccountCreatedDataFormat.addModule(new JavaTimeModule());
        userAccessConfirmedDataFormat.addModule(new JavaTimeModule());
        userLoginSocialDataFormat.addModule(new JavaTimeModule());

        from("{{events.origin.rabbit-mq.user-created}}")
                .log("recebendo mensagem do RabbitMQ: ${body}")
                .unmarshal(userAccountCreatedDataFormat)
                .bean(userCreatedEventListener,"processEvent")
                                .end();

        from("{{command.origin.user-auth-send-email}}")
                .marshal(userAccountCreatedDataFormat)
                .log("Enviando mensagem para o RabbitMQ: ${body}")
                .to("{{command.destiny.rabbit-mq.user-auth-send-email")
                .end();

        from("{{command.origin.user-access-confirmed}}")
                .log("Enviando mensagem para o RabbitMQ: ${body}")
                .bean(adminProviderService,"confirmEmailVerified")
                .marshal(userAccessConfirmedDataFormat)
                .to("{{command.destiny.rabbit-mq.user-access-confirmed")
                .end();


        from("{{events.origin.user-login-social-requested}}")
                .marshal(userLoginSocialDataFormat)
                .log("Enviando mensagem para o RabbitMQ login-social: ${body}")
                .to("{{events.destiny.rabbit-mq.user-login-social-requested")
                .end();

//        from("{{events.origin.rabbit-mq.user-login-social-requested}}")
//                .log("Enviando mensagem para o RabbitMQ: ${body}")
//                .marshal(userLoginSocialDataFormat)
//                //.bean(adminProviderService,"confirmEmailVerified")
//                .end();


    }
}
