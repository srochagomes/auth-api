package com.api.authbase.repository.entity;

import com.api.authbase.domain.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "TB_USER_AUTH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth {
    @Id
    @Column(name = "ID_KEY")
    private UUID key;

    @Column(name = "ID_APPLICATION")
    private String applicationId;

    @Column(name = "DS_LOGIN_USER")
    private String userLogin;

    @Column(name = "ID_ACCOUNT")
    private UUID accountId;

    @Column(name = "DT_HR_CREATED")
    private LocalDateTime createdAt;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "FL_TERM_ACCEPT")
    private Boolean termAccept;

    @Column(name = "FL_EMAIL_VERIFIED")
    private Boolean emailVerified;


    @Enumerated(EnumType.STRING)
    @Column(name = "EN_STATUS")
    private UserStatus status;

    @Column(name = "DS_USER_ADDRESS_PROVIDER")
    private String userProviderUrl;


    public String extractKeyFromUserProviderUrl(){

        Pattern pattern = Pattern.compile("\\/([^\\/]+)$");
        Matcher matcher = pattern.matcher(this.getUserProviderUrl());

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

}
