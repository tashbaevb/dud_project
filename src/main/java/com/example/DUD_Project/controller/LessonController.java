package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.GrammarResponseDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final GrammarService grammarService;
    private final ListeningService listeningService;
    private final ReadingService readingService;

    @PostMapping("/create")
    public Lesson create(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }


    @GetMapping("/get/grammar/{grammarId}")
    public Object getLesson(@PathVariable Integer grammarId) {
        Grammar grammar = grammarService.getGrammarById(grammarId);

        return grammar != null ? new GrammarResponseDto(grammar.getTitle(), grammar.getDescription()) : ResponseEntity.notFound().build();
    }


    @GetMapping("/get/listening/{listeningId}")
    public ResponseEntity<?> getListening(@PathVariable Integer listeningId) {
        Listening listening = listeningService.getListening(listeningId);

        return listening != null ? ResponseEntity.ok().body(listening) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get/reading/{readingId}")
    public ResponseEntity<?> getReading(@PathVariable Integer readingId) {
        Reading reading = readingService.getReading(readingId);

        return reading != null ? ResponseEntity.ok().body(reading) : ResponseEntity.notFound().build();
    }
}
