package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.Grammar;
import com.example.DUD_Project.service.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GrammarController {

    private final GrammarService grammarService;

    @PostMapping("/create/{lessonId}")
    public Grammar createGrammar(@PathVariable Integer lessonId, @RequestBody Grammar grammar) {
        return grammarService.createGrammar(lessonId, grammar);
    }

    @GetMapping("/get/{grammarId}")
    public Grammar getGrammar(@PathVariable Integer grammarId) {
        return grammarService.getGrammarById(grammarId);
    }
}
