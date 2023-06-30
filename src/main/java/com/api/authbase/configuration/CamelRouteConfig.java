package com.api.authbase.configuration;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRouteConfig extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        var jacksonDataFormat = new JacksonDataFormat();
        jacksonDataFormat.addModule(new JavaTimeModule());

        from("{{events.origin.rabbit-mq.user-created}}")
                .unmarshal(jacksonDataFormat)
                .log("recebendo mensagem do RabbitMQ: ${body}")
                .end();
    }
}
