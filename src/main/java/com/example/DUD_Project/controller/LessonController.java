package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.GrammarResponseDto;
import com.example.DUD_Project.entity.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Listening;
import com.example.DUD_Project.service.GrammarService;
import com.example.DUD_Project.service.LessonService;
import com.example.DUD_Project.service.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final GrammarService grammarService;
    private final ListeningService listeningService;

    @PostMapping("/create")
    public Lesson create(@RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson);
    }


    @GetMapping("/get/{grammarId}/grammar")
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
}
