package com.example.DUD_Project.service.content;

import com.example.DUD_Project.dto.content.MovieDto;
import com.example.DUD_Project.entity.content.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto, MultipartFile file, MultipartFile image);

    Movie getMovieById(Integer movieId);

    List<MovieDto> getAllMovies();
}
