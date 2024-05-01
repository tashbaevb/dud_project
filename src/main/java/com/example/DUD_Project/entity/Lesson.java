package com.example.DUD_Project.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title, description;

    @ManyToOne
    @JoinColumn(name = "level_id")
    Level level;
}
