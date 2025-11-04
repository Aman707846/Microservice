package com.amn.quiz_service.controller;



import com.amn.quiz_service.model.QuestionWrapper;
import com.amn.quiz_service.model.QuizDto;
import com.amn.quiz_service.model.Response;
import com.amn.quiz_service.service.QuizService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    Environment environment;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz (@RequestBody QuizDto quizDto ){
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
//        return new ResponseEntity<>("Imm here",HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(@PathVariable Integer id){
        System.out.println(environment.getProperty("local.server.port"));
        return quizService.getQuizQuestions(id);
    }



    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.calculateResult(id, response);
    }

}
