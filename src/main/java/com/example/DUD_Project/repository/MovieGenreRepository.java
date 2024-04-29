package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.content.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
}
