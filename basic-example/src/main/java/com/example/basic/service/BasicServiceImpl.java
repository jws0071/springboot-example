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
    public Optional<BasicModel> getStudyTable(Integer id) {

        return basicRepository.findById(id);
    }

    //JPA 사용
    @Override
    public List<BasicModel> getAllStudyTable() {

        return basicRepository.findAll();
    }

    //QueryDSL 사용
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

}
