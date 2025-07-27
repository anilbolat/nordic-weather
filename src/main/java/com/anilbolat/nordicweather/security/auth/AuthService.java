package com.anilbolat.nordicweather.security.auth;

import com.anilbolat.nordicweather.security.config.JwtService;
import com.anilbolat.nordicweather.security.user.User;
import com.anilbolat.nordicweather.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterRequest registerRequest) {
        Optional<User> userExist = userRepository.findByEmail(registerRequest.getEmail());
        if (userExist.isPresent()) {
            var msg = String.format("User [%s] already exists", registerRequest.getEmail());
            throw new UserAlreadyExistsException(msg);
        }

        User u = User.builder()
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        userRepository.save(u);
    }

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User u = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return jwtService.generateToken(u);
    }
}
