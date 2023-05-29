package com.api.authbase.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthbaseDTO {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("grant_type")
    private String granttype;

    @JsonProperty("client_secret")
    private String secret;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    private String scope;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @Builder.Default
    private boolean clientDefault = false;

}