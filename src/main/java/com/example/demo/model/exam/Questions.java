package com.example.demo.model.exam;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int queId;
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.MERGE)
	private Quiz quiz;
	
	@Column(length=5000)
	private String content;
	
	private String image;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private String answer;
	
	
	public int getQueId() {
		return queId;
	}
	public void setQueId(int queId) {
		this.queId = queId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	@Override
	public String toString() {
		return "Questions [queId=" + queId + ", quiz=" + quiz + ", content=" + content + ", image=" + image
				+ ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
				+ ", answer=" + answer + "]";
	}
	
}
