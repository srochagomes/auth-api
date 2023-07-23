package com.api.authbase.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CredentialDTO {

    private Long createdDate;

    private String credentialData;

    private String id;

    private Integer priority;

    private String secretData;

    private Boolean temporary;

    private String optional;

    private String userLabel;

    private String value;

}
