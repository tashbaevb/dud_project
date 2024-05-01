package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import org.springframework.http.ResponseEntity;

public interface GrammarService {

    ResponseEntity<GrammarDto> createGrammar(Integer lessonId, GrammarDto grammarDto);

    ResponseEntity<GrammarDto> getGrammarByLessonId(Integer lessonId);
}
