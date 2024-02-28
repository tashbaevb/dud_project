package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.UserDto;
import com.example.DUD_Project.entity.User;
import com.example.DUD_Project.mappers.UserMapper;
import com.example.DUD_Project.security.JwtUtil;
import com.example.DUD_Project.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserMapper mapper;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDto userDto) {
        if (authService.isPresentEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "User with email: " + userDto.getEmail() + " already exist "));
        }

        User user = mapper.convertToEntity(userDto);
        authService.register(user);

        String accessToken = jwtUtil.generateToken(userDto.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(userDto.getEmail());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestBody UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            if (authentication.isAuthenticated()) {
                String accessToken = jwtUtil.generateToken(userDto.getEmail());
                String refreshToken = jwtUtil.generateRefreshToken(userDto.getEmail());

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);

                return ResponseEntity.ok(tokens);
            } else {
                throw new UsernameNotFoundException("incorrect email or password");
            }
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Incorrect email or password");
        }
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam String email) {
        return authService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public String updatePassword(@PathVariable String resetToken, @RequestParam String password) {
        return authService.saveNewPassword(resetToken, password);
    }
}
