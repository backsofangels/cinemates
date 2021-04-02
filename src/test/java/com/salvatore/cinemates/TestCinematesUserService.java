package com.salvatore.cinemates;

import static org.junit.jupiter.api.Assertions.*;

import com.salvatore.cinemates.model.CinematesUser;
import com.salvatore.cinemates.services.CinematesUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.client.AutoConfigureMockWebServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockWebServiceServer
class TestCinematesUserService {
    @Autowired
    private CinematesUserService service;

    @AfterEach
    void cleanUp() {
        this.service.deleteAll();
    }

    @Test
    void saveNewUser() {
        CinematesUser user = new CinematesUser("a@test.com", "test", "test");
        assertTrue(service.saveNewUser(user));
    }

    @Test
    void saveNullUser() {
        assertFalse(service.saveNewUser(null));
    }

    @Test
    void saveExistingUser() {
        CinematesUser c1 = new CinematesUser("a@test.com", "test", "test");
        CinematesUser c2 = new CinematesUser("a@test.com", "test", "test");
        assertTrue(service.saveNewUser(c1));
        assertFalse(service.saveNewUser(c2));
    }

    @Test
    void updateExistingUser() {
        CinematesUser c1 = new CinematesUser("a@test.com", "test", "test");
        assertTrue(service.saveNewUser(c1));
        c1.setNationality("Salvatore");
        assertTrue(service.updateCinematesUser(c1));
    }

    @Test
    void updateNonExistingUser() {
        CinematesUser c1 = new CinematesUser("a@test.com", "test", "test");
        assertFalse(service.updateCinematesUser(c1));
    }

    @Test
    void updateNullUser() {
        assertFalse(service.updateCinematesUser(null));
    }
}
