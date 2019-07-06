package com.example.basic.service;

import com.example.basic.model.BasicModel;
import com.example.basic.model.BoardModel;

import com.example.basic.model.CommentModel;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.NONE)
public class BasicServcieTest {
    @Autowired
    BasicService basicService;

//    @Test
//    public void testgetStudyTable() {
//        try {
//            String id = "1";
//            Optional<BasicModel> result = basicService.getStudyTable(id);
//            System.out.println(result);
//
//        } catch (RuntimeException e) {
//            Assert.assertNotNull("NULL",e.getMessage());
//        }
//    }
//
//    @Test
//    public void testgetAllStudyTable() {
//        try {
//            List<BasicModel> result = basicService.getAllStudyTable();
//            System.out.println(result);
//
//        } catch (RuntimeException e) {
//            Assert.assertNotNull("NULL",e.getMessage());
//        }
//    }
//
//    @Test
//    public void testinsert_table() {
//        try {
//
//            BasicModel basicModel = new BasicModel();
//            String id = "1";
//            String name ="TEST";
//            basicModel.setId(id);
//            basicModel.setName(name);
//
//
//            basicService.insert_table(basicModel);
//
//            Optional<BasicModel> result = basicService.getStudyTable(id);
//            System.out.println(result);
//
//        } catch (RuntimeException e) {
//            Assert.assertNotNull("NULL",e.getMessage());
//        }
//    }
//
//    @Test
//    public void testupdate_table() {
//        try {
//            BasicModel basicModel = new BasicModel();
//            String id = "1";
//            String name ="TEST";
//            Optional<BasicModel> result = basicService.getStudyTable(id);
//            System.out.println(result);
//
//            basicModel.setId(id);
//            basicModel.setName(name);
//            basicService.update_table(basicModel);
//
//            Optional<BasicModel> result_1 = basicService.getStudyTable(id);
//            System.out.println(result_1);
//
//        } catch (RuntimeException e) {
//            Assert.assertNotNull("NULL",e.getMessage());
//        }
//    }
//
//    @Test
//    public void testdelete_table() {
//        try {
//            BasicModel basicModel = new BasicModel();
//            String id = "1";
//            String name = "홍길동";
//            Optional<BasicModel> result = basicService.getStudyTable(id);
//            System.out.println(result);
//
//            basicModel.setName(name);
//            basicService.delete_table(basicModel);
//            Optional<BasicModel> result_1 = basicService.getStudyTable(id);
//            System.out.println(result_1);
//
//        } catch (RuntimeException e) {
//            Assert.assertNotNull("NULL",e.getMessage());
//        }
//    }


    @Test
    public void Select_Board_Test() {
        List<BoardModel> result = basicService.getBoard();
        for(int i =0;i<result.size();i++){
            System.out.println(result.get(i).getBid());
            System.out.println(result.get(i).getBName());
            System.out.println(result.get(i).getBasicModel().getId());
            System.out.println(result.get(i).getBasicModel().getName());
        }
    }

    @Test
    public void Select_Comment_Test() {
        List<CommentModel> result = basicService.getComment();
        for(int i =0;i<result.size();i++){
            System.out.println(result.get(i).getCid());
            System.out.println(result.get(i).getCText());
            System.out.println(result.get(i).getBoardModel().getBid());
            System.out.println(result.get(i).getBoardModel().getBName());
        }

    }
    @Test
    public void Post_Comment_Test() {

        basicService.postComment("comment test4",1);


    }
    @Test
    public void Delete_Comment_Test() {

        basicService.deleteComment(1);


    }

    @Test
    public void Put_Comment_Test() {

        basicService.putComment(1, "comment test_update");
        List<CommentModel> result = basicService.getComment();
        for(int i =0;i<result.size();i++){
            System.out.println(result.get(i).getCid());
            System.out.println(result.get(i).getCText());
            System.out.println(result.get(i).getBoardModel().getBid());
            System.out.println(result.get(i).getBoardModel().getBName());
        }

    }

}
