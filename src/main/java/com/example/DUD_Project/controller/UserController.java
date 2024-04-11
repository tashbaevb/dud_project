package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.UserDto;
import com.example.DUD_Project.dto.UserResponseDto;
import com.example.DUD_Project.entity.User;
import com.example.DUD_Project.mappers.UserMapper;
import com.example.DUD_Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping("/my-profile")
    public @ResponseBody UserResponseDto myProfile(Authentication authentication) {
        return userService.getUserProfile(authentication);
    }


    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto, Authentication authentication) {
        User user = userMapper.convertToEntity(userDto);
        User updatedUser = userService.update(user, authentication);

        return userMapper.convertToDto(updatedUser);
    }


    @DeleteMapping("/delete")
    public void delete(Authentication authentication) {
        userService.delete(authentication);
    }
}
