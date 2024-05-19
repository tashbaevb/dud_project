package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.user.UpdateRequest;
import com.example.DUD_Project.dto.user.UserDto;
import com.example.DUD_Project.dto.user.UserResponseDto;
import com.example.DUD_Project.entity.Feedback;
import com.example.DUD_Project.entity.user.User;
import com.example.DUD_Project.mappers.UserMapper;
import com.example.DUD_Project.service.FeedbackService;
import com.example.DUD_Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final FeedbackService feedbackService;


    @GetMapping("/my-profile")
    public @ResponseBody UserResponseDto myProfile(Authentication authentication) {
        return userService.getUserProfile(authentication);
    }


    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UpdateRequest updateRequest, Authentication authentication) {
        User user = userMapper.convertToEntity(updateRequest);
        User updatedUser = userService.update(user, updateRequest.getLevelIds(), authentication);
        return ResponseEntity.ok(userMapper.convertToDto(updatedUser));
    }

    @DeleteMapping("/delete")
    public void delete(Authentication authentication) {
        userService.delete(authentication);
    }


    // Feedback
    @PostMapping("/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, Authentication authentication) {
        return feedbackService.createFeedback(feedback, authentication);
    }

    @GetMapping("/getAllFeedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }
}
