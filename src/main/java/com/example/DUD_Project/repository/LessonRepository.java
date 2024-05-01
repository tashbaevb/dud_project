package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByLevel(Level level);
}
