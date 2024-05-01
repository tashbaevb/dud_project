package com.example.DUD_Project.service.content;

import com.example.DUD_Project.dto.content.NoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteService {

    ResponseEntity<NoteDto> createMessage(NoteDto noteDto, Authentication authentication);

    ResponseEntity<List<NoteDto>> getNotesByUser(Authentication authentication);

    ResponseEntity<NoteDto> update(Integer noteId, NoteDto noteDto, Authentication authentication);

    ResponseEntity<Void> delete(Integer noteId, Authentication authentication);
}
