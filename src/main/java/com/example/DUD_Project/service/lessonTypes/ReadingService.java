package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;

import java.util.List;

public interface ReadingService {

    Reading createReading(Integer lessonId, Reading reading);

    Reading addQuestionsAndAnswers(Integer readingId, List<ReadingQuestions> questions);

    Reading getReading(Integer readingId);

    int checkAnswers(Integer readingId, List<AnswerRequest> answers);
}
