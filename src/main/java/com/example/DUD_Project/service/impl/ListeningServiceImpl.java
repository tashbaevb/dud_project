package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.listening.AnswerRequest;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.listening.Listening;
import com.example.DUD_Project.entity.listening.ListeningQuestions;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.ListeningQuestionsRepository;
import com.example.DUD_Project.repository.ListeningRepository;
import com.example.DUD_Project.service.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ListeningServiceImpl implements ListeningService {

    private final ListeningRepository listeningRepository;
    private final ListeningQuestionsRepository listeningQuestionsRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Listening createListening(Integer lessonId, Listening listening, MultipartFile file) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if (lesson != null) {
            String filePath = saveMp3File(file);
            listening.setMp3FilePath(filePath);

            listening.setLesson(lesson);

            return listeningRepository.save(listening);
        }
        return null;
    }

    private String saveMp3File(MultipartFile file) {

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/hFile");
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return uploadPath.toString() + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store");
        }
    }


    @Override
    public Listening addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions) {
        Listening listening = listeningRepository.findById(listeningId).orElse(null);
        if (listening != null) {
            for (ListeningQuestions question : questions) {
                question.setListening(listening);
                listeningQuestionsRepository.save(question);
            }
        }
        return listening;
    }

    @Override
    public Listening getListening(Integer listeningId) {
        Listening listening = listeningRepository.findById(listeningId).orElse(null);
        if (listening != null) {
            String mp3Url = getMp3Url(listening.getMp3FilePath());
            listening.setMp3FilePath(mp3Url);
        }

        return listening;
    }

    public String getMp3Url(String mp3Path) {
        URI mp3Uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/mp3/")
                .path(mp3Path)
                .build()
                .toUri();

        return mp3Uri.toString();
    }


    @Override
    public int checkAnswers(Integer listeningId, List<AnswerRequest> answers) {
        Listening listening = listeningRepository.findById(listeningId).orElse(null);
        if (listening == null) {
            return -1;
        }

        int correctAnswers = 0;
        List<ListeningQuestions> questions = listening.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            ListeningQuestions question = questions.get(i);
            if (answers.get(i).getSelectedOption() == question.getCorrectOption()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }
}
