package com.salvatore.cinemates.utils;

import java.io.Serializable;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.salvatore.cinemates.dto.CinematesUserAuthDto;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils implements Serializable {
    private final transient Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    public String getUsernameFromToken (String token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (JWTDecodeException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public Date getExpirationDateFromToken(String token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt();
        } catch (JWTDecodeException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public CinematesUserAuthDto getUserDetailsFromToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return new CinematesUserAuthDto(decodedJWT.getSubject(), "");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    private Date createExpiryDate() {
        return new Date(System.currentTimeMillis() + JwtConstants.JWT_EXPIRY_TIME);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiry = getExpirationDateFromToken(token);
        return expiry.before(new Date());
    }

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withIssuer("Cinemates Dev")
                .withExpiresAt(createExpiryDate())
                .sign(Algorithm.HMAC256(JwtConstants.JWT_CONST_SECRET.getBytes()));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        CinematesUserAuthDto user = (CinematesUserAuthDto) userDetails;
        final String username = getUsernameFromToken(token);
        return(username.equals(user.getUsername()) && isTokenExpired(token));
    }
}
