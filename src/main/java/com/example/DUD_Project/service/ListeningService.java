package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.AnswerRequest;
import com.example.DUD_Project.entity.Listening;
import com.example.DUD_Project.entity.ListeningQuestions;

import java.util.List;

public interface ListeningService {

    Listening createListening(Integer lessonId, Listening listening);

    Listening addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions);

    Listening getListening(Integer listeningId);

    int checkAnswers(Integer listeningId, List<AnswerRequest> answers);
}
