package edu.asu.cse360.control;

import edu.asu.cse360.model.Model;
import edu.asu.cse360.model.ViewQuizScoreModel;
import edu.asu.cse360.view.View;
import edu.asu.cse360.view.ViewQuizScoreView;


public class ViewQuizScoreController extends Controller {

	public ViewQuizScoreController (Model model, View view)
	{
		super(model, view);
	}
	
	//TODO: how to use qName?
	public void generateScore(String qName)
	{
		((ViewQuizScoreView) VIEW).setQuizName(((ViewQuizScoreModel) MODEL).getQuizName());
		((ViewQuizScoreView) VIEW).setInstructions(((ViewQuizScoreModel) MODEL).getInstructions());
		
		for(int i=0; i < ((ViewQuizScoreModel)MODEL).getTotalPoints(); ++i)
		{
			((ViewQuizScoreView) VIEW).setQuestionNumber(i+1);

			//((ViewQuizScoreView) VIEW).setQuestion(((ViewQuizScoreModel) MODEL).getQuestion(i+1));


			((ViewQuizScoreView) VIEW).setQuestion(((ViewQuizScoreModel) MODEL).getQuestion(i+1));

			
			
		}
	}
	

}
