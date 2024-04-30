package com.example.DUD_Project.service.content;

import com.example.DUD_Project.dto.content.BookDto;

import java.util.List;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    List<BookDto> getAll();
}
