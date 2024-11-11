package com.anilbolat.nordicweather.endpoint;

import com.anilbolat.nordicweather.client.exception.WeatherAPINotAuthorized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class EndpointExceptionHandler {
    
    @ExceptionHandler(WeatherAPINotAuthorized.class)
    public ResponseEntity<ErrorMessage> handleWeatherAPINotAuthorized(Exception ex) {
        log.warn(ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage("We cannot serve at the moment. Please try again later."));
    }

    public record ErrorMessage(String message) {
    }
    
}
