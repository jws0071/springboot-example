package com.example.basic.repository;

import com.example.basic.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CmtRepository extends JpaRepository<CommentModel, String> {


    Optional<CommentModel> findByCid(int id);
}

