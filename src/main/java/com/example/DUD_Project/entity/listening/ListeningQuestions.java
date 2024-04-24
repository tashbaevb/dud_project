package com.example.DUD_Project.entity.listening;

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
public class ListeningQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String question;
    String option1, option2, option3;
    int correctOption;


    @ManyToOne
    @JoinColumn(name = "listening_id")
    @JsonIgnore
    Listening listening;
}
