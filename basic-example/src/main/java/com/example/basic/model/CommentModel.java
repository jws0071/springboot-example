package com.example.basic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @Column
    private String cText;

//    @CreationTimestamp
//    private LocalDateTime cDate;

    @ManyToOne
    @JoinColumn(name="BoardModel_id")
    private  BoardModel boardModel;

    public void setBoardModel(BoardModel boardModel) {
        if(this.boardModel != null)
            this.boardModel.getCommentModels().remove(this);
        this.boardModel = boardModel;
        boardModel.getCommentModels().add(this);

    }





}
