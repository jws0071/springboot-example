package com.example.basic.model;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

