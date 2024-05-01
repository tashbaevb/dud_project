package com.example.DUD_Project.service.impl.content;

import com.example.DUD_Project.dto.content.BookDto;
import com.example.DUD_Project.entity.content.Book;
import com.example.DUD_Project.mappers.content.BookMapper;
import com.example.DUD_Project.repository.content.BookRepository;
import com.example.DUD_Project.service.content.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Override
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);

        return ResponseEntity.ok(bookMapper.toDto(book));
    }

    @Override
    public ResponseEntity<List<BookDto>> getAll() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books.stream().map(bookMapper::toDto).collect(Collectors.toList()));
    }
}
