package com.api.authbase.configuration;

import com.api.authbase.domain.dto.KeyCloakAuthbase;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "keycloack-service",
        url = "${keycloak.realm.url}",
        configuration = {KeyCloakAuthClient.Configuration.class})
public interface KeyCloakAuthClient {

    @PostMapping(value = "${keycloak.aplication.token.url}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> processAuthMicrosservices(@RequestAttribute KeyCloakAuthbase authbaseDTO);

    @GetMapping(value = "${keycloak.aplication.session.url}",produces = "application/jwt")
    public ResponseEntity<String> getSession(@RequestHeader HttpHeaders headers);


    class Configuration {
        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }


}