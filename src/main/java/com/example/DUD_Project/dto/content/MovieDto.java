package com.example.DUD_Project.dto.content;

import com.example.DUD_Project.dto.LevelDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {

    Integer id;

    String title, description, country, filePath;

    LevelDto levelDto;
}
