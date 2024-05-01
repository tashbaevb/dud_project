package com.example.DUD_Project.dto.content;

import com.example.DUD_Project.dto.LevelDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    String title, description, author, content;

    LevelDto level;
}