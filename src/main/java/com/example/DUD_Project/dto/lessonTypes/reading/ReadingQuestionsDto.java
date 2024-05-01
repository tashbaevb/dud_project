package com.example.DUD_Project.dto.lessonTypes.reading;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingQuestionsDto {

    Integer id;

    String question, option1, option2, option3;

    int correctOption;
}
