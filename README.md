# Nordic Weather

## Overview

Nordic Weather is a Spring Boot application that provides weather data by fetching it from a 3rd party API.
This project demonstrates how to work with 3rd party APIs, caching, rate limiting and environment variables.

## Features

- Fetch weather data from a 3rd party API
- Cache weather data using Redis
- Rate limiting to prevent abuse
- Exception handling for various error scenarios

## Technologies Used

- Java
- Spring Boot
- Redis
- Maven
- Bucket4j (for rate limiting)

# 3rd party weather API
- https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
- Dates should be in the format yyyy-MM-dd. For example 2020-10-19 for October 19th, 2020 or 2017-02-03 for February 3rd, 2017.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Redis server

### Installation

1. Clone the repository:
    ```sh
    git clone git@github.com:anilbolat/nordic-weather.git
    cd nordic-weather
    ```

2. Install dependencies:
    ```sh
    mvn clean install
    ```

3. Set up environment variables:
    - Add your 3rd party API key into resources/application.properties file.


4. Run the application:
    ```sh
    mvn spring-boot:run  # mvn org.springframework.boot:spring-boot-maven-plugin:3.3.5:run
    ```

## API Endpoints

### Get Weather

- **URL:** `/api/v1/weather`
- **Method:** `GET`
- **Query Parameters:**
    - `location` (required): The location for which to fetch the weather.
    - `date` (required): The date for which to fetch the weather (format: yyyy-MM-dd).
- **Response:**
    - `200 OK`: Returns the weather data.
    - `400 Bad Request`: Incorrect location or date.
    - `429 Too Many Requests`: Rate limit exceeded.
    - `503 Service Unavailable`: Service not available.

Example request:
```sh
curl -X GET "http://localhost:8080/api/v1/weather?location=Helsinki&date=2023-10-19"
```

## Exception Handling
- 503 Service Unavailable: When the weather API or cache is not available.
- 400 Bad Request: When the request to the weather API is malformed.
- 429 Too Many Requests: When the request rate limit is exceeded.

## Rate Limiting
- The application uses [Bucket4j](https://github.com/bucket4j/bucket4j) for rate limiting. The current configuration allows 10 requests per minute.

## Remaining Tasks
- Implement JWT authentication
- Tests
  - test containers

## References
- [Weather API Wrapper Service Roadmap](https://roadmap.sh/projects/weather-api-wrapper-service)
- [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api)

