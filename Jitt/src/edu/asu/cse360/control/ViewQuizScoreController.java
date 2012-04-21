package edu.asu.cse360.control;

import java.util.ArrayList;

import edu.asu.cse360.data.QuizContent;
import edu.asu.cse360.model.Model;
import edu.asu.cse360.model.ViewQuizScoreModel;
import edu.asu.cse360.view.View;
import edu.asu.cse360.view.ViewQuizScoreView;


public class ViewQuizScoreController extends Controller {

	private ArrayList<QuizContent> content;
	
	public ViewQuizScoreController (Model model, View view)
	{
		super(model, view);
		content = ((ViewQuizScoreModel) MODEL).getQuiz().getContent();
	}
	
	//TODO: how to use qName?
	public void generateScore(String qName)
	{
		((ViewQuizScoreView) VIEW).setQuizName(((ViewQuizScoreModel) MODEL).getQuizName());
		((ViewQuizScoreView) VIEW).setInstructions(((ViewQuizScoreModel) MODEL).getInstructions());
		
		for(int i=0; i < ((ViewQuizScoreModel)MODEL).getTotalPoints(); ++i)
		{
			((ViewQuizScoreView) VIEW).setQuestionNumber(i+1);

			((ViewQuizScoreView) VIEW).setQuestion(content.get(i).getQuestion());
			
			((ViewQuizScoreView)VIEW).setAstr("a) " + content.get(i).getAnswerA());	
			((ViewQuizScoreView)VIEW).setBstr("b) " + content.get(i).getAnswerB());	
			((ViewQuizScoreView)VIEW).setCstr("c) " + content.get(i).getAnswerC());	
			((ViewQuizScoreView)VIEW).setDstr("d) " + content.get(i).getAnswerD());	
		}
		
		
//		((ViewQuizScoreView)VIEW).showReport();
	}
	

}
