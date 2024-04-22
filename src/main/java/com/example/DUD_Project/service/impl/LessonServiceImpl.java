package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }
}
