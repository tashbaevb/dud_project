package com.example.DUD_Project.service.impl.content;

import com.example.DUD_Project.dto.content.MovieDto;
import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.entity.content.Movie;
import com.example.DUD_Project.mappers.LevelMapper;
import com.example.DUD_Project.mappers.content.MovieMapper;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.repository.content.MovieRepository;
import com.example.DUD_Project.service.content.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final LevelRepository levelRepository;
    private final MovieMapper movieMapper;
    private final LevelMapper levelMapper;


    @Override
    public MovieDto createMovie(MovieDto movieDto, MultipartFile file) {
        Movie movie = movieMapper.toEntity(movieDto);
        Level level = levelRepository.findById(movieDto.getLevelDto().getId())
                .orElseThrow(() -> new NotFoundException("Level not found with ID: " + movieDto.getLevelDto().getId()));

        String filePath = saveFile(file);

        movie.setLevel(level);
        movie.setFilePath(filePath);

        movie = movieRepository.save(movie);

        return movieMapper.toDto(movie);
    }

    private String saveFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/media/movies");

            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            String movieFileName = Paths.get(movie.getFilePath()).getFileName().toString();
            movie.setFilePath("/media/movies/" + movieFileName);
        }

        return movie;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> {
            MovieDto movieDto = movieMapper.toDto(movie);
            movieDto.setLevelDto(movie.getLevel() != null ? levelMapper.toDto(movie.getLevel()) : null);
            return movieDto;
        }).collect(Collectors.toList());
    }
}
