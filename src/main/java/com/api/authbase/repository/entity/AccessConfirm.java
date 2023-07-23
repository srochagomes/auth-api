package com.api.authbase.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_ACCESS_CONFIRM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessConfirm {

    @Id
    @Column(name = "ID_KEY")
    private UUID key;

    @ManyToOne
    @JoinColumn(name = "ID_USER_AUTH")
    private UserAuth userAuth;

    @Column(name = "DT_HR_CREATED")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "DT_HR_CONFIRMED")
    private LocalDateTime confirmedAt;

    public boolean isConfirmed() {
        return Objects.nonNull(this.confirmedAt);
    }

    public void registerConfirmation() {
        this.confirmedAt = LocalDateTime.now();
        this.getUserAuth().setEmailVerified(true);
    }
}
