package com.example.DUD_Project.service.content;

import com.example.DUD_Project.dto.content.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<BookDto> createBook(BookDto bookDto);

    ResponseEntity<List<BookDto>> getAll();
}
