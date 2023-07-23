package com.api.authbase.domain.dto.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private Long createdTimestamp;
    private String serviceAccountClientId;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;
    private List<CredentialRepresentationDTO> credentials;

}
