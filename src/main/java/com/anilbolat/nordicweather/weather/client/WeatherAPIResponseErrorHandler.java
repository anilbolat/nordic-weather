package com.anilbolat.nordicweather.weather.client;

import com.anilbolat.nordicweather.weather.client.exception.WeatherAPIBadRequestException;
import com.anilbolat.nordicweather.weather.client.exception.WeatherAPINotAvailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WeatherAPIResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    /*
    *
    * 400, 401, 404, 429, 500 status codes are expected from weather api.
    * 
    * */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (HttpStatus.BAD_REQUEST.value() == response.getStatusCode().value()) {
            throw new WeatherAPIBadRequestException(convertStreamToString(response.getBody()));
        } else {
            throw new WeatherAPINotAvailable(convertStreamToString(response.getBody()));
        }
    }

    /*
    * 
    *  response body is short, so reading all once is fine.
    * 
    * */
    private static String convertStreamToString(InputStream inputStream) throws IOException {
        return (inputStream != null) ? new String(inputStream.readAllBytes(), StandardCharsets.UTF_8) : "Response body is empty.";
    }

}
