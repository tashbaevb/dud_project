package com.example.DUD_Project.dto.lessonTypes.reading;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingDto {

    Integer id;

    String title, description;

    List<ReadingQuestionsDto> questions;
}
