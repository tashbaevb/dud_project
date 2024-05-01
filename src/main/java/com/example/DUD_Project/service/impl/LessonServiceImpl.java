package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LevelRepository levelRepository;

    private Level getLevelById(Integer levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new IllegalArgumentException("Level not found"));
    }

    @Override
    public Lesson createLesson(Lesson lesson, Integer levelId) {
        Level level = getLevelById(levelId);
        lesson.setLevel(level);

        return lessonRepository.save(lesson);
    }


    @Override
    public ResponseEntity<List<Lesson>> getLessonsByLevel(Integer levelId) {
        Level level = getLevelById(levelId);
        List<Lesson> lessons = lessonRepository.findByLevel(level);

        return ResponseEntity.ok(lessons);
    }
}

