package com.example.DUD_Project.service.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GrammarService {

    ResponseEntity<GrammarDto> createGrammar(GrammarDto grammarDto, MultipartFile file, Integer lessonId);

    ResponseEntity<GrammarDto> getGrammarByLessonId(Integer lessonId);
}
