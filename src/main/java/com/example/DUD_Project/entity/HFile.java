//package com.example.DUD_Project.entity;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Table(name = "hören_file")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class HFile {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer id;
//
//    String name;
//
//    @Lob
//    byte[] fileData;
//
//    @OneToOne
//    @JoinColumn(name = "lesson_id")
//    Lesson lesson;
//}
