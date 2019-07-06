package com.example.basic.service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.basic.model.BasicModel;
import com.example.basic.model.BoardModel;
import com.example.basic.model.CommentModel;
import com.example.basic.repository.BasicRepository;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




@RequiredArgsConstructor
@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CmtRepository cmtRepository;
    private int id;
    private String uText;


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
    public Optional<BoardModel> getBoardTabel(String id) {

        return boardRepository.findById(id);
    }

    @Override
    public List<BoardModel> getBoard() {
        return boardRepository.findAll();
    }

    @Override
    public List<CommentModel> getComment() {
        return cmtRepository.findAll();
    }

    @Override
    public List<CommentModel> postComment(String cText, int bId){
        CommentModel commentModel = new CommentModel();
        BoardModel boardModel = new BoardModel();

        commentModel.setCText(cText);
        boardModel.setBid(bId);

        commentModel.setBoardModel(boardModel);
        cmtRepository.save(commentModel);

       return cmtRepository.findAll();
    }

    @Override
    public void deleteComment(int cId) {
        CommentModel commentModel = new CommentModel();
        commentModel.setCid(cId);

        cmtRepository.delete(commentModel);
    }

    @Override
    public Optional<CommentModel> putComment(int id, String uText) {

        /*
        * findby로  값 호출해서 다시 세팅해서 저장
        * */
        Optional<CommentModel> result = cmtRepository.findByCid(id);
        CommentModel e  = new CommentModel();
        e.setCid(result.get().getCid());
        e.setCText(uText);

        BoardModel boardModel  = new BoardModel();
        boardModel.setBid(result.get().getBoardModel().getBid());
        e.setBoardModel(boardModel);
        cmtRepository.save(e);
        return cmtRepository.findByCid(id);
    }


}
