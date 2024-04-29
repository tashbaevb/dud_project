package com.example.DUD_Project.controller.content;

import com.example.DUD_Project.entity.content.Movie;
import com.example.DUD_Project.entity.content.MovieGenre;
import com.example.DUD_Project.entity.content.MovieGenreAssociation;
import com.example.DUD_Project.service.MovieGenreService;
import com.example.DUD_Project.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieGenreService movieGenreService;

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestParam("file") MultipartFile file,
                                             @RequestParam("title") String title,
                                             @RequestParam("description") String description,
                                             @RequestParam("country") String country,
                                             @RequestParam("genres") List<String> genres) {
        // Создаем новый фильм
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setCountry(country);

        // Создаем и сохраняем жанры
        Set<MovieGenre> movieGenres = new HashSet<>();
        for (String genreName : genres) {
            MovieGenre genre = new MovieGenre();
            genre.setGenre(genreName);
            movieGenres.add(genre);
            movieGenreService.createMovieGenre(genre);
        }

        // Создаем ассоциации между фильмом и жанрами
        Set<MovieGenreAssociation> associations = new HashSet<>();
        for (MovieGenre genre : movieGenres) {
            MovieGenreAssociation association = new MovieGenreAssociation();
            association.setMovie(movie);
            association.setGenre(genre);
            associations.add(association);
        }
        movie.setAssociations(associations);

        Movie createdMovie = movieService.createMovie(movie, file);

        return ResponseEntity.ok().body(createdMovie);
    }

    @PostMapping("/genre/create")
    public MovieGenre createMovieGenre(@RequestBody MovieGenre movieGenre) {
        return movieGenreService.createMovieGenre(movieGenre);
    }

    @GetMapping("/getById/{movieId}")
    public Movie getById(@PathVariable Integer movieId) {
        return movieService.getMovieById(movieId);
    }

}
