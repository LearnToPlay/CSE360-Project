
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
import edu.asu.cse360.model.CreateQuizModel;

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
	private AssignQuizView assignPanel;
	private JPanel createFromBlankPanel;
	boolean isCreateFromBlank = true;
	boolean isAddingExistQuiz = false;
	
	int questionNumber = 1;
	JLabel questionLabel = new JLabel("Question " + questionNumber + ": ");
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
//	String[] questions;
//	String[][] answers;
//	String[] correctAnswer;

	//for exist quiz list
	int page = 1;
	int selectedQuizNumber;
	Quiz[] existQuiz;
	Quiz createdQuiz;
	QuizContent[] createdQuizC;
	ArrayList<QuizContent> createdQuizContent;
	Answers correctAnswer;
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
    public String getCARDPANEL1(){
    	return CARDPANEL1;
    }
    public String getCARDPANEL2(){
    	return CARDPANEL2;
    }
    public String getCARDPANEL3(){
    	return CARDPANEL3;
    }
    public JPanel getCreateQuizPanel(){
    	return createQuizPanel;
    }
	public JPanel getCreateFromBlankPanel(){
		return createFromBlankPanel;
	}
	
	public JPanel getCreateFromExistPanel(){
		return createFromExistPanel;
	}
	
	
	public AssignQuizView getAssignView(){
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
//		questions = null;
//		answers = null;
//		correctAnswer = null;
		existQuestions = null;
		existAnswers = null;
		existCorrectAnswer = null;
//		existQuizList = null;
//		courseOfExistQuiz = null;
		existQuiz = null;
		initializeQuestion();
		
		
	}
	
	public boolean questionHasBlank(){
		if(questionText.getText().equals("")||(answerAText.getText().equals(""))||(answerBText.getText().equals(""))||(answerCText.getText().equals(""))||(answerDText.getText().equals("")))
			return true;
		
		else return false;
	}
	
	public void saveQuiz()
	{
		
		CardLayout c = (CardLayout)Othis.getLayout();
		Othis.add(assignPanel,CARDPANEL3);
//		if(isCreateFromBlank)
//		{
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
					
					if(!(isCreateFromBlank))
					{
						System.out.println("through 33333333");
						numberOfCreatedQuestions++;
						numberOfCreatedQuestions += numberOfExistQuestions;
						System.out.println("numberOfCreatedQuestions in Save: "+ numberOfCreatedQuestions);
					}
					//save the last question
					if(questionNumber == numberOfCreatedQuestions)
					{
						System.out.println("through 4444444");
						createdQuizC[questionNumber-1].setQuestion(questionText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerA(answerAText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerB(answerBText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerC(answerCText.getText());
						createdQuiz.getContent().get(questionNumber-1).setAnswerD(answerDText.getText());
						
						c.show(Othis, CARDPANEL3);
					}					
				}		
				
				if(numberOfCreatedQuestions < numberOfQuestions)
				{
					int leftNumber;
					leftNumber = numberOfQuestions - numberOfCreatedQuestions;
					
					System.out.println("number of question in save after if button: "+ numberOfQuestions);
					System.out.println("number of created question in save after if button: "+ numberOfCreatedQuestions);
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
			
	//	}	
		
	}
	
//	public void saveQuizFromExist(){
//		Quiz updateExistQuiz;
//		QuizContent[] updateC;
//		ArrayList<QuizContent> updateContent;
//		int updateNumberOfQuestions;
//		int count;
//		updateNumberOfQuestions = numberOfExistQuestions+numberOfCreatedQuestions;
//		updateExistQuiz = new Quiz();
//		updateC = new QuizContent[updateNumberOfQuestions];
//		updateContent = new ArrayList<QuizContent>();
//		
//		CardLayout c = (CardLayout)Othis.getLayout();
//		Othis.add(assignPanel,CARDPANEL3);
//		
//		for(int i=0; i<updateNumberOfQuestions; i++)
//		{
//			updateC[i] = new QuizContent();
//		}
//		
//		for(int i=0; i<updateNumberOfQuestions; i++)
//		{
//			updateContent.add(i, updateC[i]);
//		}
//		updateExistQuiz.setContent(updateContent);
//		
//		for(int i=0; i<numberOfExistQuestions;i++)
//		{
//			updateC[i].setQuestion(existQuiz[selectedQuizNumber].getContent().get(i).getQuestion());
//			updateC[i].setAnswerA(existQuiz[selectedQuizNumber].getContent().get(i).getAnswerA());
//			updateC[i].setAnswerB(existQuiz[selectedQuizNumber].getContent().get(i).getAnswerB());
//			updateC[i].setAnswerC(existQuiz[selectedQuizNumber].getContent().get(i).getAnswerC());
//			updateC[i].setAnswerD(existQuiz[selectedQuizNumber].getContent().get(i).getAnswerD());
//			System.out.println("updateC "+ i +" is: "+updateC[i].getQuestion());
//		}
//		for(int i=0; i<numberOfCreatedQuestions;i++)
//		{
//			count = numberOfExistQuestions+i;
//			updateC[count].setQuestion(createdQuiz.getContent().get(i).getQuestion());
//			updateC[count].setAnswerA(createdQuiz.getContent().get(i).getAnswerA());
//			updateC[count].setAnswerB(createdQuiz.getContent().get(i).getAnswerB());
//			updateC[count].setAnswerC(createdQuiz.getContent().get(i).getAnswerC());
//			updateC[count].setAnswerD(createdQuiz.getContent().get(i).getAnswerD());
//			System.out.println("updateC "+ count +" is: "+updateC[i].getQuestion());
//		}
//		
//		createdQuiz = updateExistQuiz;
//		c.show(Othis, CARDPANEL3);
//		System.out.println("createdQuiz is: "+createdQuiz.getContent().get(0).getQuestion());
//	}
	
	public class ExistingQuizView extends JPanel
	{
		
		JPanel buttonPanel;
		JPanel contentPanel;
		JPanel radioButtonPanel;
		JPanel quizPanel;
		
		public ExistingQuizView()
		{
			//read in the number of Existing Quiz

			quiz1 = new JRadioButton(existQuiz[0].getCourseName() + "   " + existQuiz[0].getQuizName());
			quiz1.setSelected(true);
			quiz2 = new JRadioButton(existQuiz[1].getCourseName() + "   " + existQuiz[1].getQuizName());
			quiz2.setSelected(false);
			quiz3 = new JRadioButton(existQuiz[2].getCourseName() + "   " + existQuiz[2].getQuizName());
			quiz3.setSelected(false);
			quiz4 = new JRadioButton(existQuiz[3].getCourseName() + "   " + existQuiz[3].getQuizName());
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
				//parse the correct answer into Model Class
				Object source = event.getSource();
				//correct answer = sourse --->  "A" or "B" or "C" or "D"
				if(source == quiz1)
				//	answerBText.setText("for testing!");
					selectedQuizNumber = 0;
				else if(source == quiz2)
					selectedQuizNumber = 1;
				else if(source == quiz3)
					selectedQuizNumber = 2;
				else if(source == quiz4)
					selectedQuizNumber = 3;
				System.out.println("selected quiz in radio button listener :" + selectedQuizNumber);//for testing!!!
				
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
					correctAnswer = Answers.A;
				else if(source == radioButtonB)
					correctAnswer = Answers.B;
				else if(source == radioButtonC)
					correctAnswer = Answers.C;
				else if(source == radioButtonD)
					correctAnswer = Answers.D;
				System.out.println("correct answer is :" + correctAnswer.toString());//for testing!!!
				
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
		radioButtonE.doClick();
	}
	
	
	public void setQuestionUI(Quiz quiz,int i){
		questionText.setText(quiz.getContent().get(i).getQuestion());
		answerAText.setText(quiz.getContent().get(i).getAnswerA());
		answerBText.setText(quiz.getContent().get(i).getAnswerB());
		answerCText.setText(quiz.getContent().get(i).getAnswerC());
		answerDText.setText(quiz.getContent().get(i).getAnswerD());
	}
	
	public void setAnswerUI(Answers a){
		if(a == Answers.A)
			radioButtonA.doClick();
		else if(a == Answers.B)
			radioButtonB.doClick();
		else if(a == Answers.C)
			radioButtonC.doClick();
		else if(a == Answers.D)
			radioButtonD.doClick();
		else
			radioButtonE.doClick();
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
				selectedQuizNumber += 4*(page-1);
				setQuestionUI(existQuiz[selectedQuizNumber],0);
				setAnswerUI(existQuiz[selectedQuizNumber].getContent().get(0).getCorrectAnswer());
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
					page++;
					quizNumber += 4;
					quiz1.setText(existQuiz[quizNumber].getCourseName() + "   " + existQuiz[quizNumber].getQuizName());
					if(quizNumber+1 < numberOfExistQuiz)
					{
						quiz2.setText(existQuiz[quizNumber+1].getCourseName()+ "   " + existQuiz[quizNumber+1].getQuizName());
					}					
					else
					{
						quiz2.setText("No more quizzes.");
					}
						
					if(quizNumber+2 < numberOfExistQuiz)
					{
						quiz3.setText(existQuiz[quizNumber+2].getCourseName()+ "   " + existQuiz[quizNumber+2].getQuizName());
					}						
					else
					{
						quiz3.setText("No more quizzes.");
					}
					
					if(quizNumber+3 < numberOfExistQuiz)
					{
						quiz4.setText(existQuiz[quizNumber+3].getCourseName()+ "   " + existQuiz[quizNumber+3].getQuizName());
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
					page--;
					
					quiz1.setText(existQuiz[quizNumber].getCourseName()+ "   " + existQuiz[quizNumber].getQuizName());
					quiz2.setText(existQuiz[quizNumber+1].getCourseName()+ "   " + existQuiz[quizNumber+1].getQuizName());
					quiz3.setText(existQuiz[quizNumber+2].getCourseName()+ "   " + existQuiz[quizNumber+2].getQuizName());
					quiz4.setText(existQuiz[quizNumber+3].getCourseName()+ "   " + existQuiz[quizNumber+3].getQuizName());			
					
				}
			
			}
			
			else if(event.getSource() == backButton)
			{
								
				if(questionNumber == 1)
					JOptionPane.showMessageDialog(null,"This is the first question!");
				else
				{
					System.out.println("number of question in back button: "+ numberOfQuestions);
					System.out.println("number of created question in back button: "+ numberOfCreatedQuestions);
					if(isCreateFromBlank||isAddingExistQuiz)
					{
					questionNumber -= 1;
					questionLabel.setText("Question "+ questionNumber + ": ");

					setQuestionUI(createdQuiz,questionNumber-1);
					setAnswerUI(createdQuiz.getContent().get(questionNumber-1).getCorrectAnswer());					
					}
					else
					{
						questionNumber -= 1;
						questionLabel.setText("Question "+ questionNumber + ": ");
						
						
						setQuestionUI(existQuiz[selectedQuizNumber],questionNumber-1);
						setAnswerUI(existQuiz[selectedQuizNumber].getContent().get(questionNumber-1).getCorrectAnswer());

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
								setAnswerUI(createdQuiz.getContent().get(questionNumber-1).getCorrectAnswer());
								
								System.out.println("numberOfCreatedQuestions in next 1111:"+ numberOfCreatedQuestions);
							}
							else
							{	
								System.out.println("questionNumber = "+questionNumber);
								System.out.println("numberOfQuestions = "+numberOfQuestions);

								setQuizContent(createdQuiz,questionNumber-1);
								createdQuiz.getContent().get(questionNumber-1).setCorrectAnswer(correctAnswer);
								System.out.println("correct answer of createdQuiz: "+ createdQuiz.getContent().get(questionNumber-1).getCorrectAnswer());
								System.out.println("new question is: "+ createdQuiz.getContent().get(questionNumber-1).getQuestion());
								questionNumber ++;								
								
								questionLabel.setText("Question "+ questionNumber + ": ");
								if(isCreateFromBlank)
								{
									numberOfCreatedQuestions ++;
									initializeQuestion();
								}
								else
								{
									numberOfCreatedQuestions ++;
									setQuestionUI(createdQuiz,questionNumber-1);
									setAnswerUI(createdQuiz.getContent().get(questionNumber-1).getCorrectAnswer());	
								}
																
								System.out.println("numberOfCreatedQuestions in next 2222:"+ numberOfCreatedQuestions);
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
									
									if(isValid)
									{
										numberOfQuestions = Integer.parseInt(inputD);
										numberOfQuestions += numberOfExistQuestions;
							
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
											//parse the exist quiz content into the created quiz
											createdQuiz.getContent().get(k).setQuestion(existQuiz[selectedQuizNumber].getContent().get(k).getQuestion());
											createdQuiz.getContent().get(k).setAnswerA(existQuiz[selectedQuizNumber].getContent().get(k).getAnswerA());
											createdQuiz.getContent().get(k).setAnswerB(existQuiz[selectedQuizNumber].getContent().get(k).getAnswerB());
											createdQuiz.getContent().get(k).setAnswerC(existQuiz[selectedQuizNumber].getContent().get(k).getAnswerC());
											createdQuiz.getContent().get(k).setAnswerD(existQuiz[selectedQuizNumber].getContent().get(k).getAnswerD());
											createdQuiz.getContent().get(k).setCorrectAnswer(existQuiz[selectedQuizNumber].getContent().get(k).getCorrectAnswer());
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
						questionNumber += 1;
						questionLabel.setText("Question "+ questionNumber + ": ");
						//parse in next question with its answers				
						setQuestionUI(existQuiz[selectedQuizNumber],questionNumber-1);
						setAnswerUI(existQuiz[selectedQuizNumber].getContent().get(questionNumber-1).getCorrectAnswer());


					}
				}
				
			}

		}
	}
}
