package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.reading.ReadingDto;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import com.example.DUD_Project.entity.lessonTypes.reading.ReadingQuestions;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.mappers.lessonTypes.ReadingMapper;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.lessonTypes.reading.ReadingQuestionsRepository;
import com.example.DUD_Project.repository.lessonTypes.reading.ReadingRepository;
import com.example.DUD_Project.service.lessonTypes.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;
    private final ReadingQuestionsRepository readingQuestionsRepository;
    private final LessonRepository lessonRepository;
    private final ReadingMapper readingMapper;


    private Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
    }

    @Override
    public ResponseEntity<ReadingDto> createReading(ReadingDto readingDto, Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        Reading reading = readingMapper.toEntity(readingDto);
        reading.setLesson(lesson);

        Reading savedReading = readingRepository.save(reading);
        ReadingDto savedReadingDto = readingMapper.toDto(savedReading);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReadingDto);
    }

    @Override
    public ResponseEntity<ReadingDto> addQuestionsAndAnswers(List<ReadingQuestions> questions, Integer readingId) {
        Reading reading = readingRepository.findById(readingId)
                .orElseThrow(() -> new IllegalArgumentException("Reading not found"));

        for (ReadingQuestions question : questions) {
            question.setReading(reading);
            readingQuestionsRepository.save(question);
        }

        ReadingDto readingDto = readingMapper.toDto(reading);
        return ResponseEntity.ok(readingDto);
    }

    @Override
    public ResponseEntity<ReadingDto> getReadingByLessonId(Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        Reading reading = readingRepository.findByLesson(lesson);
        if (reading == null) {
            return ResponseEntity.notFound().build();
        }

        ReadingDto readingDto = readingMapper.toDto(reading);
        return ResponseEntity.ok(readingDto);
    }

    @Override
    public int checkAnswers(List<AnswerRequest> answers, Integer readingId) {
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
