package br.com.api.authbase.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="credential.default")
@Data
public class ClientCredentialDefault {
    public static final String GRANT_TYPE = "password";
    private String id;
    private String secret;

}
