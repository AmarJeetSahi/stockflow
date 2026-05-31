package com.stockflow.auth_service.repository;

import com.stockflow.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);
}
