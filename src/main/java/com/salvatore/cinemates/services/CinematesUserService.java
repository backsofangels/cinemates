package com.salvatore.cinemates.services;

import com.salvatore.cinemates.dao.CinematesUserRepository;
import com.salvatore.cinemates.model.CinematesUser;
import com.salvatore.cinemates.utils.SaveEntityResultState;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CinematesUserService {
    @Autowired
    private CinematesUserRepository repository;

    private Logger logger = LoggerFactory.getLogger(CinematesUserService.class);

    public SaveEntityResultState saveNewUser(CinematesUser user) {
        if (user != null) {
            Optional<CinematesUser> queryResult = repository.findByEmail(user.getEmail());
            if (queryResult.isPresent()) {
                return SaveEntityResultState.ALREADY_PRESENT;
            } else {
                repository.save(user);
                return SaveEntityResultState.SAVED;
            }
        } else return SaveEntityResultState.NOT_SAVED;
    }
}
