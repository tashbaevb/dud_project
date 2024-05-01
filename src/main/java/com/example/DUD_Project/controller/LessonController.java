package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.LessonDto;
import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import com.example.DUD_Project.dto.lessonTypes.listening.ListeningDto;
import com.example.DUD_Project.dto.lessonTypes.reading.ReadingDto;
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
    public ResponseEntity<LessonDto> create(@RequestBody LessonDto lessonDto, @PathVariable Integer levelId) {
        return lessonService.createLesson(lessonDto, levelId);
    }

    @GetMapping("/getAllByLevel/{levelId}")
    public ResponseEntity<List<LessonDto>> getLessonsByLevel(@PathVariable Integer levelId) {
        return lessonService.getLessonsByLevel(levelId);
    }

    @GetMapping("/get/{lessonId}/grammar")
    public ResponseEntity<GrammarDto> getGrammar(@PathVariable Integer lessonId) {
        return grammarService.getGrammarByLessonId(lessonId);
    }

    @GetMapping("/get/{lessonId}/listening")
    public ResponseEntity<ListeningDto> getListening(@PathVariable Integer lessonId) {
        return listeningService.getListeningByLessonId(lessonId);
    }

    @GetMapping("/get/{lessonId}/reading")
    public ResponseEntity<ReadingDto> getReading(@PathVariable Integer lessonId) {
        return readingService.getReadingByLessonId(lessonId);
    }
}
