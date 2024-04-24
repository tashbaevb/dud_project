package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.ListeningQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningQuestionsRepository extends JpaRepository<ListeningQuestions, Integer> {
}
