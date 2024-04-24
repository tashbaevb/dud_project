package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.AnswerRequest;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Listening;
import com.example.DUD_Project.entity.ListeningQuestions;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.repository.ListeningQuestionsRepository;
import com.example.DUD_Project.repository.ListeningRepository;
import com.example.DUD_Project.service.ListeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ListeningServiceImpl implements ListeningService {

    private final ListeningRepository listeningRepository;
    private final ListeningQuestionsRepository listeningQuestionsRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Listening createListening(Integer lessonId, Listening listening) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if (lesson != null) {
            listening.setLesson(lesson);
            return listeningRepository.save(listening);
        }
        return null;
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
        return listeningRepository.findById(listeningId).orElse(null);
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
