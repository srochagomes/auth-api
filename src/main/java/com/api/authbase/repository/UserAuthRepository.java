package com.api.authbase.repository;


import com.api.authbase.repository.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    Optional<UserAuth> findUserAuthByApplicationIdAndUserLogin(String clientId, String username);
}
