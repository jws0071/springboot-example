package com.example.basic.controller;
import java.util.List;
import java.util.Optional;

import com.example.basic.repository.BasicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.basic.model.BasicModel;
import com.example.basic.service.BasicService;




@Controller
public class BasicController {

    @Autowired
    BasicService basicService;
    BasicRepository basicRepository;

    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);


    //메인 화면
    @RequestMapping(value = "/", method={RequestMethod.GET,RequestMethod.POST})
    public String home() {
        return "home";
    }
    /*
    //사용자 List 화면
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public String list(Model model){
        List<BasicModel> lists=basicService.getAllStudyTable();
        //studyService.getStudyTable(id).ifPresent(o -> model.addAttribute("study", o));
        System.out.println(lists);
        model.addAttribute("lists", lists);
        return "list";
    }
    */
    // List - Paging 처리
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Page<BasicModel> lists = basicRepository.findAll(pageable);
        logger.debug("paging log" + pageable);
        model.addAttribute("lists", lists);

        return "list";
    }


        //사용자 save 화면
        @RequestMapping(value = "/save", method=RequestMethod.POST)
        public String save(){
            return "save";
        }

        //신규 회원 저장
        @RequestMapping(value = "/insert_table", method=RequestMethod.POST)
        public String insert_table(BasicModel basicModel, @RequestParam(value="name1",required=false)String name, Model model) {
        //System.out.println("id : "+ id);
        System.out.println("name : "+ name);
        //studyTable.setId(Integer.parseInt(id));
        basicModel.setName(name);
        basicService.insert_table(basicModel);


        List<BasicModel> lists=basicService.getAllStudyTable();
        System.out.println(lists);
        model.addAttribute("lists", lists);
        return "list";
    }


    //사용자 update 화면 이동시 특정값 호출
    @RequestMapping(value = "/update/{id}",  method={RequestMethod.GET,RequestMethod.POST})
    public String update(Model model,@PathVariable String id ){
        System.out.println(id);
        basicService.getStudyTable(Integer.parseInt(id)).ifPresent(o -> model.addAttribute("get_data", o));

        return "update";
    }
    //사용자 update 저장
    @RequestMapping(value = "/update_table", method=RequestMethod.POST)
    public String update_table(BasicModel basicModel, @RequestParam(value="id1",required=false)String id,@RequestParam(value="name1",required=false)String name, Model model) {
        System.out.println("id : "+ id);
        System.out.println("name : "+ name);
        basicModel.setId(Integer.parseInt(id));
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
        System.out.println("name : "+ name);
        basicModel.setName(name);
        basicService.delete_table(basicModel);
        //System.out.println(studyTable);

        List<BasicModel> lists=basicService.getAllStudyTable();
        System.out.println(lists);
        model.addAttribute("lists", lists);
        return "list";

    }


 /*
    //회원 변경 정보 저장
    @RequestMapping(value = "/update", method=RequestMethod.POST)
    public String update_table(BasicModel basicModel, @RequestParam(value="id1",required=false)String id,
                               @RequestParam(value="name1",required=false)String name, Model model) {
        System.out.println("id : "+ id);
        System.out.println("name : "+ name);
        basicModel.setId(Integer.parseInt(id));
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
*/
/*
		@RequestMapping(value = "/get/{id}", method=RequestMethod.GET)
		public String get(Model model,@PathVariable Integer id) {
			studyService.getStudyTable(id).ifPresent(o -> model.addAttribute("study", o));
			return "list";
		}

		//http://localhost:8080/home/save/test
		@RequestMapping(value = "/save/{id}", method=RequestMethod.GET)
		public String add2(StudyTable studyTable,@PathVariable int id) {
			studyTable.setId(id);
			studyTable.setName("test");
			return "redirect:/home/get/" +  studyService.save(studyTable).getId();
		}

		//http://localhost:8080/home/delete/1
		@RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
		public String delete(@PathVariable Integer id) {
			studyService.delete(id);
			return "redirect:/home/";
		}

		//http://localhost:8080/home/getall.do
		@RequestMapping(value = "/getAll", method=RequestMethod.GET)
		public String getAll(Model model){
			List study = studyService.getAllStudyTable();

			model.addAttribute("study", study);
			return "home";
		}






	@RequestMapping("/write")
	public String write(StudyTable studyTable) {
		//studyTable.setId(id);
		studyTable.setName("test");
		return "redirect:/home/" + studyRepository.save(studyTable).getId();
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<StudyTable> studyList = studyRepository.findAll();
		model.addAttribute("postList", studyList);
		return "home";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		StudyTable study = studyRepository.findById(id);
		model.addAttribute("study", study);
		return "home";
	}

	*/






}


