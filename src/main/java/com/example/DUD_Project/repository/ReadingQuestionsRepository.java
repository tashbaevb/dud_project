package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.ReadingQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingQuestionsRepository extends JpaRepository<ReadingQuestions, Integer> {
}
