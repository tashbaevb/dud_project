package com.example.DUD_Project.controller.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grammar")
public class GrammarController {

    private final GrammarService grammarService;

    @PostMapping("/create/{lessonId}")
    public ResponseEntity<GrammarDto> createGrammar(@PathVariable Integer lessonId,
                                                    @RequestParam MultipartFile file,
                                                    @RequestParam String title,
                                                    @RequestParam String description) {
        GrammarDto grammarDto = new GrammarDto();
        grammarDto.setTitle(title);
        grammarDto.setDescription(description);
        ResponseEntity<GrammarDto> response = grammarService.createGrammar(grammarDto, file, lessonId);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
