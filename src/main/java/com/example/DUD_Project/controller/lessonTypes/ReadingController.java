package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.reading.ReadingDto;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.service.lessonTypes.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reading")
public class ReadingController {

    private final ReadingService readingService;

    @PostMapping("/create/{lessonId}")
    public ResponseEntity<ReadingDto> createReading(@PathVariable Integer lessonId, @RequestBody ReadingDto readingDto) {
        return readingService.createReading(readingDto, lessonId);
    }

    @PostMapping("/add/questionsAndAnswer/{readingId}")
    public ResponseEntity<ReadingDto> addQuestionsAndAnswers(@PathVariable Integer readingId, @RequestBody List<ReadingQuestions> questions) {
        return readingService.addQuestionsAndAnswers(questions, readingId);
    }

    @PostMapping("/check/{readingId}")
    public int checkAnswers(@PathVariable Integer readingId, @RequestBody List<AnswerRequest> answers) {
        return readingService.checkAnswers(answers, readingId);
    }
}
