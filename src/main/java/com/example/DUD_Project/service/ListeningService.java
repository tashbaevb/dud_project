package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.listening.AnswerRequest;
import com.example.DUD_Project.entity.listening.Listening;
import com.example.DUD_Project.entity.listening.ListeningQuestions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ListeningService {

    Listening createListening(Integer lessonId, Listening listening, MultipartFile file);

    Listening addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions);

    Listening getListening(Integer listeningId);

    int checkAnswers(Integer listeningId, List<AnswerRequest> answers);
}
