package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.lessonTypes.GrammarResponseDto;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import com.example.DUD_Project.service.LessonService;
import com.example.DUD_Project.service.lessonTypes.ListeningService;
import com.example.DUD_Project.service.lessonTypes.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final GrammarService grammarService;
    private final ListeningService listeningService;
    private final ReadingService readingService;

    @PostMapping("/create/{levelId}")
    public Lesson create(@RequestBody Lesson lesson, @PathVariable Integer levelId) {
        return lessonService.createLesson(lesson, levelId);
    }

    @GetMapping("/getAllByLevel/{levelId}")
    public ResponseEntity<List<Lesson>> getLessonsByLevel(@PathVariable Integer levelId) {
        return lessonService.getLessonsByLevel(levelId);
    }

    @GetMapping("/get/{lessonId}/grammar")
    public ResponseEntity<?> getGrammar(@PathVariable Integer lessonId) {
        Grammar grammar = grammarService.getGrammarByLessonId(lessonId);
        return grammar != null ? ResponseEntity.ok().body(grammar) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{lessonId}/listening")
    public ResponseEntity<?> getListening(@PathVariable Integer lessonId) {
        Listening listening = listeningService.getListeningByLessonId(lessonId);
        return listening != null ? ResponseEntity.ok().body(listening) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{lessonId}/reading")
    public ResponseEntity<?> getReading(@PathVariable Integer lessonId) {
        Reading reading = readingService.getReadingByLessonId(lessonId);
        return reading != null ? ResponseEntity.ok().body(reading) : ResponseEntity.notFound().build();
    }
}
