package com.example.basic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
public class BasicModel {

    @Id
    @GeneratedValue
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "basicModel")
    private List<BoardModel> boardModel = new ArrayList<>();
}
