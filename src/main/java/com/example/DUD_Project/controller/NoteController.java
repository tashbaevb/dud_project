package com.example.DUD_Project.controller;

import com.example.DUD_Project.dto.NoteDto;
import com.example.DUD_Project.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;


    @PostMapping()
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto, Authentication authentication) {
        return noteService.createMessage(noteDto, authentication);
    }

    @GetMapping()
    public ResponseEntity<List<NoteDto>> getNotesByUser(Authentication authentication) {
        return noteService.getNotesByUser(authentication);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Integer messageId, @RequestBody NoteDto noteDto, Authentication authentication) {
        return noteService.update(messageId, noteDto, authentication);
    }


    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer messageId, Authentication authentication) {
        return noteService.delete(messageId, authentication);
    }
}
