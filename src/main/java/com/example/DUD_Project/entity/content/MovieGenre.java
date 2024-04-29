package com.example.DUD_Project.entity.content;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String genre;

    @OneToMany(mappedBy = "genre")
    Set<MovieGenreAssociation> associations = new HashSet<>();
}
