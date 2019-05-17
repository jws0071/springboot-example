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


    //테이블 저장
    @Override
    public BasicModel insert_table(BasicModel e) {
        System.out.println(e);
        return basicRepository.save(e);
    }


    @Override
    public Optional<BasicModel> getStudyTable(Integer id) {

        return basicRepository.findById(id);
    }

    @Override
    public List<BasicModel> getAllStudyTable() {

        System.out.println(basicRepository.findAll());
        return basicRepository.findAll();
    }

    @Override
    public void update_table(BasicModel e) {
        basicRepository.update(e);
        return ;
    }

    @Override
    public void delete_table(BasicModel e) {
        basicRepository.delete(e);

    }

    @Override
    public Page<BasicModel> getAllPageTable(Pageable pageable){

        return basicRepository.findAll(pageable);
    }

}
