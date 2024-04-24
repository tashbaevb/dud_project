package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.Grammar;

public interface GrammarService {

    Grammar createGrammar(Integer lessonId, Grammar grammar);

    Grammar getGrammarById(Integer grammarId);
}
