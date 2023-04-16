package com.example.demo.services.Implementaion;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.exam.Questions;
import com.example.demo.model.exam.Quiz;
import com.example.demo.repository.QuestionRepositeory;
import com.example.demo.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepositeory questionRepo;
	
	@Override
	public Questions addQuestion(Questions question) {
		// TODO Auto-generated method stub
		System.out.println("H2");
		System.out.println(question);
		return this.questionRepo.save(question);
	}

	@Override
	public Questions updateQuestion(Questions question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(question);
	}

	@Override
	public Set<Questions> getALlQuestion() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.questionRepo.findAll());
	}

	@Override
	public Questions getQuestion(int quesId) {
		// TODO Auto-generated method stub
		System.out.println(quesId);
		return this.questionRepo.findById(quesId).get();
	}

	@Override
	public Set<Questions> getQuestionOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(int id) {
		// TODO Auto-generated method stub
		this.questionRepo.deleteById(id);
	}
	
}
