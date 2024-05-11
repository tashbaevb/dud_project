package com.example.DUD_Project.service.content;

import com.example.DUD_Project.dto.content.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    ResponseEntity<BookDto> createBook(BookDto bookDto, MultipartFile file);

    ResponseEntity<List<BookDto>> getAll();

    ResponseEntity<BookDto> getById(Integer id);
}
