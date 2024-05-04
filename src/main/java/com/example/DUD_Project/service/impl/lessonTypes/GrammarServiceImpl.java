package com.example.DUD_Project.service.impl.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.mappers.lessonTypes.GrammarMapper;
import com.example.DUD_Project.repository.lessonTypes.GrammarRepository;
import com.example.DUD_Project.repository.LessonRepository;
import com.example.DUD_Project.service.lessonTypes.GrammarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class GrammarServiceImpl implements GrammarService {

    private final GrammarRepository grammarRepository;
    private final LessonRepository lessonRepository;
    private final GrammarMapper grammarMapper;


    private Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
    }

    @Override
    public ResponseEntity<GrammarDto> createGrammar(GrammarDto grammarDto, MultipartFile file, Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        Grammar grammar = grammarMapper.toEntity(grammarDto);

        String imgPath = saveImgFile(file);
        grammar.setLesson(lesson);
        grammar.setImgPath(imgPath);

        Grammar savedGrammar = grammarRepository.save(grammar);
        GrammarDto savedGrammarDto = grammarMapper.toDto(savedGrammar);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGrammarDto);
    }

    private String saveImgFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/media/grammar");

            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "media/grammar/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store");
        }
    }

    @Override
    public ResponseEntity<GrammarDto> getGrammarByLessonId(Integer lessonId) {
        Lesson lesson = getLessonById(lessonId);
        Grammar grammar = grammarRepository.findByLesson(lesson);
        if (grammar == null) {
            return ResponseEntity.notFound().build();
        }

        GrammarDto grammarDto = grammarMapper.toDto(grammar);
        return ResponseEntity.ok(grammarDto);
    }
}
