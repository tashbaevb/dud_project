package com.example.DUD_Project.repository.lessonTypes.reading;

import com.example.DUD_Project.entity.lessonTypes.reading.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Integer> {
}
