package com.example.DUD_Project.service;

import com.example.DUD_Project.dto.LessonDto;
import com.example.DUD_Project.entity.Lesson;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LessonService {

    ResponseEntity<LessonDto> createLesson(LessonDto lessonDto, Integer levelId);

    ResponseEntity<List<LessonDto>> getLessonsByLevel(Integer levelId);
}
