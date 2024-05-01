package com.example.DUD_Project.mappers.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.GrammarDto;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrammarMapper {

    private final ModelMapper modelMapper;


    public GrammarDto toDto(Grammar grammar) {
        return modelMapper.map(grammar, GrammarDto.class);
    }

    public Grammar toEntity(GrammarDto grammarDto) {
        return modelMapper.map(grammarDto, Grammar.class);
    }
}
