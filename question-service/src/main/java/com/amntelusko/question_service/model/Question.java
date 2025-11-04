package com.amntelusko.question_service.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder

@Table(name = "quiz_db")

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String questiontitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightanswer;
    private String difficultyLevel;
    private String  category;



}
