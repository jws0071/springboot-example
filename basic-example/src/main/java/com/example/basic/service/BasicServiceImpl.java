package com.example.basic.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.example.basic.model.BasicModel;
import com.example.basic.repository.BasicRepository;

@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    BasicRepository basicRepository;


    @Override
    public Optional<BasicModel> getStudyTable(String id) {

        return basicRepository.findById(id);
    }

    //JPA 사용
    @Override
    public List<BasicModel> getAllStudyTable() {

        return basicRepository.findAll();
    }


    @Override
    public Page<BasicModel> getAllPageTable(Pageable pageable){

        return basicRepository.findAll(pageable);
    }



    @Override
    public List<BasicModel>  insert_table(BasicModel e) {
        basicRepository.save(e);
        List<BasicModel> result = getAllStudyTable();
        return result;
    }

    @Override
    public void update_table(BasicModel e) {
        basicRepository.update(e);
    }

    @Override
    public void delete_table(BasicModel e) {
        basicRepository.delete(e);

    }



    // Search 부분
    @Override
    public Page<BasicModel> getSearchPageTable(String flag, String search_info, Pageable pageable){
        if( flag.equals("1") ){
            return basicRepository.findById(search_info,pageable);
        }else if( flag.equals("2") ){
            return basicRepository.findByName(search_info,pageable);
        }else{
            return basicRepository.findByIdContainingOrNameContaining(search_info,search_info,pageable);
        }


    }

    // JOIN 된 테이블 호출하기
    @Override
    public Optional<BasicModel> getBoardTabel(String id) {

        return basicRepository.findById(id);
    }



}
