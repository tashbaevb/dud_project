package com.example.DUD_Project.dto.lessonTypes.listening;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningDto {

    Integer id;

    String title, description, mp3FilePath;

    List<ListeningQuestionsDto> questions;
}
