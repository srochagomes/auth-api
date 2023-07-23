package com.api.authbase.configuration;

import com.api.authbase.domain.dto.AuthbaseDTO;
import com.api.authbase.domain.dto.TokenDTO;
import com.api.authbase.domain.dto.provider.UserDTO;
import com.api.authbase.service.AuthbaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import jakarta.websocket.server.PathParam;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "keycloack-admin-service",
        url = "${keycloak.admin.realm.url}",
        configuration = {KeyCloakAdminClient.Configuration.class})
public interface KeyCloakAdminClient {

    @PostMapping(value = "${keycloak.admin.user.url}",consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<String> createUser(@RequestBody UserDTO user);

    @PutMapping(value = "${keycloak.admin.user.execute-actions.url}",consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<String> sendEmailExecuteActionsToUser( @PathVariable ("id") String id,
                                                  @RequestParam(value = "client_id",required = false) String clientId,
                                                  @RequestParam(value = "lifespan",required = false) String lifespan,
                                                  @RequestParam(value = "redirect_uri",required = false) String redirectUri,
                                                  @RequestBody String body);

    @PutMapping(value = "${keycloak.admin.user.verify-email.url}",consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<String> sendEmailVerifyToUser(@PathVariable ("id") String id,
                                                          @RequestParam(value = "client_id",required = false) String clientId,
                                                          @RequestParam(value = "redirect_uri",required = false) String redirectUri);

    @PutMapping(value = "${keycloak.admin.user.logout.url}",consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<String> logoutUser( @PathParam ("id") String id);

    @PutMapping(value = "${keycloak.admin.user.by-id.url}",consumes = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<?> userUpdate(@PathVariable ("id") UUID id,
                                 @RequestBody UserDTO body);




    class Configuration {

        private TokenDTO token;

        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {

            return new SpringFormEncoder(new SpringEncoder(converters));
        }

        @Bean
        public RequestInterceptor requestInterceptor(
                ObjectMapper mapper,
                @Value("${keycloak.admin.profile.client-id}")String clientId,
                @Value("${keycloak.admin.profile.secret}") String secret,
                @Value("${keycloak.admin.profile.username}") String username,
                @Value("${keycloak.admin.profile.password}") String password,
                AuthbaseService service) {



                return requestTemplate -> {
                    extracted(mapper, clientId, secret, username, password, service);
                    requestTemplate.header("Authorization", "Bearer "+this.token.getAccessToken());
                };
        }

        private void extracted(ObjectMapper mapper, String clientId, String secret, String username, String password, AuthbaseService service){
            try{
                if (ObjectUtils.allNull(this.token) || this.token.isExpired()){
                    AuthbaseDTO authBaseDto = AuthbaseDTO.builder()
                            .granttype("password")
                            .clientId(clientId)
                            .secret(secret)
                            .username(username)
                            .password(password)
                            .scope("roles")
                            .build();
                    ResponseEntity<String> resp = service.userAuthentication(authBaseDto);
                    this.token = mapper.readValue(resp.getBody(), TokenDTO.class);
                }

            }catch (Exception e){
                throw new RuntimeException("Erro ao tentar recuperar o access token Admin", e);
            }

        }


    }


}