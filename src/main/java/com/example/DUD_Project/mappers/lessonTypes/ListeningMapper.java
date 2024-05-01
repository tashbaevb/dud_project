package com.example.DUD_Project.mappers.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.listening.ListeningDto;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListeningMapper {

    private final ModelMapper modelMapper;


    public ListeningDto toDto(Listening listening) {
        return modelMapper.map(listening, ListeningDto.class);
    }

    public Listening toEntity(ListeningDto listeningDto) {
        return modelMapper.map(listeningDto, Listening.class);
    }

    public List<ListeningDto> toDtoList(List<Listening> listenings) {
        return listenings.stream().map(this::toDto).collect(Collectors.toList());
    }
}
