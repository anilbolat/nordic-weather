package com.anilbolat.nordicweather;

import com.anilbolat.nordicweather.weather.cache.CacheNotAvailableException;
import com.anilbolat.nordicweather.weather.client.exception.WeatherAPIBadRequestException;
import com.anilbolat.nordicweather.weather.client.exception.WeatherAPINotAvailable;
import com.anilbolat.nordicweather.ratelimiting.RateLimitExceededException;
import com.anilbolat.nordicweather.security.auth.AlreadyAuthenticatedException;
import com.anilbolat.nordicweather.security.auth.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ WeatherAPINotAvailable.class, CacheNotAvailableException.class })
    public ResponseEntity<ErrorMessage> handleServiceNotAvailable(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorMessage("We cannot serve at the moment. Please try again later."));
    }
    
    @ExceptionHandler(WeatherAPIBadRequestException.class)
    public ResponseEntity<ErrorMessage> handleWeatherAPIBadRequestException(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Incorrect location or date."));
    }
    
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorMessage> handleRateLimitExceededException(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorMessage("Too many requests! Please try again later."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity.
                status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(Exception ex) {
        log.error(ex.getMessage());

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(AlreadyAuthenticatedException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyAuthenticatedException(Exception ex) {
        log.warn(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(ex.getMessage()));
    }

    public record ErrorMessage(String message) {
    }

}
