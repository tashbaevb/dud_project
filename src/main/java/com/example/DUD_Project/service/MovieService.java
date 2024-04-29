package com.example.DUD_Project.service;

import com.example.DUD_Project.entity.content.Movie;
import org.springframework.web.multipart.MultipartFile;

public interface MovieService {

    Movie createMovie(Movie movie, MultipartFile file);

    Movie getMovieById(Integer movieId);
}
