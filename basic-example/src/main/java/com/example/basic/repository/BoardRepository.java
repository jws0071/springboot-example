package com.example.basic.repository;


import com.example.basic.model.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardModel, String> {
    Optional<BoardModel> findById(String id);
}
