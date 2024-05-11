package com.example.DUD_Project.controller.content;

import com.example.DUD_Project.dto.LevelDto;
import com.example.DUD_Project.dto.content.BookDto;
import com.example.DUD_Project.service.content.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public BookDto createBook(@RequestParam("file") MultipartFile file,
                                              @RequestParam("title") String title,
                                              @RequestParam("description") String description,
                                              @RequestParam("author") String author,
                                              @RequestParam("content") String content,
                                              @RequestParam("levelId") Integer levelId) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setDescription(description);
        bookDto.setAuthor(author);
        bookDto.setContent(content);

        LevelDto levelDto = new LevelDto();
        levelDto.setId(levelId);
        bookDto.setLevel(levelDto);

        return bookService.createBook(bookDto, file).getBody();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/getById/{bookId}")
    public  ResponseEntity<BookDto> getById(@PathVariable Integer bookId) {
        return bookService.getById(bookId);
    }
}
