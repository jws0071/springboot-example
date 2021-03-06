package com.example.basic.service;

import com.example.basic.model.DslModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DslService {
    List<DslModel> findAll();
    Page<DslModel> getAllPageTable(Pageable pageable);
    Page<DslModel> getSearchPageTable_2(String flag_info,String search_info,Pageable pageable);

}
