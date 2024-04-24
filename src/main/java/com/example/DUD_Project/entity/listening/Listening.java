package com.example.DUD_Project.entity.listening;

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
public class Listening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title, description;
    String mp3FilePath;

    @OneToMany(mappedBy = "listening", cascade = CascadeType.ALL)
    List<ListeningQuestions> questions;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
}
