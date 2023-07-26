package com.api.authbase.domain.dto;

import com.api.authbase.domain.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserAccessConfirmedDTO {


    private LocalDateTime confirmedAt;

    private UUID key;

    private String applicationId;

    private String userLogin;

    private UUID accountId;

    private LocalDateTime createdAt;

    private String email;

    private UserStatus status;

    @JsonIgnore
    private  CredentialDTO credential;

    private UUID userProviderId;

    public Boolean hasEmailVerified() {
        return Objects.nonNull(this.confirmedAt);
    }
}
