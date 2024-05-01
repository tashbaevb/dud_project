package com.example.DUD_Project.mappers.content;

import com.example.DUD_Project.dto.content.NoteDto;
import com.example.DUD_Project.entity.content.Note;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    private final ModelMapper modelMapper;

    public NoteDto toDto(Note note) {
        return modelMapper.map(note, NoteDto.class);
    }

    public Note toEntity(NoteDto noteDto) {
        return modelMapper.map(noteDto, Note.class);
    }

    public List<NoteDto> toDtoList(List<Note> notes) {
        return notes.stream().map(this::toDto).collect(Collectors.toList());
    }
}
