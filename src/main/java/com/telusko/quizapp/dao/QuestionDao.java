package com.telusko.quizapp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telusko.quizapp.model.Question;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
    List<Question> findByCategory(String category);
    
    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
} 