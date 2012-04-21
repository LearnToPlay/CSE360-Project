<<<<<<< HEAD
package edu.asu.cse360.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class CreateQuizView extends JPanel{
	JButton createFromBlankButton;
	JButton createFromExistingButton;
	
	JButton cancelButton1 = new JButton("Cancel");
	JButton cancelButton2 = new JButton("Cancel");
	JButton cancelButton3 = new JButton("Cancel");	
	JButton nextButton = new JButton("Next");
	JButton backButton = new JButton("Back");
	JButton completeButton = new JButton("Complete");
	JButton okButton = new JButton("OK");
	JButton nextPageButton = new JButton("Next Page");
	JButton previousPageButton = new JButton("Previous Page");

	private JPanel createQuizPanel;
	private JPanel createFromExistPanel;
	private JPanel assignPanel;
	private JPanel createFromBlankPanel;
	boolean isCreateFromBlank = true;
	boolean isEditingExistQuiz = false;
	

	int questionNumber = 1;
	JLabel questionLabel = new JLabel("Question " + questionNumber + ": ");
//	JLabel questionLabel = new JLabel();
	JTextField questionText = new JTextField();	
	JTextField answerAText = new JTextField();
	JTextField answerBText = new JTextField();
	JTextField answerCText = new JTextField();
	JTextField answerDText = new JTextField();

	
	final static String CARDPanel = "Default";
    final static String CARDPANEL2 = "Create from Existing";
    final static String CARDPANEL3 = "Assign Quiz";
    final static String CARDPANEL4 = "Create from Blank";
    
	JRadioButton radioButtonA;
	JRadioButton radioButtonB;
	JRadioButton radioButtonC;
	JRadioButton radioButtonD;
	JRadioButton radioButtonE;
	
    JRadioButton quiz1;
	JRadioButton quiz2;
	JRadioButton quiz3;
	JRadioButton quiz4;
	JLabel quiz1CourseLabel;
	JLabel quiz2CourseLabel;
	JLabel quiz3CourseLabel;
	JLabel quiz4CourseLabel;

	//for newly created quiz
	String[] questions;
	String[][] answers;
	String[] correctAnswer;
//	ArrayList<String> createdQuestions;
//	ArrayList<ArrayList<String>> createdAnswers;
//	ArrayList<String> answers;	
//	
//	ArrayList<String> existQuestions;
//	ArrayList<ArrayList<String>> existAnswers;
//	ArrayList<String> eAnswers;
	
	//for exist quiz list
    String[] existQuizList;
    String[] courseOfExistQuiz;
    
    // for exist quiz content    
    String[] existQuestions;
    String[][] existAnswers;
    String[] existCorrectAnswer;

	
	int numberOfQuestions = 0;
	int numberOfCreatedQuestions = 0;
    int numberOfExistQuestions = 0;
    int numberOfExistQuiz = 0;
    int quizNumber = 0;   
    int confirmD;
    String inputD;
    
    private JPanel Othis = this;
    
    
    
    
	public CreateQuizView()
	{
		setLayout (new CardLayout());
		
		createFromBlankButton = new JButton("Create from Blank Quiz");
		createFromExistingButton = new JButton("Create from Existing Quiz");
		
		
		ButtonListener listener = new ButtonListener();	
		createFromBlankButton.addActionListener(listener);
		createFromExistingButton.addActionListener(listener);
		
		createFromBlankPanel = new createFromBlankView();
		createFromExistPanel = new ExistingQuizView();
		assignPanel = new AssignQuizView();	
		createQuizPanel = new JPanel();
		JPanel flow1 = new JPanel();
		JPanel flow2 = new JPanel();
		
		flow1.add(createFromBlankButton);
		flow2.add(createFromExistingButton);
		createQuizPanel.setLayout(new BoxLayout(createQuizPanel,BoxLayout.Y_AXIS));
		createQuizPanel.add(Box.createVerticalGlue());
		createQuizPanel.add(flow1);
    	createQuizPanel.add(Box.createRigidArea(new Dimension(0,10)));
    	createQuizPanel.add(flow2);
		createQuizPanel.add(Box.createVerticalGlue());
		
		add(createQuizPanel,CARDPanel);		
		add(createFromExistPanel,CARDPANEL2);
		add(assignPanel,CARDPANEL3);
		add(createFromBlankPanel,CARDPANEL4);
	}
	
	public void setToNull()
	{
		questionNumber = 1;
		numberOfQuestions = 0;
		numberOfExistQuiz = 0;
		numberOfCreatedQuestions = 0;
		quizNumber = 0; 
		questions = null;
		answers = null;
		correctAnswer = null;
		existQuestions = null;
		existAnswers = null;
		existCorrectAnswer = null;
		existQuizList = null;
		courseOfExistQuiz = null;
		radioButtonA.setSelected(false);
		radioButtonB.setSelected(false);
		radioButtonC.setSelected(false);
		radioButtonD.setSelected(false);
		
		
	}
	
	
	public class ExistingQuizView extends JPanel
	{
		
		JPanel buttonPanel;
		JPanel contentPanel;
		JPanel radioButtonPanel;
		JPanel quizPanel;
		
		ExistingQuizView()
		{
			//read in the number of Existing Quiz
			numberOfExistQuiz = 7;// for testing
			
			existQuizList = new String[numberOfExistQuiz];
			courseOfExistQuiz = new String[numberOfExistQuiz];
			
			//parse in the content of existQuizList and courseOfExistQuiz
			for(int i = 0; i < numberOfExistQuiz; i++)
			{
				existQuizList[i] = "for testing";
				courseOfExistQuiz[i] = " CSE 360";
			}
			
			quiz1 = new JRadioButton(existQuizList[0] + "   " + courseOfExistQuiz[0]);
			quiz1.setSelected(true);
			quiz2 = new JRadioButton(existQuizList[1] + "   " + courseOfExistQuiz[1]);
			quiz2.setSelected(false);
			quiz3 = new JRadioButton(existQuizList[2] + "   " + courseOfExistQuiz[2]);
			quiz3.setSelected(false);
			quiz4 = new JRadioButton(existQuizList[3] + "   " + courseOfExistQuiz[3]);
			quiz4.setSelected(false);
			
			ButtonGroup group = new ButtonGroup();
			group.add(quiz1);
			group.add(quiz2);
			group.add(quiz3);
			group.add(quiz4);
			
			quiz1.addActionListener(new RadioButtonListener());
			quiz2.addActionListener(new RadioButtonListener());
			quiz3.addActionListener(new RadioButtonListener());
			quiz4.addActionListener(new RadioButtonListener());
			
			quizPanel = new JPanel();
			quizPanel.setLayout(new BoxLayout(quizPanel,BoxLayout.Y_AXIS));
			quizPanel.add(quiz1);
			quizPanel.add(quiz2);
			quizPanel.add(quiz3);
			quizPanel.add(quiz4);
			
			contentPanel = new JPanel();
			contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.X_AXIS));
			contentPanel.add(quizPanel);
			
			ButtonListener listener = new ButtonListener();
			cancelButton1.addActionListener(listener);
			okButton.addActionListener(listener);
			nextPageButton.addActionListener(listener);
			previousPageButton.addActionListener(listener);
			
			buttonPanel = new JPanel();
			buttonPanel.add(okButton);
			buttonPanel.add(previousPageButton);
			buttonPanel.add(nextPageButton);
			buttonPanel.add(cancelButton1);
			
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

			add(contentPanel);
			add(buttonPanel);
		}
		
		private class RadioButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {

				Object source = event.getSource();
				//parse the quiz name with its course name of the selected quiz into Model Class
				
			}
			
		}
	}
	
	public class createFromBlankView extends JPanel{
		JLabel hint = new JLabel("Please select one of the four answers as the correct answer.");
		JPanel answer;
		JPanel buttonPanel;

		
		createFromBlankView()
		{			
			completeButton.addActionListener(new ButtonListener());
			backButton.addActionListener(new ButtonListener());
			nextButton.addActionListener(new ButtonListener());
			cancelButton2.addActionListener(new ButtonListener());

			buttonPanel = new JPanel();
			buttonPanel.add(backButton);
			buttonPanel.add(nextButton);
			buttonPanel.add(completeButton);
			buttonPanel.add(cancelButton2);
			
			JPanel questionPanel = new JPanel();
			questionPanel.setLayout(new BorderLayout());
							
			questionPanel.add(questionLabel,BorderLayout.WEST);
			questionPanel.add(questionText,BorderLayout.CENTER);

			radioButtonA = new JRadioButton("A");
		    radioButtonA.setSelected(false);		    
		    radioButtonB = new JRadioButton("B");
		    radioButtonB.setSelected(false);		    
		    radioButtonC = new JRadioButton("C");
		    radioButtonC.setSelected(false);		    
		    radioButtonD = new JRadioButton("D");
		    radioButtonD.setSelected(false);	
		    radioButtonE = new JRadioButton("E");
		    radioButtonE.setSelected(false);
		    ButtonGroup group = new ButtonGroup();
		    group.add(radioButtonA);
		    group.add(radioButtonB);
		    group.add(radioButtonC);
		    group.add(radioButtonD);
		    group.add(radioButtonE);		 	    
		    radioButtonA.addActionListener(new RadioButtonListener());
		    radioButtonB.addActionListener(new RadioButtonListener());
		    radioButtonC.addActionListener(new RadioButtonListener());
		    radioButtonD.addActionListener(new RadioButtonListener());
		    
		    JPanel answerAPanel = new JPanel();
		    JPanel aAPanel = new JPanel();
		    aAPanel.setLayout(new BoxLayout(aAPanel,BoxLayout.X_AXIS));
		    aAPanel.add(Box.createHorizontalStrut(34));
		    aAPanel.add(radioButtonA);
		    answerAPanel.setLayout(new BorderLayout());
		    answerAPanel.add(aAPanel,BorderLayout.WEST);
		    answerAPanel.add(answerAText,BorderLayout.CENTER);
		    
		    JPanel answerBPanel = new JPanel();
		    JPanel aBPanel = new JPanel();
		    aBPanel.setLayout(new BoxLayout(aBPanel,BoxLayout.X_AXIS));
		    aBPanel.add(Box.createHorizontalStrut(34));
		    aBPanel.add(radioButtonB);
		    answerBPanel.setLayout(new BorderLayout());
		    answerBPanel.add(aBPanel,BorderLayout.WEST);
		    answerBPanel.add(answerBText,BorderLayout.CENTER);
		    
		    JPanel answerCPanel = new JPanel();
		    JPanel aCPanel = new JPanel();
		    aCPanel.setLayout(new BoxLayout(aCPanel,BoxLayout.X_AXIS));
		    aCPanel.add(Box.createHorizontalStrut(34));
		    aCPanel.add(radioButtonC);
		    answerCPanel.setLayout(new BorderLayout());
		    answerCPanel.add(aCPanel,BorderLayout.WEST);
		    answerCPanel.add(answerCText,BorderLayout.CENTER);
		    
		    JPanel answerDPanel = new JPanel();
		    JPanel aDPanel = new JPanel();
		    aDPanel.setLayout(new BoxLayout(aDPanel,BoxLayout.X_AXIS));
		    aDPanel.add(Box.createHorizontalStrut(34));
		    aDPanel.add(radioButtonD);
		    answerDPanel.setLayout(new BorderLayout());
		    answerDPanel.add(aDPanel,BorderLayout.WEST);
		    answerDPanel.add(answerDText,BorderLayout.CENTER);
		    
		    answer = new JPanel();
			answer.setLayout(new BoxLayout(answer,BoxLayout.Y_AXIS));
			answer.add(hint);
			answer.add(Box.createVerticalGlue());
			answer.add(questionPanel);
			answer.add(Box.createVerticalStrut(20));
			answer.add(answerAPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerBPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerCPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerDPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(buttonPanel);
			
			add(answer);
					
		}
		private class RadioButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				//parse the correct answer into Model Class
				Object source = event.getSource();
				//correct answer = sourse --->  "A" or "B" or "C" or "D"
				if(source == radioButtonA)
				//	answerBText.setText("for testing!");
					correctAnswer[questionNumber-1] = "A";
				else if(source == radioButtonB)
					correctAnswer[questionNumber-1] = "B";
				else if(source == radioButtonC)
					correctAnswer[questionNumber-1] = "C";
				else if(source == radioButtonD)
					correctAnswer[questionNumber-1] = "D";
				System.out.println("correct answer is :" + correctAnswer[questionNumber-1]);//for testing!!!
				
			}
			
		}

	}
	
//	public class AssignQuizView extends JPanel{
//		JLabel title;
//		AssignQuizView()
//		{
//			title = new JLabel("Assign Quiz");
//			add(title);
//			ButtonListener listener = new ButtonListener();
//			cancelButton3.addActionListener(listener);
//			add(cancelButton3);
//		}
//	}
//	
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			CardLayout c = (CardLayout)Othis.getLayout();
			if(event.getSource() == createFromBlankButton)
			{
				questionNumber = 1;
				numberOfQuestions = 0;
				numberOfExistQuiz = 0;
				numberOfCreatedQuestions = 0;
				quizNumber = 0;
				questionLabel.setText("Question "+ questionNumber + ": ");
				radioButtonE.setSelected(true);
//				createdQuestions = new ArrayList<String>();
//				createdAnswers = new ArrayList<ArrayList<String>>();
//				answers = new ArrayList<String>(4);
//				c.show(Othis,CARDPANEL4);
				inputD = JOptionPane.showInputDialog("How many questions do you need?");		
	//			numberOfQuestions = Integer.parseInt(inputD);
				
				if(inputD == null)
				;
				else
				{
					for(int i = 0; i<inputD.length(); i++)
					{
						if(Character.isDigit(inputD.charAt(i)))
							;
						else
						{
							JOptionPane.showMessageDialog(null,"Invalid input!");
							break;
						}
						numberOfQuestions = Integer.parseInt(inputD);
						if(numberOfQuestions > 0)
						{
							questions = new String[numberOfQuestions];
							answers = new String[numberOfQuestions][4];
							correctAnswer = new String[numberOfQuestions];
							c.show(Othis,CARDPANEL4);
						}
						else
							JOptionPane.showMessageDialog(null,"Invalid input!");
					}
						
				}
							
			}
			
			else if(event.getSource() == createFromExistingButton)
			{
				questionNumber = 1;
				numberOfQuestions = 0;
				numberOfCreatedQuestions = 0;
				quizNumber = 0;
				
				c.show(Othis, CARDPANEL2);
			}
			
			else if(event.getSource() == cancelButton1)
			{	
				setToNull();
	
//				questionNumber = 1;
//				numberOfQuestions = 0;
//				numberOfExistQuiz = 0;
//				numberOfCreatedQuestions = 0;
//				quizNumber = 0;
////				createdQuestions = null;
////				createdAnswers = null;
//				questions = null;
//				answers = null;
//				correctAnswer = null;
//				existQuizList = null;
//				courseOfExistQuiz = null;
				
				c.first(Othis);
			}
			
			else if(event.getSource() == cancelButton2)
			{
				//delete records in database which are already typed in
				setToNull();

//				questionNumber = 1;
//				numberOfQuestions = 0;
//				numberOfExistQuiz = 0;
//				numberOfCreatedQuestions = 0;
//				quizNumber = 0;
//				questions = null;
//				answers = null;
//				correctAnswer = null;
////				createdQuestions = null;
////				createdAnswers = null;
//				existQuizList = null;
//				courseOfExistQuiz = null;
				
				questionText.setText(null);
				answerAText.setText(null);
				answerBText.setText(null);
				answerCText.setText(null);
				answerDText.setText(null);
				
				c.first(Othis);
			}
			
			else if(event.getSource() == cancelButton3)
			{
				//delete records already typed in
				c.first(Othis);
			}
			
			else if(event.getSource() == okButton)
			{
				//for testing!!!!!!!
//				existQuestions = new ArrayList<String>();
//				existAnswers = new ArrayList<ArrayList<String>>();
//				eAnswers = new ArrayList<String>(4);				
//				
//				existQuestions.add("What number comes next? 2, 2, 4, 12, 48, ___");
//				eAnswers.add("64");
//				eAnswers.add("128");
//				eAnswers.add("240");
//				eAnswers.add("72");
//				existAnswers.add(eAnswers);
//				
//				existQuestions.add("A plane crashed on the border or US and Canada. Where do they bury the survivors?");
//				eAnswers.add("US");
//				eAnswers.add("Canada");
//				eAnswers.add("Who cares.");
//				eAnswers.add("You don't bury survivors");
//				existAnswers.add(eAnswers);
//				
//				existQuestions.add("How do you turn 2 into 5?");
//				eAnswers.add("plus 3");
//				eAnswers.add("multiply 2.5");
//				eAnswers.add("Turn it upside down and look at it in a mirror.");
//				eAnswers.add("plus -2");
//				existAnswers.add(eAnswers);
//				
//				existQuestions.add("What is N? 6, 9, 27, 54, N, 2241");
//				eAnswers.add("64");
//				eAnswers.add("128");
//				eAnswers.add("675");
//				eAnswers.add("I do not know..");
//				existAnswers.add(eAnswers);
//
//				createdQuestions = existQuestions;
//				answers = eAnswers;
//				createdAnswers = existAnswers;
	
				//read in the selected existing quiz content
				
				//for testing!!!!!!!
				numberOfExistQuestions = 5;//for testing!!!!!!!!!
				existQuestions = new String[numberOfExistQuestions];
				existAnswers = new String[numberOfExistQuestions][4];
				existCorrectAnswer = new String[numberOfExistQuestions];
				
				for(int i=0 ; i<numberOfExistQuestions; i++)
				{
					existQuestions[i]="How do you turn 2 into 5? "+ (i+1) + " time(s)";
					existAnswers[i][0]="plus 3";
					existAnswers[i][1]="multiply 2.5";
					existAnswers[i][2]="Turn it upside down and look at it in a mirror.";
					existAnswers[i][3]="plus -2";
					existCorrectAnswer[i]="B";
				}
				
				//parse existing quiz into create blank quiz page
				questionText.setText(existQuestions[0]);//for testing
				answerAText.setText(existAnswers[0][0]);//for testing
				answerBText.setText(existAnswers[0][1]);//for testing
				answerCText.setText(existAnswers[0][2]);//for testing
				answerDText.setText(existAnswers[0][3]);//for testing
				if(existCorrectAnswer[0].equals("A"))
					radioButtonA.setSelected(true);
				else if(existCorrectAnswer[0].equals("B"))
					radioButtonB.setSelected(true);
				else if(existCorrectAnswer[0].equals("C"))
					radioButtonC.setSelected(true);
				else if(existCorrectAnswer[0].equals("D"))
					radioButtonD.setSelected(true);
				
				isCreateFromBlank = false;
				questionNumber = 1;
				
				c.show(Othis,CARDPANEL4);
			}
			
			else if(event.getSource() == nextPageButton)
			{	
				System.out.println("number of exist quiz: " + numberOfExistQuiz);
				if(quizNumber+4 >= numberOfExistQuiz)
					JOptionPane.showMessageDialog(null,"It's the last page!");
				else
				{
					//parse in next 4 existing quiz names with their course names
					quizNumber += 4;
					quiz1.setText(existQuizList[quizNumber] + "   " + courseOfExistQuiz[quizNumber]);
					if(quizNumber+1 < numberOfExistQuiz)
					{
						quiz2.setText(existQuizList[quizNumber+1]+ "   " + courseOfExistQuiz[quizNumber+1]);
					}					
					else
					{
						quiz2.setText("No more quizzes.");
					}
						
					if(quizNumber+2 < numberOfExistQuiz)
					{
						quiz3.setText(existQuizList[quizNumber+2]+ "   " + courseOfExistQuiz[quizNumber+2]);
					}						
					else
					{
						quiz3.setText("No more quizzes.");
					}
					
					if(quizNumber+3 < numberOfExistQuiz)
					{
						quiz4.setText(existQuizList[quizNumber+3]+ "   " + courseOfExistQuiz[quizNumber+3]);
					}				
					else
					{
						quiz4.setText("No more quizzes.");
					}
						
					
				}
			
			}
			else if(event.getSource() == previousPageButton)
			{	
				if(quizNumber == 0)
					JOptionPane.showMessageDialog(null,"It's the first page!");
				else
				{
					//parse in next 4 existing quiz names with their course names
					quizNumber -= 4;
					
					quiz1.setText(existQuizList[quizNumber]+ "   " + courseOfExistQuiz[quizNumber]);
					quiz2.setText(existQuizList[quizNumber+1]+ "   " + courseOfExistQuiz[quizNumber+1]);
					quiz3.setText(existQuizList[quizNumber+2]+ "   " + courseOfExistQuiz[quizNumber+2]);
					quiz4.setText(existQuizList[quizNumber+3]+ "   " + courseOfExistQuiz[quizNumber+3]);
					
					
				}
			
			}
			
			else if(event.getSource() == backButton)
			{
				
				if(questionNumber == 1)
					JOptionPane.showMessageDialog(null,"This is the first question!");
				else
				{
					if(isCreateFromBlank||isEditingExistQuiz)
					{
						questionNumber -= 1;
						questionLabel.setText("Question "+ questionNumber + ": ");
						
						questionText.setText(questions[questionNumber-1]);
						answerAText.setText(answers[questionNumber-1][0]);
						answerBText.setText(answers[questionNumber-1][1]);
						answerCText.setText(answers[questionNumber-1][2]);
						answerDText.setText(answers[questionNumber-1][3]);
					}
					else
					{
						questionNumber --;
						questionLabel.setText("Question "+ questionNumber + ": ");
						//parse in next question with its answers
						//for testing!!
						questionText.setText(existQuestions[questionNumber-1]);
						answerAText.setText(existAnswers[questionNumber-1][0]);
						answerBText.setText(existAnswers[questionNumber-1][1]);
						answerCText.setText(existAnswers[questionNumber-1][2]);
						answerDText.setText(existAnswers[questionNumber-1][3]);
						if(existCorrectAnswer[questionNumber-1].equals("A"))
							radioButtonA.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("B"))
							radioButtonB.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("C"))
							radioButtonC.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("D"))
							radioButtonD.setSelected(true);
					}
					
				}
			}
			
			else if(event.getSource() == nextButton)
			{	
				
				if(isCreateFromBlank||isEditingExistQuiz)
				{
					if(questionNumber == numberOfQuestions)
						JOptionPane.showMessageDialog(null,"This is the last question!");
					else
					{

					if(questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals("")))
							JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
					else if((!radioButtonA.isSelected())&&(!radioButtonB.isSelected())&&(!radioButtonC.isSelected())&&(!radioButtonD.isSelected()))
						JOptionPane.showMessageDialog(null,"Please select a correct answer for this question!");
					else
					{
							
						if(questionNumber <= numberOfCreatedQuestions)
						{
								
							
//							
//							questionText.setText(createdQuestions.get(questionNumber-1));
//							answerAText.setText(createdAnswers.get(questionNumber-1).get(0));
//							answerBText.setText(createdAnswers.get(questionNumber-1).get(1));
//							answerCText.setText(createdAnswers.get(questionNumber-1).get(2));
//							answerDText.setText(createdAnswers.get(questionNumber-1).get(3));
							
							questionNumber += 1;
							questionLabel.setText("Question "+ questionNumber + ": ");
							questionText.setText(questions[questionNumber-1]);
							answerAText.setText(answers[questionNumber-1][0]);
							answerBText.setText(answers[questionNumber-1][1]);
							answerCText.setText(answers[questionNumber-1][2]);
							answerDText.setText(answers[questionNumber-1][3]);
						}
						else
						{
							//save the questions and answers into local array list
//							createdQuestions.add(questionNumber-1,questionText.getText());
//							answers.add(0, answerAText.getText());
//							answers.add(1, answerBText.getText());
//							answers.add(2, answerBText.getText());
//							answers.add(3, answerBText.getText());	
//							createdAnswers.add(answers);
								
								
								
							questions[questionNumber-1] = questionText.getText();
							answers[questionNumber-1][0] = answerAText.getText();
							answers[questionNumber-1][1] = answerBText.getText();
							answers[questionNumber-1][2] = answerCText.getText();
							answers[questionNumber-1][3] = answerDText.getText();
							
							questionNumber ++;
							numberOfCreatedQuestions ++;
							questionLabel.setText("Question "+ questionNumber + ": ");
							questionText.setText(null);
							answerAText.setText(null);
							answerBText.setText(null);
							answerCText.setText(null);
							answerDText.setText(null);
							radioButtonE.doClick();
						}
					}
				}
				}
				else
				{
					
					
					if(questionNumber == numberOfExistQuestions)
					{
						confirmD = JOptionPane.showConfirmDialog(null, "There's no more questions.Want to add some?");
						if(confirmD == JOptionPane.YES_OPTION)
						{
							//save the questions and answers into local array list
							inputD = JOptionPane.showInputDialog("How many questions do you want to add?");
							
							
							if(inputD == null)
								;
								else
								{
									for(int i = 0; i<inputD.length(); i++)
									{
										if(Character.isDigit(inputD.charAt(i)))
											;
										else
										{
											JOptionPane.showMessageDialog(null,"Invalid input!");
											break;
										}
										numberOfQuestions = Integer.parseInt(inputD);
										if(numberOfQuestions > 0)
										{
											questions = new String[numberOfQuestions];
											answers = new String[numberOfQuestions][4];
											correctAnswer = new String[numberOfQuestions];
											questionNumber += 1;
											questionLabel.setText("Question "+ questionNumber + ": ");
											questionText.setText(null);
											answerAText.setText(null);
											answerBText.setText(null);
											answerCText.setText(null);
											answerDText.setText(null);
											radioButtonE.doClick();
											isEditingExistQuiz = true;
										}
										else
											JOptionPane.showMessageDialog(null,"Invalid input!");
									}
										
								}						
							
						}
					}
						
					else
					{
						//TODO:save the questions and answers into database
						questionNumber ++;
						questionLabel.setText("Question "+ questionNumber + ": ");
						//parse in next question with its answers
						//for testing!!
						questionText.setText(existQuestions[questionNumber-1]);
						answerAText.setText(existAnswers[questionNumber-1][0]);
						answerBText.setText(existAnswers[questionNumber-1][1]);
						answerCText.setText(existAnswers[questionNumber-1][2]);
						answerDText.setText(existAnswers[questionNumber-1][3]);
						if(existCorrectAnswer[questionNumber-1].equals("A"))
							radioButtonA.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("B"))
							radioButtonB.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("C"))
							radioButtonC.setSelected(true);
						else if(existCorrectAnswer[questionNumber-1].equals("D"))
							radioButtonD.setSelected(true);
//						questionText.setText(????);
//						answerAText.setText(????);
//						answerBText.setText(????);
//						answerCText.setText(????);
//						answerDText.setText(????);
					}
				}
			}
			else if(event.getSource() == completeButton)
				{
				
					//if some answers of a question are empty, then show up an alert message	
					if((questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals("")))&&(questionNumber == 1))
						JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
					else if((!(questionText.getText().equals("")))&&((answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals(""))))
							JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
					else
					{
						if(questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals("")))
							;
						else
						{
							// parse the question and answers into database
//							createdQuestions.add(questionText.getText());
//							answers.add(0, answerAText.getText());
//							answers.add(1, answerBText.getText());
//							answers.add(2, answerBText.getText());
//							answers.add(3, answerBText.getText());	
//							createdAnswers.add(answers);
							
							questions[questionNumber-1] = questionText.getText();
							answers[questionNumber-1][0] = answerAText.getText();
							answers[questionNumber-1][1] = answerBText.getText();
							answers[questionNumber-1][2] = answerCText.getText();
							answers[questionNumber-1][3] = answerDText.getText();
						}
					
						
						
						if(numberOfCreatedQuestions < numberOfQuestions)
						{
							int leftNumber;
							leftNumber = numberOfQuestions - numberOfCreatedQuestions;
							confirmD = JOptionPane.showConfirmDialog(null,"You can add "+ leftNumber +" more questions.Are sure finish editting?");
							if(confirmD == JOptionPane.YES_OPTION)
							{
								
								setToNull();
//								questionNumber = 1;
//								numberOfQuestions = 0;
//								numberOfExistQuiz = 0;
//								numberOfCreatedQuestions = 0;
//								quizNumber = 0; 
//								questions = null;
//								answers = null;
//								correctAnswer = null;
//								existQuestions = null;
//								existAnswers = null;
//								existQuizList = null;
//								courseOfExistQuiz = null;
								
								questionText.setText(null);
								answerAText.setText(null);
								answerBText.setText(null);
								answerCText.setText(null);
								answerDText.setText(null);
								radioButtonE.setSelected(true);
								c.show(Othis, CARDPANEL3);
							}
						
						}
				
					
					}
					
				}
		}
	}
}
=======
package edu.asu.cse360.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import edu.asu.cse360.data.*;
import edu.asu.cse360.model.CreateQuizMod;

public class CreateQuizView extends View{
	JButton createFromBlankButton;
	JButton createFromExistingButton;
	
	JButton cancelButton1 = new JButton("Cancel");
	JButton cancelButton2 = new JButton("Cancel");
	JButton cancelButton3 = new JButton("Cancel");	
	JButton nextButton = new JButton("Next");
	JButton backButton = new JButton("Back");
	JButton completeButton = new JButton("Complete");
	JButton okButton = new JButton("OK");
	JButton nextPageButton = new JButton("Next Page");
	JButton previousPageButton = new JButton("Previous Page");

	private JPanel createQuizPanel;
	private JPanel createFromExistPanel;
	private JPanel assignPanel;
	private JPanel createFromBlankPanel;
	boolean isCreateFromBlank = true;
	boolean isAddingExistQuiz = false;
	
	int questionNumber = 1;
	JLabel questionLabel = new JLabel("Question " + questionNumber + ": ");
//	JLabel questionLabel = new JLabel();
	JTextField questionText = new JTextField();	
	JTextField answerAText = new JTextField();
	JTextField answerBText = new JTextField();
	JTextField answerCText = new JTextField();
	JTextField answerDText = new JTextField();

	
	final static String CARDPANEL1 = "Default";
    final static String CARDPANEL2 = "Create from Existing";
    final static String CARDPANEL3 = "Assign Quiz";
    final static String CARDPANEL4 = "Create from Blank";
    
	JRadioButton radioButtonA;
	JRadioButton radioButtonB;
	JRadioButton radioButtonC;
	JRadioButton radioButtonD;
	JRadioButton radioButtonE;
	
    JRadioButton quiz1;
	JRadioButton quiz2;
	JRadioButton quiz3;
	JRadioButton quiz4;
	JLabel quiz1CourseLabel;
	JLabel quiz2CourseLabel;
	JLabel quiz3CourseLabel;
	JLabel quiz4CourseLabel;

	//for newly created quiz
	String[] questions;
	String[][] answers;
	String[] correctAnswer;
	/*
//	ArrayList<String> createdQuestions;
//	ArrayList<ArrayList<String>> createdAnswers;
//	ArrayList<String> answers;	
//	
//	ArrayList<String> existQuestions;
//	ArrayList<ArrayList<String>> existAnswers;
//	ArrayList<String> eAnswers;*/
	
	//for exist quiz list
	Quiz[] existQuiz;
	Quiz createdQuiz;
	QuizContent[] createdQuizC;
	ArrayList<QuizContent> createdQuizContent;
	
//    String[] existQuizList;
//    String[] courseOfExistQuiz;
    
    // for exist quiz content    
    String[] existQuestions;
    String[][] existAnswers;
    String[] existCorrectAnswer;

	
	int numberOfQuestions = 0;
	int numberOfCreatedQuestions = 0;
    int numberOfExistQuestions = 0;
    int numberOfExistQuiz = 0;
    int quizNumber = 0;   
    int confirmD;
    String inputD;
    
    private JPanel Othis = this;
    

	public CreateQuizView()
	{
		setLayout (new CardLayout());
//		Othis = new JPanel(new CardLayout());
//		Othis = this;
		createFromBlankButton = new JButton("Create from Blank Quiz");
		createFromExistingButton = new JButton("Create from Existing Quiz");
		
		ButtonListener listener = new ButtonListener();	
		createFromBlankButton.addActionListener(listener);
		createFromExistingButton.addActionListener(listener);
		
//		createFromBlankPanel = new CreateFromBlankView();
//		createFromExistPanel = new ExistingQuizView();
		assignPanel = new AssignQuizView();	
		createQuizPanel = new JPanel();
		JPanel flow1 = new JPanel();
		JPanel flow2 = new JPanel();
		
		flow1.add(createFromBlankButton);
		flow2.add(createFromExistingButton);
		createQuizPanel.setLayout(new BoxLayout(createQuizPanel,BoxLayout.Y_AXIS));
		createQuizPanel.add(Box.createVerticalGlue());
		createQuizPanel.add(flow1);
    	createQuizPanel.add(Box.createRigidArea(new Dimension(0,10)));
    	createQuizPanel.add(flow2);
		createQuizPanel.add(Box.createVerticalGlue());
		
		add(createQuizPanel,CARDPANEL1);		
	//	add(createFromExistPanel,CARDPANEL2);
		add(assignPanel,CARDPANEL3);
	//	add(createFromBlankPanel,CARDPANEL4);
	}
	

    public JPanel getOthis(){
    	return Othis;
    }
    public String getCARDPANEL2(){
    	return CARDPANEL2;
    }
    public String getCARDPANEL3(){
    	return CARDPANEL3;
    }
	public JPanel getCreateFromBlankPanel(){
		return createFromBlankPanel;
	}
	
	public JPanel getCreateFromExistPanel(){
		return createFromExistPanel;
	}
	public JPanel getAssignPanel(){
		return assignPanel;
	}
    
	public void initializeB(){
		questionNumber = 1;
		numberOfQuestions = 0;
		numberOfExistQuiz = 0;
		numberOfCreatedQuestions = 0;
		quizNumber = 0;
		questionLabel.setText("Question "+ questionNumber + ": ");
		inputD = JOptionPane.showInputDialog("How many questions do you need?");			
	}
	
	public void initializeE(){
		questionNumber = 1;
		numberOfQuestions = 0;
		numberOfCreatedQuestions = 0;
		quizNumber = 0;
		createFromExistPanel = new ExistingQuizView();
		createFromBlankPanel = new CreateFromBlankView();
		Othis.add(createFromBlankPanel,CARDPANEL4);
	}
	
	
	public void setNumberOfExistQuiz(int a){
		numberOfExistQuiz = a;
	}
	public void setNumberOfExistQuestions(int a){
		numberOfExistQuestions = a;
	}
	
	public int getNumberOfExistQuiz(){
		return numberOfExistQuiz ;
	}
	public int getNumberOfQuestions(){
		return numberOfQuestions;
	}
	public String getInputD(){
		return inputD;
	}
	

	public int getQuestionNumber(){
		return questionNumber;
	}
	
	public JLabel getQuestionLable(){
		return questionLabel;
	}
	public int getQuizNumber(){
		return quizNumber;
	}
		
	public JButton getCompleteButton(){
		return completeButton;
	}
	public void addCompleteButtonListener(ActionListener a){
		completeButton.addActionListener(a);
	}
	
	
	public JButton getCreateFromExistingButton(){
		return createFromExistingButton;
	}
	public void addCreateFromExistingButtonListener (ActionListener a){
		createFromExistingButton.addActionListener(a);
	}
	
	public Quiz getCreatedQuiz(){
		return createdQuiz;
	}
		
	public Quiz getExistQuiz(int i){
		return existQuiz[i];
	}
	public void setExistQuiz(Quiz[] existQuizList){
		existQuiz = new Quiz[numberOfExistQuiz];
		existQuiz = existQuizList;
	}
		
	public boolean getCreateMethod(){
		return isCreateFromBlank;
	}

	public void setToNull()
	{
		questionNumber = 1;
		numberOfQuestions = 0;
		numberOfExistQuiz = 0;
		numberOfCreatedQuestions = 0;
		quizNumber = 0; 
		questions = null;
		answers = null;
		correctAnswer = null;
		existQuestions = null;
		existAnswers = null;
		existCorrectAnswer = null;
//		existQuizList = null;
//		courseOfExistQuiz = null;
		existQuiz = null;
		//radioButtonE.setSelected(true);
		radioButtonE.doClick();
		
		
	}
	
	public boolean questionHasBlank(){
		if(questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals("")))
			return true;
		
		else return false;
	}
	
	public void saveQuizFromBlank()
	{
		
		CardLayout c = (CardLayout)Othis.getLayout();
		Othis.add(assignPanel,CARDPANEL3);
		
		if(isCreateFromBlank)
		{
			//if some answers of a question are empty, then show up an alert message	
			if((questionHasBlank())&&(questionNumber == 1))
				JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
			else if((!(questionText.getText().equals("")))&&((answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals(""))))
					JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
			else
			{
				if(questionHasBlank())
					;
				else
				{
					//save the last question
					if(questionNumber == numberOfCreatedQuestions)
					{
						createdQuizC[questionNumber-1].setQuestion(questionText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerA(answerAText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerB(answerBText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerC(answerCText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerD(answerDText.getText());
					}
					

				}		
				if(numberOfCreatedQuestions < numberOfQuestions)
				{
					int leftNumber;
					leftNumber = numberOfQuestions - numberOfCreatedQuestions;
					
					confirmD = JOptionPane.showConfirmDialog(null,"You can add "+ leftNumber +" more questions.Are sure finish editting?");
					if(confirmD == JOptionPane.YES_OPTION)
					{				
						
						setToNull();
					
						questionText.setText(null);
						answerAText.setText(null);
						answerBText.setText(null);
						answerCText.setText(null);
						answerDText.setText(null);
						c.show(Othis, CARDPANEL3);
					}
				
				}		
				
			}
			
		}	
		
	}
	
	public void saveQuizFromExist(){
		Quiz updateExistQuiz;
		QuizContent[] updateC;
		ArrayList<QuizContent> updateContent;
		int updateNumberOfQuestions;
		int count;
		updateNumberOfQuestions = numberOfExistQuestions+numberOfCreatedQuestions;
		updateExistQuiz = new Quiz();
		updateC = new QuizContent[updateNumberOfQuestions];
		updateContent = new ArrayList<QuizContent>();
		
		CardLayout c = (CardLayout)Othis.getLayout();
		Othis.add(assignPanel,CARDPANEL3);
		
		for(int i=0; i<updateNumberOfQuestions; i++)
		{
			updateC[i] = new QuizContent();
			//change --->  change 0 to real selected quiz 
		}
		
		for(int i=0; i<updateNumberOfQuestions; i++)
		{
			updateContent.add(i, updateC[i]);
			//change --->  change 0 to real selected quiz 
		}
		updateExistQuiz.setContent(updateContent);
		
		for(int i=0; i<numberOfExistQuestions;i++)
		{
			updateC[i].setQuestion(existQuiz[0].getContent().get(i).getQuestion());
			updateC[i].setAnswerA(existQuiz[0].getContent().get(i).getAnswerA());
			updateC[i].setAnswerB(existQuiz[0].getContent().get(i).getAnswerB());
			updateC[i].setAnswerC(existQuiz[0].getContent().get(i).getAnswerC());
			updateC[i].setAnswerD(existQuiz[0].getContent().get(i).getAnswerD());
			System.out.println("updateC "+ i +" is: "+updateC[i].getQuestion());
		}
		for(int i=0; i<numberOfCreatedQuestions;i++)
		{
			count = numberOfExistQuestions+i;
			updateC[count].setQuestion(createdQuiz.getContent().get(i).getQuestion());
			updateC[count].setAnswerA(createdQuiz.getContent().get(i).getAnswerA());
			updateC[count].setAnswerB(createdQuiz.getContent().get(i).getAnswerB());
			updateC[count].setAnswerC(createdQuiz.getContent().get(i).getAnswerC());
			updateC[count].setAnswerD(createdQuiz.getContent().get(i).getAnswerD());
			System.out.println("updateC "+ count +" is: "+updateC[i].getQuestion());
		}
		
		createdQuiz = updateExistQuiz;
		c.show(Othis, CARDPANEL3);
		System.out.println("createdQuiz is: "+createdQuiz.getContent().get(0).getQuestion());
	}
	
	public class ExistingQuizView extends JPanel
	{
		
		JPanel buttonPanel;
		JPanel contentPanel;
		JPanel radioButtonPanel;
		JPanel quizPanel;
		
		public ExistingQuizView()
		{
			//read in the number of Existing Quiz

			quiz1 = new JRadioButton(existQuiz[0].getQuizName() + "   " + existQuiz[0].getCourseName());
			quiz1.setSelected(true);
			quiz2 = new JRadioButton(existQuiz[1].getQuizName() + "   " + existQuiz[1].getCourseName());
			quiz2.setSelected(false);
			quiz3 = new JRadioButton(existQuiz[2].getQuizName() + "   " + existQuiz[2].getCourseName());
			quiz3.setSelected(false);
			quiz4 = new JRadioButton(existQuiz[3].getQuizName() + "   " + existQuiz[3].getCourseName());
			quiz4.setSelected(false);
			
			ButtonGroup group = new ButtonGroup();
			group.add(quiz1);
			group.add(quiz2);
			group.add(quiz3);
			group.add(quiz4);
			
			quiz1.addActionListener(new RadioButtonListener());
			quiz2.addActionListener(new RadioButtonListener());
			quiz3.addActionListener(new RadioButtonListener());
			quiz4.addActionListener(new RadioButtonListener());
			
			quizPanel = new JPanel();
			quizPanel.setLayout(new BoxLayout(quizPanel,BoxLayout.Y_AXIS));
			quizPanel.add(quiz1);
			quizPanel.add(quiz2);
			quizPanel.add(quiz3);
			quizPanel.add(quiz4);
			
			contentPanel = new JPanel();
			contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.X_AXIS));
			contentPanel.add(quizPanel);
			
			ButtonListener listener = new ButtonListener();
			cancelButton1.addActionListener(listener);
			okButton.addActionListener(listener);
			nextPageButton.addActionListener(listener);
			previousPageButton.addActionListener(listener);
			
			buttonPanel = new JPanel();
			buttonPanel.add(okButton);
			buttonPanel.add(previousPageButton);
			buttonPanel.add(nextPageButton);
			buttonPanel.add(cancelButton1);
			
			
			
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

			add(contentPanel);
			add(buttonPanel);
		}
		
		private class RadioButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {

				Object source = event.getSource();
				//parse the quiz name with its course name of the selected quiz into Model Class
				
			}
			
		}
	}
	
	public class CreateFromBlankView extends JPanel{
		JLabel hint = new JLabel("Please select one of the four answers as the correct answer.");
		JPanel answer;
		JPanel buttonPanel;

		
		CreateFromBlankView()
		{			
			//completeButton.addActionListener(new ButtonListener());
			backButton.addActionListener(new ButtonListener());
			nextButton.addActionListener(new ButtonListener());
			cancelButton2.addActionListener(new ButtonListener());

			buttonPanel = new JPanel();
			buttonPanel.add(backButton);
			buttonPanel.add(nextButton);
			buttonPanel.add(completeButton);
			buttonPanel.add(cancelButton2);
			
			JPanel questionPanel = new JPanel();
			questionPanel.setLayout(new BorderLayout());
							
			questionPanel.add(questionLabel,BorderLayout.WEST);
			questionPanel.add(questionText,BorderLayout.CENTER);

			radioButtonA = new JRadioButton("A");
		   	    
		    radioButtonB = new JRadioButton("B");
		    	    
		    radioButtonC = new JRadioButton("C");
		     
		    radioButtonD = new JRadioButton("D");
		    
		    radioButtonE = new JRadioButton("E");
		   		    
		    ButtonGroup group = new ButtonGroup();
		    group.add(radioButtonA);
		    group.add(radioButtonB);
		    group.add(radioButtonC);
		    group.add(radioButtonD);
		    group.add(radioButtonE);
		    
		    radioButtonA.setSelected(false);
		    radioButtonB.setSelected(false);	
		    radioButtonC.setSelected(false);	
		    radioButtonD.setSelected(false);
		    radioButtonE.setSelected(false);
		    		 	    
		    radioButtonA.addActionListener(new RadioButtonListener());
		    radioButtonB.addActionListener(new RadioButtonListener());
		    radioButtonC.addActionListener(new RadioButtonListener());
		    radioButtonD.addActionListener(new RadioButtonListener());
		    
		    JPanel answerAPanel = new JPanel();
		    JPanel aAPanel = new JPanel();
		    aAPanel.setLayout(new BoxLayout(aAPanel,BoxLayout.X_AXIS));
		    aAPanel.add(Box.createHorizontalStrut(34));
		    aAPanel.add(radioButtonA);
		    answerAPanel.setLayout(new BorderLayout());
		    answerAPanel.add(aAPanel,BorderLayout.WEST);
		    answerAPanel.add(answerAText,BorderLayout.CENTER);
		    
		    JPanel answerBPanel = new JPanel();
		    JPanel aBPanel = new JPanel();
		    aBPanel.setLayout(new BoxLayout(aBPanel,BoxLayout.X_AXIS));
		    aBPanel.add(Box.createHorizontalStrut(34));
		    aBPanel.add(radioButtonB);
		    answerBPanel.setLayout(new BorderLayout());
		    answerBPanel.add(aBPanel,BorderLayout.WEST);
		    answerBPanel.add(answerBText,BorderLayout.CENTER);
		    
		    JPanel answerCPanel = new JPanel();
		    JPanel aCPanel = new JPanel();
		    aCPanel.setLayout(new BoxLayout(aCPanel,BoxLayout.X_AXIS));
		    aCPanel.add(Box.createHorizontalStrut(34));
		    aCPanel.add(radioButtonC);
		    answerCPanel.setLayout(new BorderLayout());
		    answerCPanel.add(aCPanel,BorderLayout.WEST);
		    answerCPanel.add(answerCText,BorderLayout.CENTER);
		    
		    JPanel answerDPanel = new JPanel();
		    JPanel aDPanel = new JPanel();
		    aDPanel.setLayout(new BoxLayout(aDPanel,BoxLayout.X_AXIS));
		    aDPanel.add(Box.createHorizontalStrut(34));
		    aDPanel.add(radioButtonD);
		    answerDPanel.setLayout(new BorderLayout());
		    answerDPanel.add(aDPanel,BorderLayout.WEST);
		    answerDPanel.add(answerDText,BorderLayout.CENTER);
		    
		    answer = new JPanel();
			answer.setLayout(new BoxLayout(answer,BoxLayout.Y_AXIS));
			answer.add(hint);
			answer.add(Box.createVerticalGlue());
			answer.add(questionPanel);
			answer.add(Box.createVerticalStrut(20));
			answer.add(answerAPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerBPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerCPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(answerDPanel);
			answer.add(Box.createVerticalStrut(10));
			answer.add(buttonPanel);
			
			add(answer);
					
		}
		private class RadioButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				//parse the correct answer into Model Class
				Object source = event.getSource();
				//correct answer = sourse --->  "A" or "B" or "C" or "D"
				if(source == radioButtonA)
				//	answerBText.setText("for testing!");
					correctAnswer[questionNumber-1] = "A";
				else if(source == radioButtonB)
					correctAnswer[questionNumber-1] = "B";
				else if(source == radioButtonC)
					correctAnswer[questionNumber-1] = "C";
				else if(source == radioButtonD)
					correctAnswer[questionNumber-1] = "D";
				System.out.println("correct answer is :" + correctAnswer[questionNumber-1]);//for testing!!!
				
			}
			
		}

	}
	
	public void setQuizContent(Quiz quiz,int i){
		
		quiz.getContent().get(i).setQuestion(questionText.getText());
		quiz.getContent().get(i).setAnswerA(answerAText.getText());
		quiz.getContent().get(i).setAnswerB(answerBText.getText());
		quiz.getContent().get(i).setAnswerC(answerCText.getText());
		quiz.getContent().get(i).setAnswerD(answerDText.getText());
	}
	
	public void initializeQuestion(){
		questionText.setText(null);
		answerAText.setText(null);
		answerBText.setText(null);
		answerCText.setText(null);
		answerDText.setText(null);	
	}
	
	
	public void setQuestionUI(Quiz quiz,int i){
		questionText.setText(quiz.getContent().get(i).getQuestion());
		answerAText.setText(quiz.getContent().get(i).getAnswerA());
		answerBText.setText(quiz.getContent().get(i).getAnswerB());
		answerCText.setText(quiz.getContent().get(i).getAnswerC());
		answerDText.setText(quiz.getContent().get(i).getAnswerD());
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			CardLayout c = (CardLayout)Othis.getLayout();
			if(event.getSource() == createFromBlankButton)
			{
				createFromBlankPanel = new CreateFromBlankView();		
				Othis.add(createFromBlankPanel,CARDPANEL4);
				
				questionNumber = 1;
				numberOfQuestions = 0;
				numberOfExistQuiz = 0;
				numberOfCreatedQuestions = 0;
				quizNumber = 0;
				questionLabel.setText("Question "+ questionNumber + ": ");				
				
				inputD = JOptionPane.showInputDialog("How many questions do you need?");		
	//			numberOfQuestions = Integer.parseInt(inputD);
				boolean isValid = false;
				
				if(inputD == null)
				;
				else
				{
					for(int i = 0; i<inputD.length(); i++)
					{
						if(Character.isDigit(inputD.charAt(i)))
							;
						else
						{
							JOptionPane.showMessageDialog(null,"Invalid input!");
							break;
						}
						numberOfQuestions = inputD.charAt(i);
						System.out.println("numberOfQuestions is: "+numberOfQuestions);
					//	numberOfQuestions = Integer.parseInt(inputD);
						if(numberOfQuestions <= 0)
						{
							JOptionPane.showMessageDialog(null,"Invalid input!");
							break;
						}
						if(i == inputD.length()-1)
							isValid = true;
					}
						if(isValid)
						{
							numberOfQuestions = Integer.parseInt(inputD);
							createdQuiz = new Quiz();
							createdQuizC = new QuizContent[numberOfQuestions];
							createdQuizContent = new ArrayList<QuizContent>();
								
							for(int i=0; i<numberOfQuestions; i++)
							{
								createdQuizC[i] = new QuizContent();
							}
							
							for(int j=0; j<numberOfQuestions; j++)
							{
								createdQuizContent.add(j, createdQuizC[j]);
							}						
							createdQuiz.setContent(createdQuizContent);
							
							//change --> real correct answer
							correctAnswer = new String[numberOfQuestions];
							c.show(Othis,CARDPANEL4);					
						}
													
				}						
			}
			
			else if(event.getSource() == cancelButton1)
			{	
				setToNull();			
				c.first(Othis);
			}
			
			else if(event.getSource() == cancelButton2)
			{
				//delete records in database which are already typed in
				setToNull();
				initializeQuestion();
		
				c.first(Othis);
			}
			
			else if(event.getSource() == cancelButton3)
			{
				//delete records already typed in
				c.first(Othis);
			}
			
			else if(event.getSource() == okButton)
			{
						
				//parse existing quiz into create blank quiz page
				setQuestionUI(existQuiz[0],0);
				
//				if(existCorrectAnswer[0].equals("A"))
//					radioButtonA.setSelected(true);
//				else if(existCorrectAnswer[0].equals("B"))
//					radioButtonB.setSelected(true);
//				else if(existCorrectAnswer[0].equals("C"))
//					radioButtonC.setSelected(true);
//				else if(existCorrectAnswer[0].equals("D"))
//					radioButtonD.setSelected(true);
//				
				isCreateFromBlank = false;
				questionNumber = 1;
			
				System.out.println("numberOfCreatedQuestions in okButton: "+numberOfCreatedQuestions);
				c.show(Othis,CARDPANEL4);
				
			}
			
			else if(event.getSource() == nextPageButton)
			{	

				if(quizNumber+4 >= numberOfExistQuiz)
					JOptionPane.showMessageDialog(null,"It's the last page!");
				else
				{
					//parse in next 4 existing quiz names with their course names
					quizNumber += 4;
					quiz1.setText(existQuiz[quizNumber].getQuizName() + "   " + existQuiz[quizNumber].getCourseName());
					if(quizNumber+1 < numberOfExistQuiz)
					{
						quiz2.setText(existQuiz[quizNumber+1].getQuizName()+ "   " + existQuiz[quizNumber+1].getCourseName());
					}					
					else
					{
						quiz2.setText("No more quizzes.");
					}
						
					if(quizNumber+2 < numberOfExistQuiz)
					{
						quiz3.setText(existQuiz[quizNumber+2].getQuizName()+ "   " + existQuiz[quizNumber+2].getCourseName());
					}						
					else
					{
						quiz3.setText("No more quizzes.");
					}
					
					if(quizNumber+3 < numberOfExistQuiz)
					{
						quiz4.setText(existQuiz[quizNumber+3].getQuizName()+ "   " + existQuiz[quizNumber+3].getCourseName());
					}				
					else
					{
						quiz4.setText("No more quizzes.");
					}
						
					
				}
			
			}
			else if(event.getSource() == previousPageButton)
			{	
				if(quizNumber == 0)
					JOptionPane.showMessageDialog(null,"It's the first page!");
				else
				{
					//parse in next 4 existing quiz names with their course names
					quizNumber -= 4;
					
					quiz1.setText(existQuiz[quizNumber].getQuizName()+ "   " + existQuiz[quizNumber].getCourseName());
					quiz2.setText(existQuiz[quizNumber+1].getQuizName()+ "   " + existQuiz[quizNumber+1].getCourseName());
					quiz3.setText(existQuiz[quizNumber+2].getQuizName()+ "   " + existQuiz[quizNumber+2].getCourseName());
					quiz4.setText(existQuiz[quizNumber+3].getQuizName()+ "   " + existQuiz[quizNumber+3].getCourseName());			
					
				}
			
			}
			
			else if(event.getSource() == backButton)
			{
				
				System.out.println("isCreateFromBlank: "+ isCreateFromBlank);
				System.out.println("isAddingExistQuiz: "+ isAddingExistQuiz);
				
				
				if(questionNumber == 1)
					JOptionPane.showMessageDialog(null,"This is the first question!");
				else
				{
					if(isCreateFromBlank||isAddingExistQuiz)
					{
					questionNumber -= 1;
					questionLabel.setText("Question "+ questionNumber + ": ");

					setQuestionUI(createdQuiz,questionNumber-1);
								
					//change --> parse in the correct answer
//					if(existCorrectAnswer[questionNumber-1].equals("A"))
//						radioButtonA.setSelected(true);
//					else if(existCorrectAnswer[questionNumber-1].equals("B"))
//						radioButtonB.setSelected(true);
//					else if(existCorrectAnswer[questionNumber-1].equals("C"))
//						radioButtonC.setSelected(true);
//					else if(existCorrectAnswer[questionNumber-1].equals("D"))
//						radioButtonD.setSelected(true);
					
					}
					else
					{
						questionNumber -= 1;
						questionLabel.setText("Question "+ questionNumber + ": ");
						
						//change 0 to selected quiz ---> quiz name
						setQuestionUI(existQuiz[0],questionNumber-1);
					
						//change --> parse in the correct answer
//						if(existCorrectAnswer[questionNumber-1].equals("A"))
//							radioButtonA.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("B"))
//							radioButtonB.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("C"))
//							radioButtonC.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("D"))
//							radioButtonD.setSelected(true);
					}
				}
			}
			
			else if(event.getSource() == nextButton)
			{	
				if(isCreateFromBlank||isAddingExistQuiz)
				{
					if(questionNumber == numberOfQuestions)
						JOptionPane.showMessageDialog(null,"This is the last question!");
					else
					{
						if(questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerBText.getText().equals(""))||(answerDText.getText().equals("")))
							JOptionPane.showMessageDialog(null,"Question or some answers are blank, please fill them in.");
						else
						{
							
							if(questionNumber <= numberOfCreatedQuestions)
							{								
								questionNumber += 1;
								questionLabel.setText("Question "+ questionNumber + ": ");
							
								setQuestionUI(createdQuiz,questionNumber-1);

							}
							else
							{	
								System.out.println("questionNumber = "+questionNumber);
								System.out.println("numberOfQuestions = "+numberOfQuestions);

								setQuizContent(createdQuiz,questionNumber-1);
								
								System.out.println("new question is: "+ createdQuiz.getContent().get(questionNumber-1).getQuestion());
								questionNumber ++;								
								
								questionLabel.setText("Question "+ questionNumber + ": ");
								if(isCreateFromBlank)
								{
									numberOfCreatedQuestions ++;
									initializeQuestion();
								}
								else
									setQuestionUI(createdQuiz,questionNumber-1);
								
								
							}
													
							
						}
					}	
					
				}
				else
				{
					
					
					if(questionNumber == numberOfExistQuestions)
					{
						confirmD = JOptionPane.showConfirmDialog(null, "There's no more questions.Want to add some?");
						if(confirmD == JOptionPane.YES_OPTION)
						{
							//save the questions and answers into local array list
							inputD = JOptionPane.showInputDialog("How many questions do you want to add?");
							boolean isValid = false;
							
							if(inputD == null)
								;
								else
								{
			
									for(int i = 0; i<inputD.length(); i++)
									{
										if(Character.isDigit(inputD.charAt(i)))
											;
										else
										{
											JOptionPane.showMessageDialog(null,"Invalid input!");
											break;
										}
										numberOfQuestions = inputD.charAt(i);

											if(numberOfQuestions <= 0)
											{
												JOptionPane.showMessageDialog(null,"Invalid input!");
												break;
											}
											if(i == inputD.length()-1)
												isValid = true;
											
									}		
									
									//change--> real correct answer of the question
									if(isValid)
									{
										numberOfQuestions = Integer.parseInt(inputD);
										numberOfQuestions += numberOfExistQuestions;
										
										correctAnswer = new String[numberOfQuestions];								
										createdQuiz = new Quiz();
										createdQuizC = new QuizContent[numberOfQuestions];
										createdQuizContent = new ArrayList<QuizContent>();
										
										for(int i=0; i<numberOfQuestions; i++)
										{
											createdQuizC[i] = new QuizContent();
										}
										for(int j=0; j<numberOfQuestions; j++)
										{
											createdQuizContent.add(j, createdQuizC[j]);
										}						
										createdQuiz.setContent(createdQuizContent);
										
										for(int k=0; k<numberOfExistQuestions; k++)
										{
											//change ---> change 0 to real exist quiz
											createdQuiz.getContent().get(k).setQuestion(existQuiz[0].getContent().get(k).getQuestion());
											createdQuiz.getContent().get(k).setAnswerA(existQuiz[0].getContent().get(k).getAnswerA());
											createdQuiz.getContent().get(k).setAnswerB(existQuiz[0].getContent().get(k).getAnswerB());
											createdQuiz.getContent().get(k).setAnswerC(existQuiz[0].getContent().get(k).getAnswerC());
											createdQuiz.getContent().get(k).setAnswerD(existQuiz[0].getContent().get(k).getAnswerD());
										}
										
										questionNumber += 1;
										questionLabel.setText("Question "+ questionNumber + ": ");
										initializeQuestion();

										isAddingExistQuiz = true;
									}
											
										
									}															
						}
					}
						
					else
					{
						//save the questions and answers into database
						questionNumber += 1;
						questionLabel.setText("Question "+ questionNumber + ": ");
						//parse in next question with its answers				
						//change 0 to what user selected---> quiz name
						setQuestionUI(existQuiz[0],questionNumber-1);

						
//						if(existCorrectAnswer[questionNumber-1].equals("A"))
//							radioButtonA.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("B"))
//							radioButtonB.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("C"))
//							radioButtonC.setSelected(true);
//						else if(existCorrectAnswer[questionNumber-1].equals("D"))
//							radioButtonD.setSelected(true);

					}
				}
				
			}

		}
	}
	

}

>>>>>>> 5cb929cc8ac103fb609cb934aa437661f1535d49
