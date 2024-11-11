package com.anilbolat.nordicweather.client;

import com.anilbolat.nordicweather.client.exception.WeatherAPINotAuthorized;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class WeatherAPIResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        switch (response.getStatusCode().value()) {
            case 400:
                printError(response);
                break;
            case 401:
                throw new WeatherAPINotAuthorized(response.getBody());
            case 404:
                printError(response);
                break;
            case 429:
                printError(response);
                break;
            case 500:
                printError(response);
                break;
        }
    }

    // remove all above done
    private static void printError(ClientHttpResponse response) throws IOException {
        System.out.println(response.getStatusCode().value());
        System.out.println(response.getStatusText());
        System.out.println(new String(response.getBody().readAllBytes()));
        throw new RuntimeException("implement me");
    }

}
