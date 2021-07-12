package br.com.rd.authbase.configuration;

import br.com.rd.authbase.domain.dto.AuthbaseDTO;
import br.com.rd.authbase.domain.dto.KeyCloakAuthbase;
import feign.Headers;
import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "keycloack-service",
        url = "http://localhost:7888/auth/realms",
        configuration = {KeyCloakAuthClient.Configuration.class})
public interface KeyCloakAuthClient {

    @PostMapping(value = "/poc_realm/protocol/openid-connect/token",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> processAuthMicrosservices(@RequestAttribute KeyCloakAuthbase authbaseDTO);


    class Configuration {
        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }


}