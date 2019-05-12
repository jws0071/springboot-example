package com.example.basic.service;

import com.example.basic.model.BasicModel;
import com.example.basic.repository.BasicRepository;
import com.example.basic.service.BasicService;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert.*;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.NONE)
public class BasicServcieTest {
    @Autowired
    BasicService basicService;

    @Test
    public void testgetStudyTable() {
        try {
            int id = 1;
            Optional<BasicModel> result = basicService.getStudyTable(id);
            System.out.println(result);

        } catch (RuntimeException e) {
            Assert.assertNotNull("NULL",e.getMessage());
        }
    }

    @Test
    public void testgetAllStudyTable() {
        try {
            List<BasicModel> result = basicService.getAllStudyTable();
            System.out.println(result);

        } catch (RuntimeException e) {
            Assert.assertNotNull("NULL",e.getMessage());
        }
    }

    @Test
    public void testinsert_table() {
        try {

            BasicModel basicModel = new BasicModel();
            int id = 3;
            String name ="TEST";
            basicModel.setId(id);
            basicModel.setName(name);


            basicService.insert_table(basicModel);

            Optional<BasicModel> result = basicService.getStudyTable(id);
            System.out.println(result);

        } catch (RuntimeException e) {
            Assert.assertNotNull("NULL",e.getMessage());
        }
    }

    @Test
    public void testupdate_table() {
        try {
            BasicModel basicModel = new BasicModel();
            int id = 1;
            String name ="TEST";
            Optional<BasicModel> result = basicService.getStudyTable(id);
            System.out.println(result);

            basicModel.setId(id);
            basicModel.setName(name);
            basicService.update_table(basicModel);

            Optional<BasicModel> result_1 = basicService.getStudyTable(id);
            System.out.println(result_1);

        } catch (RuntimeException e) {
            Assert.assertNotNull("NULL",e.getMessage());
        }
    }

    @Test
    public void testdelete_table() {
        try {
            BasicModel basicModel = new BasicModel();
            int id = 2;
            String name = "홍길동";
            Optional<BasicModel> result = basicService.getStudyTable(id);
            System.out.println(result);

            basicModel.setName(name);
            basicService.delete_table(basicModel);
            Optional<BasicModel> result_1 = basicService.getStudyTable(id);
            System.out.println(result_1);

        } catch (RuntimeException e) {
            Assert.assertNotNull("NULL",e.getMessage());
        }
    }
}
