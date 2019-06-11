package com.example.basic.service;

import com.example.basic.model.DslModel;


import com.example.basic.repository.DslRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;




@Service
public class DslServiceImpl implements DslService {


    @Autowired
    DslRepository dslRepository;

    // 전체 호출 쿼리
    @Override
    public List<DslModel> findAll() {
        return dslRepository.findAllList();
    }

    // 전체 페이징 처리 쿼리
    @Override
    public Page<DslModel> getAllPageTable(Pageable pageable) {

       return dslRepository.findAllPage(pageable);
    }

    // 검색기능 및 페이지 처리
    @Override
    public Page<DslModel> getSearchPageTable_2(String flag_info,String search_info,Pageable pageable) {

            return dslRepository.SearchAllPage(flag_info,search_info,pageable);

    }



}