//package com.anilbolat.nordicweather.grpc.service;
//
//import com.anilbolat.WeatherRequest;
//import com.anilbolat.WeatherResponse;
//import com.anilbolat.WeatherServiceGrpc.WeatherServiceImplBase;
//import com.anilbolat.nordicweather.cache.CacheService;
//import com.anilbolat.nordicweather.client.WeatherAPIClient;
//import io.grpc.stub.StreamObserver;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.grpc.server.service.GrpcService;
//
//
//@Slf4j
//@GrpcService
//public class WeathergRPCServiceImpl extends WeatherServiceImplBase {    
//    
//    private final WeatherAPIClient weatherAPIClient;
//    private final CacheService cacheService;
//
//    public WeathergRPCServiceImpl(WeatherAPIClient weatherAPIClient, CacheService cacheService) {
//        this.weatherAPIClient = weatherAPIClient;
//        this.cacheService = cacheService;
//    }
//
//    @Override
//    public void getWeather(WeatherRequest request, StreamObserver<WeatherResponse> responseObserver) {
//        String location = request.getLocation();
//        String date = request.getDate();
//        
//        String weather = this.cacheService.getFromCache(location, date);
//        if (weather == null) {
//            log.info("Not found in cache, [{}, {}]", location, date);
//            weather = this.weatherAPIClient.getWeather(location, date);
//            this.cacheService.saveToCache(location, date, weather);
//        }
//
//        WeatherResponse weatherResponse = WeatherResponse.newBuilder().setWeather(weather).build();
//        
//        responseObserver.onNext(weatherResponse);
//        responseObserver.onCompleted();
//    }
//}
