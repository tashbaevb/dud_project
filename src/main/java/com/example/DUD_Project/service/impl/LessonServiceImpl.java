package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.dto.LessonDto;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.mappers.LessonMapper;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LevelRepository levelRepository;
    private final LessonMapper lessonMapper;

    private Level getLevelById(Integer levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new IllegalArgumentException("Level not found"));
    }

    @Override
    public ResponseEntity<LessonDto> createLesson(LessonDto lessonDto, Integer levelId) {
        Level level = getLevelById(levelId);
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        lesson.setLevel(level);

        Lesson savedLesson = lessonRepository.save(lesson);
        LessonDto savedLessonDto = lessonMapper.toDto(savedLesson);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLessonDto);
    }


    @Override
    public ResponseEntity<List<LessonDto>> getLessonsByLevel(Integer levelId) {
        Level level = getLevelById(levelId);
        List<Lesson> lessons = lessonRepository.findByLevel(level);

        return ResponseEntity.ok(lessonMapper.toDtoList(lessons));
    }
}

