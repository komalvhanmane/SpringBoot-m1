package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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

import com.example.demo.model.exam.*;

import com.example.demo.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizservice;

	//add quiz service
	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizservice.addQuiz(quiz));
	}

	//update
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizservice.updateQuiz(quiz));
	}

	//get quiz
	@GetMapping("/")
	public ResponseEntity<?> quizzes(){
		return ResponseEntity.ok(this.quizservice.getQuizzes());
	}

	//get single
	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") int qid) {
		return this.quizservice.getQuiz(qid);
	}

	//delte
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") int qid) {
		System.out.println("dsfkasldfsldfk");
		this.quizservice.deleteQuiz(qid);
	}

	//get quizzess of particular category
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") int cid){
		Category c=new Category();
		c.setCid(cid);
		
		List<Quiz> quizzesOfCategory = this.quizservice.getQuizzesOfCategory(c);
		System.out.println(quizzesOfCategory);
		return quizzesOfCategory;
	}
	
	//get quizzess 
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzess(){
		return this.quizservice.getActiveQuizzes();
	}
	
	//get category active quizzess
	@GetMapping("/active/{cid}")
	public List<Quiz> getActiveCategoryQuizz(Category c){
		return this.quizservice.getActiveQuizzesOfCategory(c);
	}
	
//	@PostMapping("/getScore")
//	public Pair<Integer, Integer> getScore(@RequestBody int arr[],@RequestBody int cid) {
//		Quiz q=this.quizservice.getQuiz(cid);
//		float m=Integer.parseInt(q.getMaxMarks())/Integer.parseInt(q.getNumberOfQuestions());
//		
//		int count=0;
//		
//	}
}
