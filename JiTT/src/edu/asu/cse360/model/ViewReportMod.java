<<<<<<< HEAD
package edu.asu.cse360.model;

import edu.asu.cse360.data.*;
import java.util.ArrayList;

public class ViewReportMod extends Model
{
	private Quiz key;
	private Quiz[] studentQuizzes;
	private ArrayList<int[]> studentScores = new ArrayList<int[]>();
	private ArrayList<double[]> answerPercentages;
	
	public ViewReportMod()
    { }
	
	public void getQuizData(String quiz)
	{
		// use String quiz to get instructor key from database
		key = new Quiz();
		
		// use String quiz to get studentQuizzes from database
		studentQuizzes = new Quiz[4];
		studentQuizzes[0] = new Quiz();
		studentQuizzes[1] = new Quiz();
		studentQuizzes[2] = new Quiz();
		studentQuizzes[3] = new Quiz();
	}

	public Quiz getInstructorKey()
	{
		return key;
	}

	public Quiz[] getStudentQuizzes()
	{
		return studentQuizzes;
	}
	
	public void setStudentScores(ArrayList<int[]> s)
	{
		studentScores = s;
	}
	
	public void setAnswerPercentages(ArrayList<double[]> d)
	{
		answerPercentages = d;
	}
	
	public ArrayList<int[]> getStudentScores()
	{
		return studentScores;
	}
	
	public ArrayList<double[]> getAnswerPercentages()
	{
		return answerPercentages;	
	}
}

=======
package edu.asu.cse360.model;

import edu.asu.cse360.data.*;
import java.util.ArrayList;

public class ViewReportMod extends Model
{
	private Quiz key;
	private Quiz[] studentQuizzes;
	private ArrayList<int[]> studentScores = new ArrayList<int[]>();
	private ArrayList<double[]> answerPercentages;
	
	public ViewReportMod()
    { }
	
	public void getQuizData(String quiz)
	{
		// use String quiz to get instructor key from database
		key = new Quiz();
		
		// use String quiz to get studentQuizzes from database
		studentQuizzes = new Quiz[4];
		studentQuizzes[0] = new Quiz();
		studentQuizzes[1] = new Quiz();
		studentQuizzes[2] = new Quiz();
		studentQuizzes[3] = new Quiz();
	}

	public Quiz getInstructorKey()
	{
		return key;
	}

	public Quiz[] getStudentQuizzes()
	{
		return studentQuizzes;
	}
	
	public void setStudentScores(ArrayList<int[]> s)
	{
		studentScores = s;
	}
	
	public void setAnswerPercentages(ArrayList<double[]> d)
	{
		answerPercentages = d;
	}
	
	public ArrayList<int[]> getStudentScores()
	{
		return studentScores;
	}
	
	public ArrayList<double[]> getAnswerPercentages()
	{
		return answerPercentages;	
	}
}

>>>>>>> 740b003273b3064d2e2731216538b2078bc7a60d
