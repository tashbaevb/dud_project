package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.listening.ListeningDto;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.entity.lessonTypes.listening.ListeningQuestions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ListeningService {

    ResponseEntity<ListeningDto> createListening(ListeningDto listeningDto, Integer lessonId, MultipartFile file);

    ResponseEntity<ListeningDto> addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions);

    ResponseEntity<ListeningDto> getListeningByLessonId(Integer lessonId);

    ResponseEntity<Integer> checkAnswers(Integer listeningId, List<AnswerRequest> answers);
}
