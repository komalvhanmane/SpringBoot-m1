package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.exam.Questions;
import com.example.demo.model.exam.Quiz;
import com.example.demo.model.exam.Result;
import com.example.demo.services.QuestionService;
import com.example.demo.services.QuizService;
import com.example.demo.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionservice;
	
	@Autowired
	private QuizService quizservice;
	
	@Autowired
	private UserService userservice;
	
	//add
	@PostMapping("/")
	public ResponseEntity<Questions> add(@RequestBody Questions question) {
		System.out.println("H1");
		return ResponseEntity.ok(this.questionservice.addQuestion(question));
	}
	
	//update
	@PutMapping("/")
	public ResponseEntity<Questions> update(@RequestBody Questions question){
		System.out.println("Hello");
		System.out.println(question.getQueId()+" "+question.getContent()+" "+question.getOption1());
		return ResponseEntity.ok(this.questionservice.updateQuestion(question));
	}
	
	//get all quiz question
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") int qid){
//		Quiz q1=new Quiz();
//		q1.setqId(qid);
//		return ResponseEntity.ok(this.questionservice.getQuestionOfQuiz(q1));
		//here we get all questions but we want only limited questions
		Quiz quiz=this.quizservice.getQuiz(qid);
		Set<Questions> questions = quiz.getQuestions();
		List<Questions> lst=new ArrayList<>(questions);
		if(lst.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			lst=lst.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
		}
		
		for(int i=0;i<lst.size();i++) {
			lst.get(i).setAnswer(null);
		}
		Collections.shuffle(lst);
		return ResponseEntity.ok(lst);
		
	}
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") int qid){
		Quiz q1=new Quiz();
		q1.setqId(qid);
		return ResponseEntity.ok(this.questionservice.getQuestionOfQuiz(q1));
	}
	
	
	//get single question
	@GetMapping("/{queId}")
	public Questions get(@PathVariable("queId") int queId) {
		return this.questionservice.getQuestion(queId);
	}
	
	//delete
	@DeleteMapping("/{queId}")
	public void delete(@PathVariable("queId") int queId)
	{
		this.questionservice.deleteQuestion(queId);
	}
	
	@PostMapping("/eval-quiz/{qId}")
	public Result evaluate(@RequestBody List<Questions> lst,@PathVariable int qId) {
		System.out.println(lst);
		int correctAnswer = 0;
		int attempted = 0;
		int totalQuestion =0;
		List<String> lst1=new LinkedList<>();
		for(int i=0;i<lst.size();i++) {
			lst1.add(this.questionservice.getQuestion(lst.get(i).getQueId()).getAnswer());
			System.out.println(lst1);
			if(lst.get(i).getAnswer()!=null) {
				System.out.println(lst.get(i).getAnswer()+" "+this.questionservice.getQuestion(lst.get(i).getQueId()).getAnswer());
				if(lst.get(i).getAnswer().equals(this.questionservice.getQuestion(lst.get(i).getQueId()).getAnswer())) {
					correctAnswer++;
				}
				attempted++;
			}
		}
		totalQuestion=lst.size();
		System.out.println(correctAnswer+" "+attempted);
		System.out.println(lst1);
		Result r=new Result(attempted, correctAnswer,totalQuestion,lst1);
		return r;
	}
}
