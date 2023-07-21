package com.api.authbase.repository;


import com.api.authbase.repository.entity.ApplicationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDataRepository extends JpaRepository<ApplicationData, String> {
    
}
