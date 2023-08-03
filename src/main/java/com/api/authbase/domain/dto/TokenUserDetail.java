package com.api.authbase.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class TokenUserDetail {
        private String sub;
        private ResourceAccess resourceAccess;
        private boolean emailVerified;
        private List<String> allowedOrigins;
        private String iss;
        private String typ;
        private String preferredUsername;
        private String givenName;
        private String sid;
        private List<String> aud;
        private String acr;
        private RealmAccess realmAccess;
        private String azp;
        private String scope;
        private String exp;
        private String sessionState;
        private String iat;
        private String familyName;
        private String jti;
        private String email;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
class ResourceAccess {
    private Account account;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
class Account {
    private List<String> roles;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
class RealmAccess {
    private List<String> roles;

    // Add getters and setters here
}
