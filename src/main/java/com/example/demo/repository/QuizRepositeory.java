package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;

public interface QuizRepositeory extends JpaRepository<Quiz,Integer>{
	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
}
