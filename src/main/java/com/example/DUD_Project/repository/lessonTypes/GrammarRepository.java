package com.example.DUD_Project.repository.lessonTypes;

import com.example.DUD_Project.entity.Lesson;
import com.example.DUD_Project.entity.lessonTypes.Grammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarRepository extends JpaRepository<Grammar, Integer> {

    Grammar findByLesson(Lesson lesson);
}
