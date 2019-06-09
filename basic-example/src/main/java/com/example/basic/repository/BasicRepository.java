package com.example.basic.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.basic.model.BasicModel;

import javax.transaction.Transactional;


// JPA 작성 문법 https://weejw.tistory.com/83
public interface BasicRepository extends  JpaRepository<BasicModel, String> {
    Optional<BasicModel> findById(String id);
    Page<BasicModel> findById(String id, Pageable pageable);
    Page<BasicModel> findByName(String name, Pageable pageable);
    Page<BasicModel> findByIdOrName(String all,Pageable pageable);



    @Transactional
    @Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="update BASIC_MODEL T set T.name = :#{#e.name} WHERE T.id = :#{#e.id}", nativeQuery=true)
    Integer update(@Param("e")  BasicModel e );

    @Transactional
    @Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="delete BASIC_MODEL T WHERE T.name = :#{#e.name}", nativeQuery=true)
    void delete(@Param("e")  BasicModel e );


}

