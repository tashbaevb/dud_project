package com.example.DUD_Project.service.impl.content;

import com.example.DUD_Project.dto.content.BookDto;
import com.example.DUD_Project.entity.Level;
import com.example.DUD_Project.entity.content.Book;
import com.example.DUD_Project.mappers.LevelMapper;
import com.example.DUD_Project.mappers.content.BookMapper;
import com.example.DUD_Project.repository.LevelRepository;
import com.example.DUD_Project.repository.content.BookRepository;
import com.example.DUD_Project.service.content.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final LevelRepository levelRepository;


    @Override
    public ResponseEntity<BookDto> createBook(BookDto bookDto, MultipartFile file) {
        Book book = bookMapper.toEntity(bookDto);
        Level level = levelRepository.findById(bookDto.getLevel().getId())
                .orElseThrow(() -> new NotFoundException("Level not found with ID: " + bookDto.getLevel().getId()));

        String filePath = saveFile(file);
        book.setLevel(level);
        book.setFilePath(filePath);

        book = bookRepository.save(book);

        return ResponseEntity.ok(bookMapper.toDto(book));
    }


    private String saveFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get("src/main/resources/media/books");

            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "media/books/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }


    @Override
    public ResponseEntity<List<BookDto>> getAll() {
        List<Book> books = bookRepository.findAll();

        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }

    @Override
    public ResponseEntity<BookDto> getById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));

        BookDto bookDto = bookMapper.toDto(book);

        return ResponseEntity.ok(bookDto);
    }
}
