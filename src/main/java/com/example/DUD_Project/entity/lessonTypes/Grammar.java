package com.example.DUD_Project.entity.lessonTypes;

import com.example.DUD_Project.entity.Lesson;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Grammar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title, description;

    String imgPath;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
}
