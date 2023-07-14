package com.api.authbase.domain.dto;

import com.api.authbase.domain.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {

    private MessageType type;

    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

    private SpecificationDTO specification;

}
