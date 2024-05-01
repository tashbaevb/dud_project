package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.listening.ListeningDto;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
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
    public ResponseEntity<ListeningDto> createListening(@PathVariable Integer lessonId,
                                                        @RequestParam("file") MultipartFile file,
                                                        @RequestParam("title") String title,
                                                        @RequestParam("description") String description) {
        ListeningDto listeningDto = new ListeningDto();
        listeningDto.setTitle(title);
        listeningDto.setDescription(description);
        ResponseEntity<ListeningDto> response = listeningService.createListening(listeningDto, lessonId, file);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/add/questionsAndAnswer/{listeningId}")
    public ResponseEntity<ListeningDto> addQuestionsAndAnswers(@PathVariable Integer listeningId,
                                                               @RequestBody List<ListeningQuestions> questions) {
        ResponseEntity<ListeningDto> response = listeningService.addQuestionsAndAnswers(listeningId, questions);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/check/{listeningId}")
    public ResponseEntity<Integer> checkAnswers(@PathVariable Integer listeningId,
                                                @RequestBody List<AnswerRequest> answers) {
        ResponseEntity<Integer> response = listeningService.checkAnswers(listeningId, answers);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
