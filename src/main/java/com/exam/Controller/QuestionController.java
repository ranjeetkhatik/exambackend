package com.exam.Controller;

import com.exam.Services.QuestionService;
import com.exam.Services.QuizService;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizId") Long quizId) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(quizId);
//        return ResponseEntity.ok(this.questionService.getQuestionOfQuiz(quiz));
         Quiz quiz= this.quizService.getQuiz(quizId);
         Set<Question> questions= quiz.getQuestions();

        List list=new ArrayList(questions);

        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()))
        {
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get single question
    @GetMapping("/{qustionId}")
    public ResponseEntity<?> getQuestion(@PathVariable("questionId") Long questionId)
    {
        return ResponseEntity.ok(this.questionService.getQuestion(questionId));
    }

    //delete question
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId){
        this.questionService.deleteQuestion(questionId);
    }
}
