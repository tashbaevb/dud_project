package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.Lesson;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LessonService {

    Lesson createLesson(Lesson lesson, Integer levelId);

    ResponseEntity<List<Lesson>> getLessonsByLevel(Integer levelId);
}
