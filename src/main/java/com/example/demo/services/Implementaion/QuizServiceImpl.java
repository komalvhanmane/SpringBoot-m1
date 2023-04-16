package com.example.demo.services.Implementaion;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;
import com.example.demo.repository.QuizRepositeory;
import com.example.demo.services.QuizService;

import net.bytebuddy.asm.Advice.Return;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	private QuizRepositeory quizrepo;
	
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizrepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizrepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.quizrepo.findAll());
	}

	@Override
	public Quiz getQuiz(int quizId) {
		// TODO Auto-generated method stub
		return this.quizrepo.findById(quizId).get();
		
	}

	@Override
	public void deleteQuiz(int quizId) {
		// TODO Auto-generated method stub
//		Quiz quiz=new Quiz();
//		quiz.setqId(quizId);
//		System.out.println(quiz.getqId());
		this.quizrepo.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizrepo.findByCategory(c);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizrepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizrepo.findByCategoryAndActive(c, true);
	}

}
