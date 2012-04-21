package edu.asu.cse360.data;

import java.sql.ResultSet;

public class QuizContent extends SQLEntity {

	private int questionNumber;
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private Answers studentAnswer, correctAnswer;
	
	public QuizContent()
	{
		question = "Type question here...";
		question = new String();
		answerA = new String();
		answerB = new String();
		answerC = new String();
		answerD = new String();
//		correctAnswer = new Answers();
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
	public void setAnswerA(String answer) {
		this.answerA = answer;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerB(String answer) {
		this.answerB = answer;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerC(String answer) {
		this.answerC = answer;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerD(String answer) {
		this.answerD = answer;
	}
	public String getAnswerD() {
		return answerD;
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



/*** SQL Methods ***/

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
