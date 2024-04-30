package com.example.DUD_Project.repository.content;

import com.example.DUD_Project.entity.content.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
