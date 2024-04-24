package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.service.lessonTypes.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reading")
public class ReadingController {

    private final ReadingService readingService;

    @PostMapping("/create/{lessonId}")
    public Reading createReading(@PathVariable Integer lessonId, @RequestBody Reading reading) {
        return readingService.createReading(lessonId, reading);
    }

    @PostMapping("/add/questionsAndAnswer/{readingId}")
    public Reading addQuestionsAndAnswers(@PathVariable Integer readingId, @RequestBody List<ReadingQuestions> questions) {
        return readingService.addQuestionsAndAnswers(readingId, questions);
    }

    @PostMapping("/check/{readingId}")
    public int checkAnswers(@PathVariable Integer readingId, @RequestBody List<AnswerRequest> answers) {
        return readingService.checkAnswers(readingId, answers);
    }
}
