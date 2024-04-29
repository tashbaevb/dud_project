package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grammar")
public class GrammarController {

    private final GrammarService grammarService;

    @PostMapping("/create/{lessonId}")
    public Grammar createGrammar(@PathVariable Integer lessonId, @RequestBody Grammar grammar) {
        return grammarService.createGrammar(lessonId, grammar);
    }
}
