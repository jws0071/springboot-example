package com.example.basic.controller;

import com.example.basic.model.BasicModel;
import com.example.basic.repository.BasicRepository;
import com.example.basic.service.BasicService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class BasicControllerTest {


    private MockMvc mockMvc;

    @MockBean
    BasicController basicController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(basicController).build();
    }


    @Test
    public void getBasicTest() throws Exception {

        mockMvc.perform(get("/list"))
                .andReturn();
                //.andExpect(status().isOk())
                //.andExpect(jsonPath("$['title']", containsString("Homes")))
                //.andDo(print());
    }


}
