package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Reading;
import com.example.DUD_Project.entity.ReadingQuestions;
import com.example.DUD_Project.entity.listening.AnswerRequest;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.ReadingQuestionsRepository;
import com.example.DUD_Project.repository.ReadingRepository;
import com.example.DUD_Project.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;
    private final ReadingQuestionsRepository readingQuestionsRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Reading createReading(Integer lessonId, Reading reading) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if (lesson != null) {
            reading.setLesson(lesson);
            return readingRepository.save(reading);
        }

        return null;
    }

    @Override
    public Reading addQuestionsAndAnswers(Integer readingId, List<ReadingQuestions> questions) {
        Reading reading = readingRepository.findById(readingId).orElse(null);
        if (reading != null) {
            for (ReadingQuestions question : questions) {
                question.setReading(reading);
                readingQuestionsRepository.save(question);
            }
        }

        return reading;
    }

    @Override
    public Reading getReading(Integer readingId) {
        return readingRepository.findById(readingId).orElse(null);
    }

    @Override
    public int checkAnswers(Integer readingId, List<AnswerRequest> answers) {
        Reading reading = readingRepository.findById(readingId).orElse(null);
        if (reading == null) {
            return -1;
        }

        int correctAnswers = 0;

        List<ReadingQuestions> questions = reading.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            ReadingQuestions question = questions.get(i);
            if (answers.get(i).getSelectedOption() == question.getCorrectOption()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }
}
