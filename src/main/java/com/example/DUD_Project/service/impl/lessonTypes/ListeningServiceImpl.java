package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.listening.ListeningDto;
import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import com.example.DUD_Project.entity.lessonTypes.listening.ListeningQuestions;
import com.example.DUD_Project.mappers.lessonTypes.ListeningMapper;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.lessonTypes.listening.ListeningQuestionsRepository;
import com.example.DUD_Project.repository.lessonTypes.listening.ListeningRepository;
import com.example.DUD_Project.service.lessonTypes.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ListeningServiceImpl implements ListeningService {

    private final ListeningRepository listeningRepository;
    private final ListeningQuestionsRepository listeningQuestionsRepository;
    private final LessonRepository lessonRepository;
    private final ListeningMapper listeningMapper;

    private Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
    }


    @Override
    public ResponseEntity<ListeningDto> createListening(ListeningDto listeningDto, Integer lessonId, MultipartFile file) {
        Lesson lesson = getLessonById(lessonId);
        Listening listening = listeningMapper.toEntity(listeningDto);

        String filePath = saveMp3File(file);
        listening.setMp3FilePath(filePath);
        listening.setLesson(lesson);

        Listening savedListening = listeningRepository.save(listening);
        ListeningDto savedListeningDto = listeningMapper.toDto(savedListening);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedListeningDto);
    }

    private String saveMp3File(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/hFile");
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "hFile/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store");
        }
    }

    @Override
    public ResponseEntity<ListeningDto> addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions) {
        Listening listening = listeningRepository.findById(listeningId)
                .orElseThrow(() -> new IllegalArgumentException("Listening not found"));

        for (ListeningQuestions question : questions) {
            question.setListening(listening);
            listeningQuestionsRepository.save(question);
        }

        ListeningDto listeningDto = listeningMapper.toDto(listening);
        return ResponseEntity.ok(listeningDto);
    }

    @Override
    public ResponseEntity<ListeningDto> getListeningByLessonId(Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        Listening listening = listeningRepository.findByLesson(lesson);
        if (listening == null) {
            return ResponseEntity.notFound().build();
        }

        ListeningDto listeningDto = listeningMapper.toDto(listening);
        return ResponseEntity.ok(listeningDto);
    }

    @Override
    public ResponseEntity<Integer> checkAnswers(Integer listeningId, List<AnswerRequest> answers) {
        Listening listening = listeningRepository.findById(listeningId).orElse(null);
        if (listening == null) {
            return ResponseEntity.notFound().build();
        }

        int correctAnswers = 0;
        List<ListeningQuestions> questions = listening.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            ListeningQuestions question = questions.get(i);
            if (answers.get(i).getSelectedOption() == question.getCorrectOption()) {
                correctAnswers++;
            }
        }

        return ResponseEntity.ok(correctAnswers);
    }
}
