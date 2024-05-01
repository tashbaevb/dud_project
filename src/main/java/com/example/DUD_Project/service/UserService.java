package com.example.DUD_Project.service;

import com.example.DUD_Project.dto.user.UserResponseDto;
import com.example.DUD_Project.entity.user.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    User update(User newUser, Authentication authentication);

    void delete(Authentication authentication);

    UserResponseDto getUserProfile(Authentication authentication);
}
