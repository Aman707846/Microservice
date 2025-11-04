package com.amntelusko.question_service.controller;


import com.amntelusko.question_service.model.Question;
import com.amntelusko.question_service.model.QuestionWrapper;
import com.amntelusko.question_service.model.Response;
import com.amntelusko.question_service.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin (origins = "http://localhost:3000")
@RestController
@RequestMapping("question")

public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllquestions(){
        return questionService.getAllQuestions();


    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return questionService.addQuestion(question);
    }


    @GetMapping("generate")
    public ResponseEntity<List<Integer>>getQuestionsForQuiz
            (@RequestParam String categoryName,@RequestParam Integer numQuestions){
return  questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
    public  ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer>questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public  ResponseEntity <Integer>getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

    //generate
    //getQuestions(questionid)
    //getScore

}
