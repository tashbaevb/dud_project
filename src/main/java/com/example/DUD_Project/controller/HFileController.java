package com.example.DUD_Project.controller;

import com.example.DUD_Project.entity.HFile;
import com.example.DUD_Project.service.HFileService;
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

    @PostMapping("/create")
    public HFile uploadFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        return hFileService.save(name, file);
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
