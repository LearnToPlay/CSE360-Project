package edu.asu.cse360.model;

import java.util.ArrayList;

import edu.asu.cse360.data.*;

public class CreateQuizMod extends Model{
	
Quiz[] existQuizList;
Quiz createdQuiz;

QuizContent[] existQuizC;
ArrayList<QuizContent> existQuizContent;
ArrayList<QuizContent> createdQuizContent;
int numberOfExistQuiz;
int numberOfExistQuestions;
int numberOfQuestions;

public CreateQuizMod(){
	
	
	//for testing!!
	numberOfExistQuiz = 7;
	numberOfExistQuestions = 5;

	existQuizList = new Quiz[numberOfExistQuiz];
	existQuizContent = new ArrayList<QuizContent>(numberOfExistQuiz);
	createdQuizContent = new ArrayList<QuizContent>();
	existQuizC = new QuizContent[numberOfExistQuestions];
	
	for(int i=0; i<numberOfExistQuiz; i++)
	{
		existQuizList[i] = new Quiz();
	}
	for(int i=0; i<numberOfExistQuestions; i++)
	{
		
		existQuizC[i] = new QuizContent();
		existQuizC[i].setQuestion("How do you turn 2 into 5? "+ "(question " +(i+1) + " )");
		existQuizC[i].setAnswerA("plus 3");
		existQuizC[i].setAnswerB("multiply 2.5");
		existQuizC[i].setAnswerC("Turn it upside down and look at it in a mirror.");
		existQuizC[i].setAnswerD("plus -2");

		
	}
	//whole for loop is for testing!!!!!!---> initialize quiz and quiz content
	for(int i=0 ; i<numberOfExistQuiz; i++)
	{
		existQuizList[i].setQuizName("Quiz " + (i+1));
		existQuizList[i].setCourseName("CSE 360");
//		existQuizContent.add(i,existQuizC[i]);

	}
	
	//whole for loop is for testing!!!!!! 
	for(int i = 0; i<numberOfExistQuestions; i++)
	{
		existQuizContent.add(i,existQuizC[i]);

	}
	for(int i = 0; i<numberOfExistQuestions; i++)
	{

		existQuizList[i].setContent(existQuizContent);
	}
	
	
}

	public int getNumberOfExistQuestions(){
		return numberOfExistQuestions;
	}
	public int getNumberOfExistQuizzes(){
		return numberOfExistQuiz;
	}
	public Quiz[] getExistQuizList()
	{
		// get the list of existing quizzes into existQuizList		
		return existQuizList;
	}
	
	public Quiz getCreatedQuiz()
	{
		return createdQuiz;
	}
	public void setCreatedQuiz(Quiz quiz)
	{
		createdQuiz = quiz;
	}
	
	
	
}
