//package com.example.DUD_Project.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "questions")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class Question {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer id;
//
//    @Column(name = "question_text")
//    String questionText;
//
//    @ManyToOne
//    @JoinColumn(name = "lesson_id")
//    Lesson lesson;
//
//}