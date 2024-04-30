package com.example.DUD_Project.controller.content;

import com.example.DUD_Project.dto.LevelDto;
import com.example.DUD_Project.dto.content.MovieDto;
import com.example.DUD_Project.entity.content.Movie;
import com.example.DUD_Project.service.content.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<MovieDto> createMovie(@RequestParam("file") MultipartFile file,
                                                @RequestParam("title") String title,
                                                @RequestParam("description") String description,
                                                @RequestParam("country") String country,
                                                @RequestParam("levelId") Integer levelId) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setDescription(description);
        movieDto.setCountry(country);

        LevelDto levelDto = new LevelDto();
        levelDto.setId(levelId);
        movieDto.setLevelDto(levelDto);

        MovieDto createdMovie = movieService.createMovie(movieDto, file);

        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }


    @GetMapping("/getById/{movieId}")
    public Movie getById(@PathVariable Integer movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movieDto = movieService.getAllMovies();
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }
}
