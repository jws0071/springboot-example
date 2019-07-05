package com.example.basic.model;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Getter
@Setter
@ToString
@Entity
public  class BoardModel{
    @Id
    private String id;

    @Column
    private String boardtext;

    @ManyToOne
    @JoinColumn(name = "BasicModel_id")
    private BasicModel basicModel;
}

