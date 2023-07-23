package com.api.authbase.domain.dto;

import com.api.authbase.domain.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserAccountDTO {

    private String key;
    private AccountCreatedDTO account;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime dateCreated;

    private String name;

    private String email;

    private String userLogin;

    private Boolean termAccept;


    private Boolean emailVerified;

    private UserStatus status;

    public String getFirstNameByName() {

        if (Objects.nonNull(this.name)){
            String[] split = this.name.split(" ");
            if (split.length > 0){
                return split[0];
            }
        }
        return "";

    }

    public String getLastNameByName() {
        if (Objects.nonNull(this.name)){
            String[] split = this.name.split(" ");
            if (split.length > 1){
                return split[split.length-1];
            }
        }
        return "";
    }
}
