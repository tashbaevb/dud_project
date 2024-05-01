package com.example.DUD_Project.controller.content;

import com.example.DUD_Project.dto.content.BookDto;
import com.example.DUD_Project.service.content.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return bookService.getAll();
    }
}
