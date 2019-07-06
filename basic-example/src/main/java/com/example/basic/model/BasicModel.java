package com.example.basic.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Setter
@Getter
@Entity
public class BasicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;


}
