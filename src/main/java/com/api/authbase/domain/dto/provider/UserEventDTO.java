package com.api.authbase.domain.dto.provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEventDTO {

    private UUID id;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSSSS", timezone = "America/Sao_Paulo")
    private LocalDateTime createAt;

    private String event;


    private UserDTO user;


    private String oldState;


    private String newState;


}