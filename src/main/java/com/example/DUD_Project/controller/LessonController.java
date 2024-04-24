package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.GrammarResponseDto;
import com.example.DUD_Project.entity.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Reading;
import com.example.DUD_Project.entity.listening.Listening;
import com.example.DUD_Project.service.GrammarService;
import com.example.DUD_Project.service.LessonService;
import com.example.DUD_Project.service.ListeningService;
import com.example.DUD_Project.service.ReadingService;
import lombok.RequiredArgsConstructor;
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
        if (grammar == null) {
            return "Lesson not found";
        }

        return new GrammarResponseDto(grammar.getTitle(), grammar.getDescription());
    }


    @GetMapping("/get/listening/{listeningId}")
    public Listening getListening(@PathVariable Integer listeningId) {
        return listeningService.getListening(listeningId);
    }

    @GetMapping("/get/reading/{readingId}")
    public Reading getReading(@PathVariable Integer readingId) {
        return readingService.getReading(readingId);
    }
}
