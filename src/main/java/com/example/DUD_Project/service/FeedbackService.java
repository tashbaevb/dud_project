package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FeedbackService {

    ResponseEntity<Feedback> createFeedback(Feedback feedback, Authentication authentication);

    ResponseEntity<List<Feedback>> getAllFeedbacks();
}
