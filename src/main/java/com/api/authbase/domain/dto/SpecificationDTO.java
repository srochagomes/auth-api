package com.api.authbase.domain.dto;

import com.api.authbase.domain.enums.DomainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationDTO {

    private DomainType domainType;
    private UUID accountId;
    private SpecificationDetailDTO detail;
}
