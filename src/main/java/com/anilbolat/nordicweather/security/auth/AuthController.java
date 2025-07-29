package com.anilbolat.nordicweather.security.auth;

import com.anilbolat.nordicweather.security.config.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest req) {
        checkIfAlreadyAuthenticated(req);

        authService.register(registerRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest req) {
        checkIfAlreadyAuthenticated(req);

        return ResponseEntity.ok("{ \"token\" : " + "\"" + authService.login(loginRequest) + "\"}" );
    }

    private void checkIfAlreadyAuthenticated(HttpServletRequest req) {
        final String jwt = jwtService.getJwtFromRequest(req);
        if (jwt != null && !jwtService.isTokenExpired(jwt)) {
            throw new AlreadyAuthenticatedException("Already authenticated.");
        }
    }
}
