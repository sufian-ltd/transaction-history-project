package com.example.transactionhistorybackend.controller;

import com.example.transactionhistorybackend.constants.ApiUrlConstant;
import com.example.transactionhistorybackend.dto.AuthenticationRequest;
import com.example.transactionhistorybackend.dto.AuthenticationResponse;
import com.example.transactionhistorybackend.model.User;
import com.example.transactionhistorybackend.security.JwtTokenUtil;
import com.example.transactionhistorybackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ApiUrlConstant.AUTH)
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(ApiUrlConstant.SIGN_UP)
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok(null);
    }

    @PostMapping(ApiUrlConstant.SIGN_IN)
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest authRequest) {
        Optional<User> userOpt = userService.getByUsername(authRequest.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(authRequest.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtTokenUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
