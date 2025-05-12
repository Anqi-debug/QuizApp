package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.dao.QuestionDao;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        if (questions.isEmpty()) {
            return new ResponseEntity<>("No questions found for category: " + category, HttpStatus.NOT_FOUND);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        Quiz savedQuiz = quizDao.save(quiz);

        return new ResponseEntity<>("Success" + savedQuiz.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();

        if (quiz == null) {
            return ResponseEntity.badRequest().body(0); // Handle invalid quiz ID
        }

        List<Question> questions = quiz.getQuestions();
        int right = 0;

        // Convert questions into a Map for fast lookup by ID
        HashMap<Integer, String> correctAnswers = new HashMap<>();
        for (Question q : questions) {
            correctAnswers.put(q.getId(), q.getRightAnswer());
        }

        // Compare responses against correct answers using ID matching
        for (Response response : responses) {
            if (correctAnswers.containsKey(response.getId()) &&
                correctAnswers.get(response.getId()).equals(response.getResponse())) {
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
