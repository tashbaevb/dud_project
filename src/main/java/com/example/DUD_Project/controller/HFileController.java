package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.HFile;
import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.service.HFileService;
import com.example.DUD_Project.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hfile")
public class HFileController {

    private final HFileService hFileService;
    private final LessonService lessonService;


    @PostMapping("/create")
    public HFile uploadFile(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file, @RequestParam("lessonId") Integer lessonId) {

        Lesson lesson = lessonService.findById(lessonId);
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson with id " + lessonId + " not found.");
        }

        return hFileService.save(name, file, lesson);
    }


    @GetMapping("/getFile")
    public ResponseEntity<Resource> getFile(@RequestParam("id") Integer id) throws IOException {
        HFile file = hFileService.findById(id);
        if (file != null) {
            ByteArrayResource resource = new ByteArrayResource(file.getFileData());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
            headers.setContentLength(file.getFileData().length);
            headers.setContentDispositionFormData("filename", file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
