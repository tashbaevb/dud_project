package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.lessonTypes.reading.ReadingQuestionsRepository;
import com.example.DUD_Project.repository.lessonTypes.reading.ReadingRepository;
import com.example.DUD_Project.service.lessonTypes.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;
    private final ReadingQuestionsRepository readingQuestionsRepository;
    private final LessonRepository lessonRepository;


    private Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
    }

    @Override
    public Reading createReading(Integer lessonId, Reading reading) {
        Lesson lesson = getLessonById(lessonId);

        reading.setLesson(lesson);
        return readingRepository.save(reading);
    }

    @Override
    public Reading addQuestionsAndAnswers(Integer readingId, List<ReadingQuestions> questions) {
        Reading reading = readingRepository.findById(readingId)
                .orElseThrow(() -> new IllegalArgumentException("Reading not found"));

        for (ReadingQuestions question : questions) {
            question.setReading(reading);
            readingQuestionsRepository.save(question);
        }

        return reading;
    }

    @Override
    public Reading getReadingByLessonId(Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        return readingRepository.findByLesson(lesson);
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
