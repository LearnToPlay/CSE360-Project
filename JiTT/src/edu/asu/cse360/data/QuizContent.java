package edu.asu.cse360.data;

import java.sql.ResultSet;

public class QuizContent extends SQLEntity {

	private int questionNumber;
	private String question;
	private Answers studentAnswer, correctAnswer;
	
	public QuizContent()
	{
		question = "Type question here...";
	}

	
/*** Getter and Setter ***/
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setStudentAnswer(Answers answerChoice) {
		this.studentAnswer = answerChoice;
	}

	public Answers getStudentAnswer() {
		return studentAnswer;
	}

	public void setCorrectAnswer(Answers correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Answers getCorrectAnswer() {
		return correctAnswer;
	}


	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ResultSet select(String str) {
		// TODO Auto-generated method stub
		return null;
	}
}
