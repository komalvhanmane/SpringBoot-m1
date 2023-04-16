package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.exam.Questions;
import com.example.demo.model.exam.Quiz;

public interface QuestionRepositeory extends JpaRepository<Questions,Integer>{

	Set<Questions> findByQuiz(Quiz quiz);
	
	

}
