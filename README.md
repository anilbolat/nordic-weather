# Nordic Weather

## Overview

**Nordic Weather** is a secure, rate-limited weather data API built with **Spring Boot**. 
It fetches weather data from the [Visual Crossing API](https://www.visualcrossing.com/weather-api), 
supports **JWT-based authentication**, uses **Redis** for caching, and applies **Bucket4j** for rate limiting.

This project demonstrates how to securely integrate third-party APIs into a Spring application with proper exception handling,
rate limiting, caching strategies, and user authentication.

---

## 🚀 Features

- Fetch weather data via third-party API (Visual Crossing)
- JWT-based user registration and login
- Redis-backed caching
- Bucket4j rate limiting
- Exception handling
- Configurable with environment variables or properties
- Clean architecture using Spring Boot best practices

---

## 🛠️ Technologies Used

- Java
- Spring (Spring Boot, Spring Security)
- Maven
- Bucket4j (rate limiting)
- Redis (cache)
- PostgreSQL (database)
- JWT (authentication)

---

## ⚙️ Getting Started

### Prerequisites

- Java
- Maven
- Redis server
- PostgreSQL server
- A Visual Crossing API key

---

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/anilbolat/nordic-weather.git
   cd nordic-weather
   ```

2. Install dependencies:
    ```sh
    mvn clean install
    ```

3. Set your weather API key into environment variables or edit application.properties:
    ```yaml
    weather.api.key=${WEATHER_API_KEY:your_weather_api_key}
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run  # mvn org.springframework.boot:spring-boot-maven-plugin:3.3.5:run
    ```

## API Endpoints

### Authentication

#### Register
- **URL:** `POST /api/v1/auth/register`
- **Request Body:**
    ```json
    {
      "email": "exampleEmail",
      "password": "examplePassword",
      "firstName": "exampleFirstName",
      "lastName": "exampleLastName"
    }
    ```
- **Response:**
   - `201 Created`: User successfully registered
   - `400 Bad Request`: Invalid input or Already authenticated


#### Login
- **URL:** `POST /api/v1/auth/login`
- **Request Body:**
    ```json
    {
      "email": "exampleEmail",
      "password": "examplePassword"
    }
    ```
- **Response:**
   - `200 OK`: Returns a JWT
   - `401 Unauthorized`: Invalid credentials


### Get Weather

- **URL:** `/api/v1/weather`
- **Method:** `GET`
- **Query Parameters:**
   - `location` (required): e.g. `Tampere`
   - `date` (required): `2025-08-14` (format: yyyy-MM-dd)
- **Response:**
   - `200 OK`: Returns the weather data.
   - `400 Bad Request`: Invalid location or date.
   - `429 Too Many Requests`: Rate limit exceeded.
   - `503 Service Unavailable`: Service not available.

Example request:
```sh
curl -X GET "http://localhost:8080/api/v1/weather?location=Tampere&date=2025-08-14"
```

## 🚫 Exception Handling
- 400 Bad Request: When the request to the weather API is malformed.
- 401 Unauthorized (invalid or missing token)
- 429 Too Many Requests: When the request rate limit is exceeded.
- 503 Service Unavailable: When the weather API or cache is not available.

## Rate Limiting
- The application uses [Bucket4j](https://github.com/bucket4j/bucket4j) for rate limiting. 
The default configuration allows 30 requests per minute.

## 🧩 Roadmap
- Add OpenAPI doc
- Implement /locations to show user's saved locations after login
- Implement refresh token
- Add testcontainers-based integration tests

## References
- [Weather API Wrapper Service Roadmap](https://roadmap.sh/projects/weather-api-wrapper-service)
- [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api)

