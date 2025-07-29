package com.anilbolat.nordicweather.security.auth;

public class AlreadyAuthenticatedException extends RuntimeException {
  
    public AlreadyAuthenticatedException(String message) {
        super(message);
    }
}
