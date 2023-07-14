package com.api.authbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationDetailDTO {
    private String emailTo;
    private String nameTo;
    private String subject;
    private String urlConfirmation;
}
