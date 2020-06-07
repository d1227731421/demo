package com.example.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author dsr
 * @date 2020-05-10
 **/
@Entity
@Table(name = "question")
@Setter
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "text",length = 1024)
    private String text;
    @Column(name = "phone",length = 11)
    private String phone;
}
