package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.content.MovieGenre;
import com.example.DUD_Project.repository.MovieGenreRepository;
import com.example.DUD_Project.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {

    private final MovieGenreRepository movieGenreRepository;

    @Override
    public MovieGenre createMovieGenre(MovieGenre movieGenre) {
        return movieGenreRepository.save(movieGenre);
    }
}
