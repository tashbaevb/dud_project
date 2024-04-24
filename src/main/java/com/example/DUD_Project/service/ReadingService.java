package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.Reading;
import com.example.DUD_Project.entity.ReadingQuestions;
import com.example.DUD_Project.entity.listening.AnswerRequest;

import java.util.List;

public interface ReadingService {

    Reading createReading(Integer lessonId, Reading reading);

    Reading addQuestionsAndAnswers(Integer readingId, List<ReadingQuestions> questions);

    Reading getReading(Integer readingId);

    int checkAnswers(Integer readingId, List<AnswerRequest> answers);
}
