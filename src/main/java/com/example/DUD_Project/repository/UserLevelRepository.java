package com.example.DUD_Project.repository;

import com.example.DUD_Project.entity.user.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel, Integer> {
}
