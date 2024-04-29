package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.user.Level;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LevelRepository levelRepository;

    @Override
    public Lesson createLesson(Lesson lesson, Integer levelId) {
        Level level = levelRepository.findById(levelId).orElse(null);
        if (level == null) {
            throw new IllegalArgumentException("Level with id " + levelId + " not found");
        }
        lesson.setLevel(level);

        return lessonRepository.save(lesson);
    }
}

