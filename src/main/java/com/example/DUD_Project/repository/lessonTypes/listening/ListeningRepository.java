package com.example.DUD_Project.repository.lessonTypes.listening;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import com.example.DUD_Project.entity.lessonTypes.listening.Listening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningRepository extends JpaRepository<Listening, Integer> {

    Listening findByLesson(Lesson lesson);
}
