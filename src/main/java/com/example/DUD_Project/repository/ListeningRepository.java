package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.Listening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningRepository extends JpaRepository<Listening, Integer> {
}
