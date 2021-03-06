package com.example.basic.controller;
import java.util.List;
import java.util.Map;

import com.example.basic.model.CommentModel;
import com.example.basic.model.DslModel;

import com.example.basic.model.SearchData;
import com.example.basic.service.DslService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.basic.model.BasicModel;
import com.example.basic.service.BasicService;


@Controller
public class BasicController {

    @Autowired
    BasicService basicService;

    @Autowired
    DslService dslService;

    //메인 화면
    @RequestMapping(value = "/", method={RequestMethod.GET,RequestMethod.POST})
    public String home() {
        return "home";
    }


    // List - JPA Paging 처리
    @RequestMapping(value = "/list" )
    public String list(Model model,@PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable){
        Page<BasicModel> lists = basicService.getAllPageTable(pageable);
        model.addAttribute("lists", lists);
        model.addAttribute("searchdata", new SearchData());
        return "list";
    }

    // List - JPA 검색기능
    @RequestMapping(value = "/search",method={RequestMethod.GET,RequestMethod.POST})
    public String search(Model model,@ModelAttribute SearchData searchdata, @PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable){

        //flag ID : 1 , Name : 2 , ID+NAME : 3
        System.out.println("SearchData_flag = " + searchdata.getFlag_info() + " SearchData_info = " +searchdata.getSearch_info());
        String flag_info = searchdata.getFlag_info();
        String search_info = searchdata.getSearch_info();
        Page<BasicModel> lists = basicService.getSearchPageTable(flag_info,search_info,pageable);
        model.addAttribute("lists", lists);
        model.addAttribute("searchdata", searchdata);
        return "list";
    }

    // List_2 - QueryDSL Paging 처리
    @RequestMapping(value = "/list_2")
    public String list_2(Model model, @PageableDefault(size = 5)  Pageable pageable){
        System.out.println("controller");
        Page<DslModel >lists = dslService.getAllPageTable(pageable);
        model.addAttribute("lists", lists);
        model.addAttribute("searchdata", new SearchData());
        return "list_2";
    }

    // List_2 - QueryDSL 검색기능
    @RequestMapping(value = "/search_2",method={RequestMethod.GET,RequestMethod.POST})
    public String search_2(Model model,@ModelAttribute SearchData searchdata, @PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable){

        //flag ID : 1 , Name : 2 , ADDRESS : 3 ,  ID+NAME+ADDRESS : 4
        System.out.println("SearchData_flag = " + searchdata.getFlag_info() + " SearchData_info = " +searchdata.getSearch_info());
        String flag_info = searchdata.getFlag_info();
        String search_info = searchdata.getSearch_info();
        Page<DslModel> lists = dslService.getSearchPageTable_2(flag_info,search_info,pageable);
        model.addAttribute("lists", lists);
        model.addAttribute("searchdata", searchdata);
        return "list_2";
    }


    //사용자 save 화면
    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String save(){
        return "save";
    }

        //신규 회원 저장
    @RequestMapping(value = "/insert_table", method=RequestMethod.POST)
        public String insert_table(BasicModel basicModel, @RequestParam(value="name1",required=false)String name, Model model) {
        basicModel.setName(name);
        List<BasicModel> lists = basicService.insert_table(basicModel);
        model.addAttribute("lists", lists);
        return "list";
    }


    //사용자 update 화면 이동시 특정값 호출
    @RequestMapping(value = "/update/{id}",  method={RequestMethod.GET,RequestMethod.POST})
    public String update(Model model,@PathVariable String id ){
        System.out.println(id);
        basicService.getStudyTable(id).ifPresent(o -> model.addAttribute("get_data", o));

        return "update";
    }
    //사용자 update 저장
    @RequestMapping(value = "/update_table", method=RequestMethod.POST)
    public String update_table(BasicModel basicModel, @RequestParam(value="id1",required=false)String id,@RequestParam(value="name1",required=false)String name, Model model) {
        //basicModel.setId(id);
        basicModel.setName(name);
        basicService.update_table(basicModel);
        //System.out.println(studyTable);

        List<BasicModel> lists=basicService.getAllStudyTable();
        System.out.println(lists);
        model.addAttribute("lists", lists);
        return "list";
    }

    //사용자 delete 화면
    @RequestMapping(value = "/delete", method=RequestMethod.POST)
    public String delete(){
        return "delete";
    }

    //사용자 delete 완료
    @RequestMapping(value = "/delete_table", method=RequestMethod.POST)
    public String delete(BasicModel basicModel, @RequestParam(value="name1",required=false)String name, Model model) {
        basicModel.setName(name);
        basicService.delete_table(basicModel);

        List<BasicModel> lists=basicService.getAllStudyTable();
        model.addAttribute("lists", lists);
        return "list";

    }

    //게시판 메인 내용
    @GetMapping(value = "/board/{id}"  )
    public String board(Model model,@PathVariable String id ){
        System.out.println(id);
        basicService.getStudyTable(id).ifPresent(o -> model.addAttribute("get_data", o));

        return "board";
    }
    //댓글 호출
    @GetMapping(value = "/comment/{id}"  )
    public String commentSelect(Model model,@PathVariable String id ){
        System.out.println(id);
        basicService.getStudyTable(id).ifPresent(o -> model.addAttribute("get_data", o));

        return "board";
    }
    //댓글 호출
    @GetMapping(value = "/commentAll"  )
    public String commentSelectAll(Model model ){
        List<CommentModel> result = basicService.getComment();
        model.addAttribute("result", result);
        for(int i =0;i<result.size();i++){
            System.out.println(result.get(i).getCid());
            System.out.println(result.get(i).getCText());
            System.out.println(result.get(i).getBoardModel().getBid());
            System.out.println(result.get(i).getBoardModel().getBName());
        }
        return "board";
    }
    //댓글 추가
    @PostMapping(value = "/comment"  )
    public String commentInsert(Model model,@RequestParam(value="cText",required=false)String cText,@RequestParam(value="bId",required=false)int bId){
        List<CommentModel> result = basicService.postComment(cText,bId);
        model.addAttribute("result", result);
        return "board";
    }
    //댓글 업데이트
    @PutMapping(value = "/comment"  )
    public String commentUpdate(Model model,@RequestParam(value="cId",required=false)int cId,@RequestParam(value="cText",required=false)String cText){

        basicService.putComment(cId,cText).ifPresent(o -> model.addAttribute("getCommentUp", o));

        return "board";
    }

    //댓글 삭제
    @DeleteMapping(value = "/comment"  )
    public String commentDelete(Model model,@RequestParam(value="cId",required=false)int cId){

        basicService.deleteComment(cId);

        model.addAttribute("result","삭제완료");
        return "board";
    }


}


