package com.example.DUD_Project.mappers.content;

import com.example.DUD_Project.dto.content.BookDto;
import com.example.DUD_Project.entity.content.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookDto toDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book toEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public List<BookDto> toDtoList(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
}
