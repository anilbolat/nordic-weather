package com.anilbolat.nordicweather.endpoint;

import com.anilbolat.nordicweather.cache.CacheNotAvailableException;
import com.anilbolat.nordicweather.client.exception.WeatherAPIBadRequestException;
import com.anilbolat.nordicweather.client.exception.WeatherAPINotAvailable;
import com.anilbolat.nordicweather.endpoint.ratelimiting.RateLimitExceededException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class EndpointExceptionHandler {
    
    @ExceptionHandler({ WeatherAPINotAvailable.class, CacheNotAvailableException.class })
    public ResponseEntity<ErrorMessage> handleServiceNotAvailable(Exception ex) {
        log.error(ex.getMessage(), ex);
        
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorMessage("We cannot serve at the moment. Please try again later."));
    }
    
    @ExceptionHandler(WeatherAPIBadRequestException.class)
    public ResponseEntity<ErrorMessage> handleWeatherAPIBadRequestException(Exception ex) {
        log.error(ex.getMessage(), ex);
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Incorrect location or date."));
    }
    
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorMessage> handleRateLimitExceededException(Exception ex) {
        log.error(ex.getMessage(), ex);
        
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorMessage("Too many requests! Please try again later."));
    }

    public record ErrorMessage(String message) {
    }
    
}
