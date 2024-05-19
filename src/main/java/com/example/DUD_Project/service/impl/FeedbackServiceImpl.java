package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Feedback;
import com.example.DUD_Project.entity.user.User;
import com.example.DUD_Project.repository.FeedbackRepository;
import com.example.DUD_Project.repository.UserRepository;
import com.example.DUD_Project.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;


    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(("User not found")));
    }

    private User getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User not authenticated");
        }
        String email = authentication.getName();

        return getUserByEmail(email);
    }

    @Override
    public ResponseEntity<Feedback> createFeedback(Feedback feedback, Authentication authentication) {
        User user = getCurrentUser(authentication);
        feedback.setUser(user);
        Feedback createdFeedback = feedbackRepository.save(feedback);

        return ResponseEntity.ok(createdFeedback);
    }

    @Override
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackRepository.findAll());
    }
}
