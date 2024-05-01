package com.example.DUD_Project.mappers;

import com.example.DUD_Project.dto.LevelDto;
import com.example.DUD_Project.entity.Level;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelMapper {

    private final ModelMapper modelMapper;

    public LevelDto toDto(Level level) {
        return modelMapper.map(level, LevelDto.class);
    }

    public Level toEntity(LevelDto levelDto) {
        return modelMapper.map(levelDto, Level.class);
    }
}
