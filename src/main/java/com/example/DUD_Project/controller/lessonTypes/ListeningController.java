package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import com.example.DUD_Project.entity.lessonTypes.listening.ListeningQuestions;
import com.example.DUD_Project.service.lessonTypes.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listen")
public class ListeningController {

    private final ListeningService listeningService;

    @PostMapping("/create/{lessonId}")
    public ResponseEntity<Listening> createListening(@PathVariable Integer lessonId, @RequestParam("file") MultipartFile file,
                                                     @RequestParam("title") String title, @RequestParam("description") String description) {
        Listening listening = new Listening();
        listening.setTitle(title);
        listening.setDescription(description);
        Listening createdListening = listeningService.createListening(lessonId, listening, file);
        return ResponseEntity.ok().body(createdListening);
    }

    @PostMapping("/add/questionsAndAnswer/{listeningId}")
    public Listening addQuestionsAndAnswers(@PathVariable Integer listeningId, @RequestBody List<ListeningQuestions> questions) {
        return listeningService.addQuestionsAndAnswers(listeningId, questions);
    }


    @PostMapping("/check/{listeningId}")
    public int checkAnswers(@PathVariable Integer listeningId, @RequestBody List<AnswerRequest> answers) {
        return listeningService.checkAnswers(listeningId, answers);
    }
}
