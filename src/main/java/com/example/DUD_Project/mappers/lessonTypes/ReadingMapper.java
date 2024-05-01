package com.example.DUD_Project.mappers.lessonTypes;

import com.example.DUD_Project.dto.lessonTypes.reading.ReadingDto;
import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReadingMapper {

    private final ModelMapper modelMapper;


    public ReadingDto toDto(Reading reading) {
        return modelMapper.map(reading, ReadingDto.class);
    }

    public Reading toEntity(ReadingDto readingDto) {
        return modelMapper.map(readingDto, Reading.class);
    }

    public List<ReadingDto> toDtoList(List<Reading> readings) {
        return readings.stream().map(this::toDto).collect(Collectors.toList());
    }
}
