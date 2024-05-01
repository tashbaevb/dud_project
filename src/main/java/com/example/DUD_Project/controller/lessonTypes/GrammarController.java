package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grammar")
public class GrammarController {

    private final GrammarService grammarService;

    @PostMapping("/create/{lessonId}")
    public ResponseEntity<GrammarDto> createGrammar(@PathVariable Integer lessonId, @RequestBody GrammarDto grammarDto) {
        return grammarService.createGrammar(lessonId, grammarDto);
    }
}
