package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.repository.lessonTypes.GrammarRepository;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrammarServiceImpl implements GrammarService {

    private final GrammarRepository grammarRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Grammar createGrammar(Integer lessonId, Grammar grammar) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        grammar.setLesson(lesson);

        return grammarRepository.save(grammar);
    }


    @Override
    public Grammar getGrammarByLessonId(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        return grammarRepository.findByLesson(lesson);
    }
}
