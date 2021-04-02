package com.salvatore.cinemates.services;

import com.salvatore.cinemates.dao.CinematesUserRepository;
import com.salvatore.cinemates.model.CinematesUser;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CinematesUserService {
    @Autowired
    private CinematesUserRepository repository;

    private Logger logger = LoggerFactory.getLogger(CinematesUserService.class);

    public enum SearchMode {
        EMAIL,
        ID,
        USERNAME;
    }

    public boolean saveNewUser(CinematesUser user) {
        if (user != null) {
            try {
                repository.save(user);
            } catch (DataIntegrityViolationException exception) {
                logger.error(ExceptionUtils.getMessage(exception));
                return false;
            }
            logger.debug("Saving user " + user.toString());
            return true;
        } else return false;
    }

    public CinematesUser findUser (String searchableField, SearchMode searchMode) {
        if (searchableField != null) {
            Optional<CinematesUser> queryResult;
            switch (searchMode) {
                case EMAIL:
                    queryResult = repository.findByEmail(searchableField);
                    break;
                case USERNAME:
                    queryResult = repository.findByUsername(searchableField);
                    break;
                case ID:
                    try {
                        queryResult = repository.findByUserId(Long.parseLong(searchableField));
                    } catch (NumberFormatException exception) {
                        logger.error(ExceptionUtils.getMessage(exception));
                        queryResult = Optional.empty();
                    }
                    break;
                default:
                    queryResult = Optional.empty();
                    break;
            }
            return queryResult.orElse(null);
        } else return null;
    }

    public boolean updateCinematesUser(CinematesUser user) {
        if (user == null) {
            return false;
        }
        CinematesUser persistedUser = findUser(user.getUsername(), SearchMode.USERNAME);

        if (persistedUser != null) {
            persistedUser.setName(user.getName());
            persistedUser.setBirthDate(user.getBirthDate());
            persistedUser.setNationality(user.getNationality());
            persistedUser.setPreferredLanguage(user.getPreferredLanguage());
            persistedUser.setSurname(user.getSurname());
            return saveNewUser(persistedUser);
        } else return false;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    /*
        La classe deve essere un servizio di supporto ed astrazione dalla repository utente nelle operazioni di persistenza

        Deve provvvedere a
            1. Salvare senza sovrascrivere entità (e.g. ho entità S1, voglio salvare S2 uguale ad S1
                S1 non deve essere sovrascritta)
            2. Fornire un update reliable delle entità
                E.g. S1 cambia, passo S1, effettua controlli, aggiorna S1
            3. Fornire una ricerca non prona ad eccezioni
                e.g. cerco S1, ritorna un optional, effettua controllo ed eventualmente torna l'entità presente
     */
}
