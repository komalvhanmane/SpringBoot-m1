package com.example.demo.services;

import java.util.List;
import java.util.Set;


import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;

public interface QuizService {
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizzes();
	public Quiz getQuiz(int quizId);
	public void deleteQuiz(int quizId);
	public List<Quiz> getQuizzesOfCategory(Category c);
	
	public List<Quiz> getActiveQuizzes();
	public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
