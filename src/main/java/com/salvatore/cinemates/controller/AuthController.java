package com.salvatore.cinemates.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.salvatore.cinemates.dao.CinematesUserRepository;
import com.salvatore.cinemates.dto.CinematesUserAuthDto;
import com.salvatore.cinemates.model.CinematesUser;
import com.salvatore.cinemates.model.ErrorResponse;
import com.salvatore.cinemates.services.UserDetailsServiceImpl;
import com.salvatore.cinemates.utils.JwtConstants;
import com.salvatore.cinemates.utils.JwtTokenUtils;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class AuthController {
    @Autowired
    private CinematesUserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtils tokenUtils;

    @Bean
    public BCryptPasswordEncoder createEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody CinematesUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return ResponseEntity.status(409).body(new ErrorResponse(409, "Conflict", "Entity already exists"));
        }
        return ResponseEntity.ok().build();
    }

    //TODO: creare il token JWT in modo che abbia validità di 10 anni
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody CinematesUserAuthDto authDto, HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDto.getUsername());
        final String token = tokenUtils.generateToken(userDetails.getUsername());
        response.setHeader(JwtConstants.JWT_TOKEN_HEADER, token);
        return ResponseEntity.ok().body(repository.findByUsername(authDto.getUsername()));
    }

    //TODO: Creare un endpoint di logout che invalidi il token JWT dell'utente server-side
}
