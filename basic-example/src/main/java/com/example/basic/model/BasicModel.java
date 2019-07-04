package com.example.basic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@ToString
@Entity
public class BasicModel {

    @Id
    @GeneratedValue
    String id;

    @Column
    String name;
}
