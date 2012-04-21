package edu.asu.cse360.control;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.asu.cse360.model.CreateQuizMod;
import edu.asu.cse360.model.Model;
import edu.asu.cse360.view.CreateQuizView;
import edu.asu.cse360.view.View;
import edu.asu.cse360.view.CreateQuizView.CreateFromBlankView;
import edu.asu.cse360.view.CreateQuizView.ExistingQuizView;


public class CreateQuizCtrl extends Controller {
	

	public CreateQuizCtrl()
	{		
			 VIEW = new CreateQuizView();
			 MODEL = new CreateQuizMod();	
			 
			 
	}
	
	public CreateQuizCtrl(Model mod, View view)
	{
		
		VIEW = view;
		MODEL = mod;
		 ((CreateQuizView)VIEW).addCreateFromExistingButtonListener(new CreateFromExistingButtonListener());
		 ((CreateQuizView)VIEW).addCompleteButtonListener(new CompleteButtonListener());
		 ((CreateQuizView) VIEW).setNumberOfExistQuiz(((CreateQuizMod)MODEL).getNumberOfExistQuizzes());
	}	
	
	
	

	
	
	public class CreateFromExistingButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == ((CreateQuizView) VIEW).getCreateFromExistingButton())
		{
			((CreateQuizView) VIEW).setNumberOfExistQuestions(((CreateQuizMod) MODEL).getNumberOfExistQuestions());
			((CreateQuizView) VIEW).setNumberOfExistQuiz(((CreateQuizMod)MODEL).getNumberOfExistQuizzes());
			passExistQuiz();
			((CreateQuizView) VIEW).initializeE();
					
			CardLayout c = (CardLayout) ((CreateQuizView)VIEW).getOthis().getLayout();
			((CreateQuizView)VIEW).getOthis().add(((CreateQuizView)VIEW).getCreateFromExistPanel(),((CreateQuizView)VIEW).getCARDPANEL2());
			c.show(((CreateQuizView)VIEW).getOthis(),((CreateQuizView)VIEW).getCARDPANEL2());	
							
			}
		}
			
	}
		
	
	public class CompleteButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource() == ((CreateQuizView) VIEW).getCompleteButton())
			{
				
				if(((CreateQuizView) VIEW).getCreateMethod())
					//create from blank quiz
				{
					((CreateQuizView) VIEW).saveQuizFromBlank();
					((CreateQuizMod)MODEL).setCreatedQuiz(((CreateQuizView)VIEW).getCreatedQuiz());
					System.out.println("newly created quiz's question is : "+ ((CreateQuizMod)MODEL).getCreatedQuiz().getContent().get(0).getQuestion());
				}
				else 
					//create from existing quiz
				{
					((CreateQuizView) VIEW).saveQuizFromExist();
					((CreateQuizMod)MODEL).setCreatedQuiz(((CreateQuizView)VIEW).getCreatedQuiz());
					System.out.println("newly created quiz's question is : "+ ((CreateQuizMod)MODEL).getCreatedQuiz().getContent().get(0).getQuestion());
				}
				
			}
		}
		
	}
	
	
	
	
	public void passExistQuiz()
	{
		((CreateQuizView)VIEW).setExistQuiz(((CreateQuizMod)MODEL).getExistQuizList());
		
	}
	
	public void passCreatedQuiz(){
		((CreateQuizMod)MODEL).setCreatedQuiz(((CreateQuizView)VIEW).getCreatedQuiz());
	}

}
