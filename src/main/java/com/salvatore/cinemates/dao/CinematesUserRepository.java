package com.salvatore.cinemates.dao;

import com.salvatore.cinemates.model.CinematesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinematesUserRepository extends JpaRepository<CinematesUser, Long> {
    Optional<CinematesUser> findByUsername(String username);
    Optional<CinematesUser> findByEmail(String email);
    Optional<CinematesUser> findByUserId(Long userId);
}
