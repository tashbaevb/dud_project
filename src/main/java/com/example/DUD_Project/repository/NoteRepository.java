package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.Note;
import com.example.DUD_Project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUser(User user);
}
