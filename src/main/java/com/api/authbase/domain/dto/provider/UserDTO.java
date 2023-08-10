package com.api.authbase.domain.dto.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
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
    private String id;
    private String emailConstraint;
    private String realmId;
    private int notBefore;
    private List<FederatedIdentityDTO> federateIdentities;

}
