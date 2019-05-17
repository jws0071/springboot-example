package com.example.basic.repository;


import com.example.basic.model.DslModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DslRepository extends JpaRepository<DslModel,Long> {

}
