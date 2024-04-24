package com.example.DUD_Project.entity.lessonTypes.reading;

import com.example.DUD_Project.entity.Lesson;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title, description;

    @OneToMany(mappedBy = "reading", cascade = CascadeType.ALL)
    List<ReadingQuestions> questions;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
}
