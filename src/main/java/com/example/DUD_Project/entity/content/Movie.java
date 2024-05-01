package com.example.DUD_Project.entity.content;

import com.example.DUD_Project.entity.Level;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title, description, country;

    String filePath;

    @ManyToOne
    @JoinColumn(name = "level_id")
    Level level;
}
