package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_user;
    private String text;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<QuestionResponse> questions;
}
