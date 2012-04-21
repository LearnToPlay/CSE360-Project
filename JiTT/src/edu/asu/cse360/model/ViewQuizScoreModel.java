package edu.asu.cse360.model;

import edu.asu.cse360.data.Answers;
import edu.asu.cse360.data.Quiz;

public class ViewQuizScoreModel extends Model {

	private Quiz quiz;
	private boolean isCorrect;

	
	public boolean isCorrect(int question) {
		return isCorrect;
	}
	
	public int calculatePoints()
	{
		return 0;
	}
	
	public int getTotalPoints()
	{
		return quiz.getNumberOfQuestions();
	}
	
	public String getQuizName()
	{
		return quiz.getQuizName();
	}
	
	public String getInstructions()
	{
		return quiz.getInstructions();
	}
	
	public String getAnswerChoice(Answers question)
	{
		return quiz.getAnswerChoice(question);
	}
	
	public Answers getCorrectAnswer()
	{
		return null;
	}
	
	public Answers getStudentAnswer()
	{
		return null;
	}
	
	public String getQuestion(int num)
	{
		//TODO: get the question at num
		return null;
	}

	public Quiz getQuiz() {
		return quiz;
	}
	
	
}
