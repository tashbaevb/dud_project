package com.example.DUD_Project.mappers;

import com.example.DUD_Project.dto.LessonDto;
import com.example.DUD_Project.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {

    private final ModelMapper modelMapper;

    public LessonDto toDto(Lesson lesson) {
        return modelMapper.map(lesson, LessonDto.class);
    }

    public Lesson toEntity(LessonDto lessonDto) {
        return modelMapper.map(lessonDto, Lesson.class);
    }

    public List<LessonDto> toDtoList(List<Lesson> lessons) {
        return lessons.stream().map(this::toDto).collect(Collectors.toList());
    }
}
