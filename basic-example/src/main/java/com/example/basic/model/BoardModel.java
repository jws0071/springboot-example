package com.example.basic.model;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public  class BoardModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @Column
    private String bName;


    @OneToOne
    @JoinColumn(name="BasicModel_id")
    private BasicModel basicModel;

    @OneToMany(mappedBy="boardModel")
    private List<CommentModel> commentModels = new ArrayList<CommentModel>();

    public void addBasicModels(CommentModel commentModel) {
        this.commentModels.add(commentModel);
    }


}

