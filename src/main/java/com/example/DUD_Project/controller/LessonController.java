package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/create")
    public Lesson create(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @GetMapping("/get/{lessonId}")
    public Lesson findById(@PathVariable Integer lessonId) {

        return lessonService.findById(lessonId);
    }
}
