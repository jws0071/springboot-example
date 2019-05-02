package com.example.basic.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.basic.model.BasicModel;

public interface BasicRepository extends  JpaRepository<BasicModel, Integer> {
    Optional<BasicModel> findById(int id);

    @Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="update study_table u set u.name = :#{#e.name} WHERE u.id = :#{#e.id}", nativeQuery=true)
    Object update(@Param("e")  BasicModel e );

}

