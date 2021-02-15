package com.salvatore.cinemates.dao;

import com.salvatore.cinemates.model.CinematesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinematesUserRepository extends JpaRepository<CinematesUser, Long> {
    CinematesUser findByUsername(String username);
}
