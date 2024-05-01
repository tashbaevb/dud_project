package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.listening.AnswerRequest;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import com.example.DUD_Project.entity.lessonTypes.listening.ListeningQuestions;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.lessonTypes.listening.ListeningQuestionsRepository;
import com.example.DUD_Project.repository.lessonTypes.listening.ListeningRepository;
import com.example.DUD_Project.service.lessonTypes.ListeningService;
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
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        String filePath = saveMp3File(file);
        listening.setMp3FilePath(filePath);

        listening.setLesson(lesson);

        return listeningRepository.save(listening);
    }

    private String saveMp3File(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/hFile");
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String res = "hFile/" + fileName;

            return res;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store");
        }
    }



    @Override
    public Listening addQuestionsAndAnswers(Integer listeningId, List<ListeningQuestions> questions) {
        Listening listening = listeningRepository.findById(listeningId)
                .orElseThrow(() -> new IllegalArgumentException("Listening not found"));
        for (ListeningQuestions question : questions) {
            question.setListening(listening);
            listeningQuestionsRepository.save(question);
        }

        return listening;
    }

    @Override
    public Listening getListeningByLessonId(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        return listeningRepository.findByLesson(lesson);
    }


//    @Override
//    public Listening getListening(Integer listeningId) {
//        Listening listening = listeningRepository.findById(listeningId)
//                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
//
//        String mp3FileName = Paths.get(listening.getMp3FilePath()).getFileName().toString();
//        listening.setMp3FilePath("/hFile/" + mp3FileName);
//
//        return listening;
//    }


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
