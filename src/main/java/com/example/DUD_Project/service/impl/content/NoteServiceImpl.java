package com.example.DUD_Project.service.impl.content;

import com.example.DUD_Project.dto.content.NoteDto;
import com.example.DUD_Project.entity.content.Note;
import com.example.DUD_Project.entity.user.User;
import com.example.DUD_Project.mappers.content.NoteMapper;
import com.example.DUD_Project.repository.content.NoteRepository;
import com.example.DUD_Project.repository.UserRepository;
import com.example.DUD_Project.service.content.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(("User not found")));
    }

    private User getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User not authenticated");
        }
        String email = authentication.getName();

        return getUserByEmail(email);
    }

    @Override
    public ResponseEntity<NoteDto> createMessage(NoteDto noteDto, Authentication authentication) {
        User user = getCurrentUser(authentication);

        Note note = new Note();
        note.setMessage(noteDto.getMessage());
        note.setUser(user);
        Note createdNote = noteRepository.save(note);

        return ResponseEntity.ok(noteMapper.toDto(createdNote));
    }


    @Override
    public ResponseEntity<List<NoteDto>> getNotesByUser(Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<Note> notes = noteRepository.findByUser(user);

        return ResponseEntity.ok(noteMapper.toDtoList(notes));
    }


    @Override
    public ResponseEntity<NoteDto> update(Integer noteId, NoteDto noteDto, Authentication authentication) {
        User user = getCurrentUser(authentication);

        Note noteToUpdate = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!noteToUpdate.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        noteToUpdate.setMessage(noteDto.getMessage());
        Note updateNote = noteRepository.save(noteToUpdate);

        return ResponseEntity.ok(noteMapper.toDto(updateNote));
    }


    @Override
    public ResponseEntity<Void> delete(Integer noteId, Authentication authentication) {
        User user = getCurrentUser(authentication);

        Note noteToDelete = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!noteToDelete.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        noteRepository.delete(noteToDelete);

        return ResponseEntity.noContent().build();
    }
}
