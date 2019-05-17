package com.example.basic.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.basic.model.BasicModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BasicService {

    Optional<BasicModel> getStudyTable(Integer id);
    List<BasicModel> getAllStudyTable();
    Page<BasicModel> getAllPageTable(Pageable pageable);
    //테이블 저장
    BasicModel insert_table(BasicModel e);
    //테이블 업데이트
    void update_table(BasicModel e);

    void delete_table(BasicModel e);




}
