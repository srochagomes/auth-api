package com.api.authbase.repository;


import com.api.authbase.repository.entity.AccessConfirm;
import com.api.authbase.repository.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccessConfirmRepository extends JpaRepository<AccessConfirm, UUID> {

}
