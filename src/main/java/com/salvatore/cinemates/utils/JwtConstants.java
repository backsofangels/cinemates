package com.salvatore.cinemates.utils;

public interface JwtConstants {
    String JWT_CONST_SECRET = "cinemates";
    Long JWT_EXPIRY_TIME = 3600L;
    String JWT_TOKEN_PREFIX = "Bearer ";
    String JWT_TOKEN_HEADER = "Authorization";
}
