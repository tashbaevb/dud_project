package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.repository.GrammarRepository;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.service.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrammarServiceImpl implements GrammarService {

    private final GrammarRepository grammarRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Grammar createGrammar(Integer lessonId, Grammar grammar) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

        if (lesson != null) {
            grammar.setLesson(lesson);
            return grammarRepository.save(grammar);
        }

        return null;
    }

    @Override
    public Grammar getGrammarById(Integer grammarId) {
        return grammarRepository.findById(grammarId).orElse(null);
    }
}
