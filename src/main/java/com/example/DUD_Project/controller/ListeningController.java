package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.Listening;
import com.example.DUD_Project.entity.ListeningQuestions;
import com.example.DUD_Project.service.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listen")
public class ListeningController {

    private final ListeningService listeningService;

    @PostMapping("/create/{lessonId}")
    public Listening createListening(@PathVariable Integer lessonId, @RequestBody Listening listening) {
        return listeningService.createListening(lessonId, listening);
    }

    @PostMapping("/add/questionsAndAnswer/{listeningId}")
    public Listening addQuestionsAndAnswers(@PathVariable Integer listeningId, @RequestBody List<ListeningQuestions> questions) {
        return listeningService.addQuestionsAndAnswers(listeningId, questions);
    }

    @GetMapping("/get/{listeningId}")
    public Listening getListening(@PathVariable Integer listeningId) {
        return listeningService.getListening(listeningId);
    }
}
