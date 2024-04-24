package com.example.DUD_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String question;
    String option1, option2, option3;
    int correctOption;

    @ManyToOne
    @JoinColumn(name = "reading_id")
    @JsonIgnore
    Reading reading;
}
