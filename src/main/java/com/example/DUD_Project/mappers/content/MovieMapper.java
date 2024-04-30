package com.example.DUD_Project.mappers.content;

import com.example.DUD_Project.dto.content.MovieDto;
import com.example.DUD_Project.entity.content.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final ModelMapper modelMapper;

    public MovieDto toDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie toEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }
}
