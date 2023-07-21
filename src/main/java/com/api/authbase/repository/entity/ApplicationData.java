package com.api.authbase.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_APPLICATION_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationData {
    @Id
    @Column(name = "ID_APPLICATION")
    private String applicationId;

    @Column(name = "DS_APP_ADDRESS")
    private String address;

}
