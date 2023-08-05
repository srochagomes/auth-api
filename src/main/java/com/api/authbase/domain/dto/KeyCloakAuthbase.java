package com.api.authbase.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class KeyCloakAuthbase {

    private String grant_type;

    private String client_id;

    private String client_secret;

    private String refresh_token;

    private String username;

    private String password;

    private String code;

    private String redirect_uri;

    private String scope;

}