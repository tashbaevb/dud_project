package com.example.DUD_Project.service.impl;

import com.example.DUD_Project.entity.content.Movie;
import com.example.DUD_Project.repository.MovieRepository;
import com.example.DUD_Project.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie, MultipartFile file) {
        String filePath = saveFile(file);
        movie.setFilePath(filePath);

        return movieRepository.save(movie);
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
}
