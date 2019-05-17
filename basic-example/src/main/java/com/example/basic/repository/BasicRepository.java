package com.example.basic.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.basic.model.BasicModel;

import javax.transaction.Transactional;

public interface BasicRepository extends  JpaRepository<BasicModel, Integer> {
    Optional<BasicModel> findById(int id);

    @Transactional
    @Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="update BASIC_MODEL T set T.name = :#{#e.name} WHERE T.id = :#{#e.id}", nativeQuery=true)
    Integer update(@Param("e")  BasicModel e );

    @Transactional
    @Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="delete BASIC_MODEL T WHERE T.name = :#{#e.name}", nativeQuery=true)
    void delete(@Param("e")  BasicModel e );


}

