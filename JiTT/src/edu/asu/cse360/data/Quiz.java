package edu.asu.cse360.data;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Quiz extends SQLEntity {

	private String quizName, courseName, instructions;
	private int numberOfQuestions;
	private Timestamp open, close;
	private ArrayList<QuizContent> content = new ArrayList<QuizContent>();
	
	public Quiz()
	{
		quizName = "New Quiz";
		courseName = "New Course";
		instructions = "Read the following questions and click on a response. " +
				"You have 20 minutes to complete the quiz.";			
	}
	
	public void addContent()
	{
		
	}
	
	public void deleteContent()
	{
		
	}
	
/*** Getter and Setters ***/

	public void setQuizName(String quizName) {
		this.quizName = quizName; //This might be extended to include the course name and such.
	}

	public String getQuizName() {
		return quizName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setOpen(Timestamp open) {
		this.open = open;
	}

	public Timestamp getOpen() {
		return open;
	}

	public void setClose(Timestamp close) {
		this.close = close;
	}

	public Timestamp getClose() {
		return close;
	}

	/** setContent and getContent may need to be revised to
	 ** getting and setting a specific index of content.	
	 ** @param content
	 */
	public void setContent(ArrayList<QuizContent> content) {
		this.content = content;
	}

	public ArrayList<QuizContent> getContent() {
		return content;
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
