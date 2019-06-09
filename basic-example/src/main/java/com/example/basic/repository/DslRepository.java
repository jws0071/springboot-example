package com.example.basic.repository;


import com.example.basic.model.DslModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DslRepository {
    List<DslModel> findAllList();
    Page<DslModel> findAllPage(Pageable pageable);
}
