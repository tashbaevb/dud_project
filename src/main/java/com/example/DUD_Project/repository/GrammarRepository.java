package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.Grammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarRepository extends JpaRepository<Grammar, Integer> {
}
