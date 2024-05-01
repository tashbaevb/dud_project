package com.example.DUD_Project.dto.lessonTypes;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarDto {

    Integer id;

    String title, description;
}
