package edu.asu.cse360.control;

import java.util.ArrayList;

import edu.asu.cse360.model.*;
import edu.asu.cse360.view.*;
import edu.asu.cse360.data.*;

public class ViewReportCtrl extends Controller
{
	public ViewReportCtrl()
	{
		VIEW = new ViewReportView();
		MODEL = new ViewReportMod();
	}
	
	public ViewReportCtrl(Model mod, View view)
	{
		super(mod, view);
	}
	
	public void gatherAnswers()//Quiz key, Quiz studentQuiz)
	{
		Quiz key = ((ViewReportMod) MODEL).getInstructorKey();
		Quiz[] studentQuizzes = ((ViewReportMod) MODEL).getStudentQuizzes();
		
		ArrayList<int[]> scores = new ArrayList<int[]>();
		// needs to be unit-tested
		/*for(int i=0; i<key.getNumberOfQuestions(); i++)
		{
			int a=0, b=0, c=0, d=0;
			for(int j=0; j<studentQuizzes.length; j++)
			{
				// ArrayList<QuizContent> current = studentQuizzes[j].getContent();
				switch(studentQuizzes[j].getContent().get(i).getStudentAnswer())
				{
				case A: a++; break;
				case B: b++; break;
				case C: c++; break;
				case D: d++; break;
				default: break;
				}
			}
			int[] temp = {a, b, c, d};
			scores.add(temp);
		}*/
		int[] temp = {1,2,4,3};
		scores.add(temp);
		((ViewReportMod) MODEL).setStudentScores(scores);
	}
	
	public void calculatePercentages()//ArrayList<int[4]> studentScores, int total)
	{
		ArrayList<int[]> studentScores = ((ViewReportMod) MODEL).getStudentScores();
		
		// TODO: Logic for calculating Percentages.
		// this information will be used for creating the bar graphs in View
		ArrayList<double[]> answerPercentages = new ArrayList<double[]>();
		/*int numberOfStudents = ((ViewReportMod) MODEL).getStudentQuizzes().length;
		double[] percents = new double[4];
		for(int i=0; i<studentScores.size(); i++)
		{
			int[] temp = studentScores.get(i);
			for(int j=0; j<4; j++)
				percents[j] = temp[j]/numberOfStudents;
			answerPercentages.add(percents);
		}*/
		
		double[] percents = {2.33, 22.8, 70.33, 3.02, 1.52};
		answerPercentages.add(percents);
		
		// set answerPercentages ArrayList<double[]> in Model
		((ViewReportMod) MODEL).setAnswerPercentages(answerPercentages);
	}
	
	public void generateReport(String ReportName)
	{
		// set the quiz data in model using String ReportName
		((ViewReportMod) MODEL).getQuizData(ReportName);
		
		// logic for setting studentScores and answerPercentages
		gatherAnswers();
		calculatePercentages();
		
		// logic for View's showReport()
    	((ViewReportView) VIEW).setReportName(ReportName);
    	((ViewReportView) VIEW).showReport(
    			((ViewReportMod) MODEL).getInstructorKey(),
    			((ViewReportMod) MODEL).getAnswerPercentages()
    			);
	}
}