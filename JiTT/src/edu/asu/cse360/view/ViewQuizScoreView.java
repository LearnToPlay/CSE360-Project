package edu.asu.cse360.view;

import java.awt.Color;
import javax.swing.JLabel;

public class ViewQuizScoreView extends View {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	final private Color right = Color.green, wrong = Color.red;
	
	private JLabel earnedPoints, totalPoints, quizName, instructions,
					questionNumber, question, ABCorD, studentAnswer;
	
	
	
/*** Setters ***/
	
	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints.setText(Integer.toString(earnedPoints));
	}


public void setTotalPoints(int totalPoints) {
	this.totalPoints.setText(Integer.toString(totalPoints));
}


public void setQuizName(String name) {
	this.quizName.setText(name);
}


public void setInstructions(String instructions) {
	this.instructions.setText(instructions);
}


public void setQuestionNumber(int questionNumber) {
	this.questionNumber.setText(Integer.toString(questionNumber));
}


public void setQuestion(String question) {
	this.question.setText(question);
}


public void setABCorD(char ABCorD) {
	this.ABCorD.setText(Character.toString(ABCorD));
}


public void setStudentAnswer(char studentAnswer) {
	this.studentAnswer.setText(Character.toString(studentAnswer));
}


}
