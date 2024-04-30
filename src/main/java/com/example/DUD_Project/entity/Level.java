package com.example.DUD_Project.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String levelName; // A1, A2, etc.
}
