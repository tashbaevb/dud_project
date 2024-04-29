package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.Lesson;

public interface LessonService {

    Lesson createLesson(Lesson lesson, Integer levelId);
}
