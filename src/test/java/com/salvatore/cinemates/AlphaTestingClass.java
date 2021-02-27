package com.salvatore.cinemates;

import static org.assertj.core.api.Assertions.assertThat;

import com.salvatore.cinemates.controller.MovieSearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlphaTestingClass {
    @Autowired
    private MovieSearchController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
