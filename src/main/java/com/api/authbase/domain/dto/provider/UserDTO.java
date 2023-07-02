package com.api.authbase.domain.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private Long createdTimestamp;
    private String serviceAccountClientId;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;

}
