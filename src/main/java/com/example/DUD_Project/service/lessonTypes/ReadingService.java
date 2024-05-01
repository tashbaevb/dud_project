package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.reading.ReadingDto;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReadingService {

    ResponseEntity<ReadingDto> createReading(ReadingDto readingDto, Integer lessonId);

    ResponseEntity<ReadingDto> addQuestionsAndAnswers(List<ReadingQuestions> questions, Integer readingId);

    ResponseEntity<ReadingDto> getReadingByLessonId(Integer readingId);

    int checkAnswers(List<AnswerRequest> answers, Integer readingId);
}
