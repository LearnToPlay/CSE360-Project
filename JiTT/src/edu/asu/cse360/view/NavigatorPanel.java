<<<<<<< HEAD
package edu.asu.cse360.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.asu.cse360.model.*;
import edu.asu.cse360.control.*;

public class NavigatorPanel extends JFrame
{
	static final long serialVersionUID = 1l;
	
	final static String CARDPANEL1 = "Create Course";
    final static String CARDPANEL2 = "Create Quiz";
    final static String CARDPANEL3 = "View Quiz Report";
    final static String CARDPANEL4 = "Take Quiz";
    final static String CARDPANEL5 = "View Quiz Scores";
    JPanel cards; //a panel that uses CardLayout
    JComboBox CreateCourseButton, ViewReportButton,
    		TakeQuizButton, ViewScoresButton;
    JButton CreateQuizButton, LogoutButton;

    public void addComponentToPane(Container pane)
    {
    	// these will be contained in the OuterPane
        JLabel hello = new JLabel("<html><font size = \"10\">Welcome to the JiTT Program</font><html>");
        hello.setHorizontalAlignment(JLabel.CENTER);
        LogoutButton = new JButton("Logout");
        LogoutButton.addActionListener(new ButtonListener());
        JPanel upperPane = new JPanel(new BorderLayout());
        JPanel temp = new JPanel();
        temp.add(LogoutButton);
        upperPane.add(hello, BorderLayout.CENTER);
        upperPane.add(temp, BorderLayout.EAST);
        
        // Navigation Buttons. JMenu, JTree or whatever works best...
        JPanel navigationPane = new JPanel();
        
        boolean redo = true;
        while(redo)
        {
	        String s = (String)JOptionPane.showInputDialog(
	        		new JFrame(),
	        		"Please type either instructor or student\nEnter Log In Number:",
	        		"Log In",
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,
	        		null,
	        		"Enter ID Number here"
	        		);
	        if(s.compareToIgnoreCase("instructor") == 0)
	        {
	        	navigationPane = makeInstructor();
	        	redo = false;
	        }
	        else if(s.compareToIgnoreCase("student") == 0)
	        {
	            navigationPane = makeStudent();
	            redo = false;
	        }
	        else
	        	redo = true;
        }
        
        // CardLayout's not really good idea...
        cards = new JPanel(new CardLayout());
        
        // put everything together
        pane.add(upperPane, BorderLayout.NORTH);
        pane.add(navigationPane, BorderLayout.WEST);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    private JPanel makeInstructor()
    {
        // Instructor uses:
        CreateCourseButton = new JComboBox();
        CreateCourseButton.addItem(CARDPANEL1);
        // add created courses (in the case of edits)
        CreateCourseButton.addItem("Create New Course");
        CreateCourseButton.addItem("Edit Temp Course 1");
        CreateCourseButton.addItem("Edit Temp Course 2");
        
        CreateQuizButton = new JButton(CARDPANEL2);
        
        ViewReportButton = new JComboBox();
        ViewReportButton.addItem(CARDPANEL3);
        // add available quiz reports
        ViewReportButton.addItem("View Temp Quiz Report 1");
        ViewReportButton.addItem("View Temp Quiz Report 2");
        ViewReportButton.addItem("View Temp Quiz Report 3");
        
        CreateCourseButton.addActionListener(new ButtonListener());
        CreateQuizButton.addActionListener(new ButtonListener());
        ViewReportButton.addActionListener(new ButtonListener());
        JPanel flow1 = new JPanel();
        JPanel flow2 = new JPanel();
        JPanel flow3 = new JPanel();
        flow1.add(CreateCourseButton);
        flow2.add(CreateQuizButton);
        flow3.add(ViewReportButton);
        
        JPanel toReturn = new JPanel();
        toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
        toReturn.add(flow1);
        toReturn.add(flow2);
        toReturn.add(flow3);
        return toReturn;
    }
    
    public JPanel makeStudent()
    {
        // Student uses:
        TakeQuizButton = new JComboBox();
        TakeQuizButton.addItem(CARDPANEL4);
        // add available quizzes to take
        TakeQuizButton.addItem("Take Temp Quiz 1");
        TakeQuizButton.addItem("Take Temp Quiz 2");
        TakeQuizButton.addItem("Take Temp Quiz 3");

        ViewScoresButton = new JComboBox();
        ViewScoresButton.addItem(CARDPANEL5);
        // add available quizzes to view
        ViewScoresButton.addItem("View Temp Quiz Scores 1");
        ViewScoresButton.addItem("View Temp Quiz Scores 2");
        ViewScoresButton.addItem("View Temp Quiz Scores 3");
        
        TakeQuizButton.addActionListener(new ButtonListener());
        ViewScoresButton.addActionListener(new ButtonListener());
        JPanel flow1 = new JPanel();
        JPanel flow2 = new JPanel();
        flow1.add(TakeQuizButton);
        flow2.add(ViewScoresButton);
        
        JPanel toReturn = new JPanel(new GridLayout(2,1));
        toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
        toReturn.add(flow1);
        toReturn.add(flow2);
        return toReturn;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	// specific cards are identified by their names (Strings)
            String toShow = "";
        	if(e.getSource() == CreateCourseButton)
            {
        		// Code for setting up Model, View Controller:
            	Model model = new CreateCourseMod();
            	View view = new CreateCourseView();
            	Controller CreateCourseCtrl = new CreateCourseCtrl(model, view);
            	
            	cards.add(view, CARDPANEL1);
                toShow = CARDPANEL1;
            }
            else if(e.getSource() == CreateQuizButton)
            {
                JPanel CreateQuizCard = new CreateQuizView();
                cards.add(CreateQuizCard, CARDPANEL2);
                toShow = CARDPANEL2;
            }
            else if(e.getSource() == ViewReportButton)
            {
            	// Code for setting up Model, View Controller:
            	Model ViewReportModel = new ViewReportMod();
            	View ViewReportUI = new ViewReportView();
            	Controller ViewReportController = new ViewReportCtrl(ViewReportModel, ViewReportUI);
            	
            	// Call Controller method
            	String reportName = (String)ViewReportButton.getSelectedItem();
            	((ViewReportCtrl)ViewReportController).generateReport(reportName);
            	
            	// add to Navigator's ViewPanel
            	cards.add(ViewReportUI, CARDPANEL3);
            	toShow = CARDPANEL3;
            }
            else if(e.getSource() == TakeQuizButton)
            {
                JPanel TakeQuizCard = new JPanel();
                TakeQuizCard.add(new JLabel("Take Quiz View Panel"));
                cards.add(TakeQuizCard, CARDPANEL4);
                toShow = CARDPANEL4;
            }
            else if(e.getSource() == ViewScoresButton)
            {
            	// Code for setting up Model, View Controller:
            	Model model = new ViewQuizScoreModel();
            	View view = new ViewQuizScoreView();
            	Controller controller = new ViewQuizScoreController(model, view); 
            	
            	// Call Controller method
            	String scoreName = (String)ViewScoresButton.getSelectedItem();
            	((ViewQuizScoreController)controller).generateScore(scoreName);
            	
            	// add to Navigator's ViewPanel
                cards.add(view, CARDPANEL5);
                toShow = CARDPANEL5;
            }
            else if(e.getSource() == LogoutButton)
            {
            	System.out.println("logging off");
            	//TODO:	logout procedure
            	System.exit(0);
            }
            else
            {
                System.out.println("Unknown Source");
            }
            CardLayout c1 = (CardLayout)cards.getLayout();
            c1.first(cards); // for first click
            c1.show(cards, toShow);
            //System.out.println("You Clicked " + toShow); // for debugging
        }
    }
=======
package edu.asu.cse360.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.asu.cse360.model.*;
import edu.asu.cse360.control.*;

public class NavigatorPanel extends JFrame
{
	static final long serialVersionUID = 1l;
	
	final static String CARDPANEL1 = "Create Course";
    final static String CARDPANEL2 = "Create Quiz";
    final static String CARDPANEL3 = "View Quiz Report";
    final static String CARDPANEL4 = "Take Quiz";
    final static String CARDPANEL5 = "View Quiz Scores";
    JPanel cards; //a panel that uses CardLayout
    JComboBox CreateCourseButton, ViewReportButton,
    		TakeQuizButton, ViewScoresButton;
    JButton CreateQuizButton, LogoutButton;

    public void addComponentToPane(Container pane)
    {
    	// these will be contained in the OuterPane
        JLabel hello = new JLabel("<html><font size = \"10\">Welcome to the JiTT Program</font><html>");
        hello.setHorizontalAlignment(JLabel.CENTER);
        LogoutButton = new JButton("Logout");
        LogoutButton.addActionListener(new ButtonListener());
        JPanel upperPane = new JPanel(new BorderLayout());
        JPanel temp = new JPanel();
        temp.add(LogoutButton);
        upperPane.add(hello, BorderLayout.CENTER);
        upperPane.add(temp, BorderLayout.EAST);
        
        // Navigation Buttons. JMenu, JTree or whatever works best...
        JPanel navigationPane = new JPanel();
        
        boolean redo = true;
        while(redo)
        {
	        String s = (String)JOptionPane.showInputDialog(
	        		new JFrame(),
	        		"Please type either instructor or student\nEnter Log In Number:",
	        		"Log In",
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,
	        		null,
	        		"Enter ID Number here"
	        		);
	        if(s.compareToIgnoreCase("instructor") == 0)
	        {
	        	navigationPane = makeInstructor();
	        	redo = false;
	        }
	        else if(s.compareToIgnoreCase("student") == 0)
	        {
	            navigationPane = makeStudent();
	            redo = false;
	        }
	        else
	        	redo = true;
        }
        
        // CardLayout's not really good idea...
        cards = new JPanel(new CardLayout());
        
        // put everything together
        pane.add(upperPane, BorderLayout.NORTH);
        pane.add(navigationPane, BorderLayout.WEST);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    private JPanel makeInstructor()
    {
        // Instructor uses:
        CreateCourseButton = new JComboBox();
        CreateCourseButton.addItem(CARDPANEL1);
        // add created courses (in the case of edits)
        CreateCourseButton.addItem("Create New Course");
        CreateCourseButton.addItem("Edit Temp Course 1");
        CreateCourseButton.addItem("Edit Temp Course 2");
        
        CreateQuizButton = new JButton(CARDPANEL2);
        
        ViewReportButton = new JComboBox();
        ViewReportButton.addItem(CARDPANEL3);
        // add available quiz reports
        ViewReportButton.addItem("View Temp Quiz Report 1");
        ViewReportButton.addItem("View Temp Quiz Report 2");
        ViewReportButton.addItem("View Temp Quiz Report 3");
        
        CreateCourseButton.addActionListener(new ButtonListener());
        CreateQuizButton.addActionListener(new ButtonListener());
        ViewReportButton.addActionListener(new ButtonListener());
        JPanel flow1 = new JPanel();
        JPanel flow2 = new JPanel();
        JPanel flow3 = new JPanel();
        flow1.add(CreateCourseButton);
        flow2.add(CreateQuizButton);
        flow3.add(ViewReportButton);
        
        JPanel toReturn = new JPanel();
        toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
        toReturn.add(flow1);
        toReturn.add(flow2);
        toReturn.add(flow3);
        return toReturn;
    }
    
    public JPanel makeStudent()
    {
        // Student uses:
        TakeQuizButton = new JComboBox();
        TakeQuizButton.addItem(CARDPANEL4);
        // add available quizzes to take
        TakeQuizButton.addItem("Take Temp Quiz 1");
        TakeQuizButton.addItem("Take Temp Quiz 2");
        TakeQuizButton.addItem("Take Temp Quiz 3");

        ViewScoresButton = new JComboBox();
        ViewScoresButton.addItem(CARDPANEL5);
        // add available quizzes to view
        ViewScoresButton.addItem("View Temp Quiz Scores 1");
        ViewScoresButton.addItem("View Temp Quiz Scores 2");
        ViewScoresButton.addItem("View Temp Quiz Scores 3");
        
        TakeQuizButton.addActionListener(new ButtonListener());
        ViewScoresButton.addActionListener(new ButtonListener());
        JPanel flow1 = new JPanel();
        JPanel flow2 = new JPanel();
        flow1.add(TakeQuizButton);
        flow2.add(ViewScoresButton);
        
        JPanel toReturn = new JPanel(new GridLayout(2,1));
        toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
        toReturn.add(flow1);
        toReturn.add(flow2);
        return toReturn;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	// specific cards are identified by their names (Strings)
            String toShow = "";
        	if(e.getSource() == CreateCourseButton)
            {
        		// Code for setting up Model, View Controller:
            	Model model = new CreateCourseMod();
            	View view = new CreateCourseView();
            	Controller CreateCourseCtrl = new CreateCourseCtrl(model, view);
            	
            	cards.add(view, CARDPANEL1);
                toShow = CARDPANEL1;
            }
            else if(e.getSource() == CreateQuizButton)
            {
           //   JPanel CreateQuizCard = new CreateQuizView();
                // CreateQuizCard.add(new JLabel("Create Quiz View Panel"));
                 Model createQuizModel = new CreateQuizMod();
                 View createQuizUI = new CreateQuizView();
                 Controller createQuizController = new CreateQuizCtrl(createQuizModel,createQuizUI);
                // createQuizController.attachButtons();
               // createQuizController.attachButtons(); 
                 cards.add(createQuizUI, CARDPANEL2);
                toShow = CARDPANEL2;
            }
            else if(e.getSource() == ViewReportButton)
            {
            	// Code for setting up Model, View Controller:
            	Model ViewReportModel = new ViewReportMod();
            	View ViewReportUI = new ViewReportView();
            	Controller ViewReportController = new ViewReportCtrl(ViewReportModel, ViewReportUI);
            	
            	// Call Controller method
            	String reportName = (String)ViewReportButton.getSelectedItem();
            	((ViewReportCtrl)ViewReportController).generateReport(reportName);
            	
            	// add to Navigator's ViewPanel
            	cards.add(ViewReportUI, CARDPANEL3);
            	toShow = CARDPANEL3;
            }
            else if(e.getSource() == TakeQuizButton)
            {
                JPanel TakeQuizCard = new JPanel();
                TakeQuizCard.add(new JLabel("Take Quiz View Panel"));
                cards.add(TakeQuizCard, CARDPANEL4);
                toShow = CARDPANEL4;
            }
            else if(e.getSource() == ViewScoresButton)
            {
            	// Code for setting up Model, View Controller:
            	Model model = new ViewQuizScoreModel();
            	View view = new ViewQuizScoreView();
            	Controller controller = new ViewQuizScoreController(model, view); 
            	
            	// Call Controller method
            	String scoreName = (String)ViewScoresButton.getSelectedItem();
            	((ViewQuizScoreController)controller).generateScore(scoreName);
            	
            	// add to Navigator's ViewPanel
                cards.add(view, CARDPANEL5);
                toShow = CARDPANEL5;
            }
            else if(e.getSource() == LogoutButton)
            {
            	System.out.println("logging off");
            	//TODO:	logout procedure
            	System.exit(0);
            }
            else
            {
                System.out.println("Unknown Source");
            }
            CardLayout c1 = (CardLayout)cards.getLayout();
            c1.first(cards); // for first click
            c1.show(cards, toShow);
            //System.out.println("You Clicked " + toShow); // for debugging
        }
    }

>>>>>>> 5cb929cc8ac103fb609cb934aa437661f1535d49
}