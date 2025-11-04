package com.amn.quiz_service.service;



import com.amn.quiz_service.dao.QuizDao;
//import com.amn.quiz_service.model.Question;
import com.amn.quiz_service.feign.QuizInterface;
import com.amn.quiz_service.model.QuestionWrapper;
import com.amn.quiz_service.model.Quiz;
import com.amn.quiz_service.model.Response;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

//    @Autowired
//    QuestionDao questionDao;
@Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions =quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);
//        RestTemplate

//        List<Integer> questions= //call the generate url -RestTemplate http://localhost:8080/question/generate
//

//

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz= quizDao.findById(id).get();
      List<Integer>questionIds=quiz.getQuestionsIds();
      ResponseEntity<List<QuestionWrapper>>questions=
              quizInterface.getQuestionsFromId(questionIds);

        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {

        ResponseEntity<Integer> score=quizInterface.getScore(response);
        return score;

//        Quiz quiz=quizDao.findById(id).get();
//        List<Question>questions=quiz.getQuestions();
//int right=0;
//int i =0;
//        for(Response response1:response){
//if(response1.getResponse().equals(questions.get(i).getRightanswer()))
//    right++;
//
//i++;
//        }

    }
}
