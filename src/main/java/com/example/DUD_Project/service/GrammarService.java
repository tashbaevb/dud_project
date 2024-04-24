package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.Grammar;

public interface GrammarService {

    Grammar createGrammar(Integer lessonId, Grammar grammar);

    Grammar getGrammarById(Integer grammarId);
}
