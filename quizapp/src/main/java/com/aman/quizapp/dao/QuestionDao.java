package com.aman.quizapp.dao;


import com.aman.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao  extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);


    @Query(value = "SELECT * FROM quiz_db q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true )
    List<Question> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") int numQ);
//List<Question>findRandomQuestionsByCategory(String category, int numQ);
}
