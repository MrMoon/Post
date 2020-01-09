package com.moon.squad.configuration;

import org.springframework.beans.factory.annotation.Value;

public abstract class ConfigurationConstants {
    //Redis
    @Value ("${spring.redis.host}")
    public static final String HOST_NAME = "localhost";
    @Value ("${spring.redis.port}")
    public static final int PORT_NUMBER = 6379;
    public static final String CACHE = "cache";
    //Security
    public static final long VALIDITY_IN_MILLESECONDS = 3600000;
    public static final String SECRET_KEY = "squadsecretkeyIsPrime2";
    public static final String ROLES = "roles";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String UNABLE_TO_GET_TOKEN = "Unable to get JWT Token";
    public static final String EXPIRED_TOKEN = "JWT Token has expired";
    public static final String INVALID_TOKEN_DONT = "JWT Token does not begin with " + TOKEN_PREFIX;
    public static final String INVALID_SIGNATURE = "Invalid JWT signature";
    public static final String INVALID_TOKEN = "Invalid JWT token";
    public static final String INVALID_DATE = "Expired JWT token";
    public static final String INVALID_UNSUPPORTED = "Unsupported JWT token";
    public static final String INVALID_EMPTY = "JWT claims string is empty.";
    public static final String INVALID_NO = "No JWT Token found in this request";
}
