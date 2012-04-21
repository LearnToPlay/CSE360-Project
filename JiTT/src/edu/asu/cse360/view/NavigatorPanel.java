
package edu.asu.cse360.view;

import edu.asu.cse360.model.*;
import edu.asu.cse360.control.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class NavigatorPanel extends JFrame
{
	static final long serialVersionUID = 1l;
	
	final static String CARDPANEL1 = "Create Course";
    final static String CARDPANEL2 = "Create Quiz";
    final static String CARDPANEL3 = "View Quiz Report";
    final static String CARDPANEL4 = "Take Quiz";
    final static String CARDPANEL5 = "View Quiz Scores";
    JTree CreateCourseTree, ViewReportTree,
    		TakeQuizTree, ViewScoresTree;
    JButton CreateQuizButton, LogoutButton;
	JPanel htmlPane; // will replace cards

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
        JScrollPane navigationPane = new JScrollPane();
        
        // Authentication a = new Authentication();
        
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
	        	//navigationPane = makeInstructor();
	        	navigationPane = createInstructorTree();
	        	redo = false;
	        }
	        else if(s.compareToIgnoreCase("student") == 0)
	        {
	            navigationPane = createStudentTree();
	            redo = false;
	        }
	        else
	        	redo = true;
        }
        
        htmlPane = new JPanel();
        htmlPane.add(new JLabel("Welcome page"));
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(navigationPane);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        navigationPane.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(150); 
        splitPane.setPreferredSize(new Dimension(600, 300));

        // put everything together
        pane.add(upperPane, BorderLayout.NORTH);
        pane.add(splitPane, BorderLayout.CENTER);
    }
    
    private JScrollPane createInstructorTree()
    {
        // Create the nodes and a tree that allows one selection at a time.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Edit a Course");
        createCourseNodes(top);
        CreateCourseTree = new JTree(top);
        CreateCourseTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        CreateCourseTree.addTreeSelectionListener(new TreeListener()); //Listen for when the selection changes.
        
        // Create Quiz Button
        CreateQuizButton = new JButton(CARDPANEL2);
        CreateQuizButton.addActionListener(new ButtonListener());
        JPanel flow = new JPanel();
        flow.add(CreateQuizButton);

        DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("View Quiz Report");
        viewQuizReportNodes(top2);
        ViewReportTree = new JTree(top2);
        ViewReportTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ViewReportTree.addTreeSelectionListener(new TreeListener()); //Listen for when the selection changes.
                
        //Create the scroll pane and add the tree to it.
        JPanel treeView = new JPanel();
        treeView.setLayout(new GridLayout(3,1));
        treeView.add(CreateCourseTree);
        treeView.add(flow);
        treeView.add(ViewReportTree);
        JScrollPane toReturn = new JScrollPane(treeView);
        
        return toReturn;
    }
    
    private JScrollPane createStudentTree()
    {
        // Create the nodes and a tree that allows one selection at a time.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Take a Quiz");
        takeQuizNodes(top);
        TakeQuizTree = new JTree(top);
        TakeQuizTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        TakeQuizTree.addTreeSelectionListener(new TreeListener()); //Listen for when the selection changes.

        DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("View Quiz Score");
        viewQuizScoreNodes(top2);
        ViewScoresTree = new JTree(top2);
        ViewScoresTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ViewScoresTree.addTreeSelectionListener(new TreeListener()); //Listen for when the selection changes.
                
        //Create the scroll pane and add the tree to it.
        JPanel treeView = new JPanel();
        treeView.setLayout(new GridLayout(2,1));
        treeView.add(TakeQuizTree);
        treeView.add(ViewScoresTree);
        JScrollPane toReturn = new JScrollPane(treeView);
        
        return toReturn;
    }
    
    private void displayURL(String url, String courseName, String quizName)
    {
    	System.out.println("Viewing: " + url + "\nCourse: " + courseName + "\nQuiz: " + quizName);
    	while(htmlPane.getComponentCount() != 0)
    		htmlPane.remove(htmlPane.getComponent(0));
    	
    	View view = new View();

    	// TODO: send message to create each UI
    	if(url == CARDPANEL1)
        {
    		// Code for setting up Model, View Controller:
        	Model model = new CreateCourseMod();
        	view = new CreateCourseView();
        	Controller CreateCourseCtrl = new CreateCourseCtrl(model, view);
        }
        else if(url == CARDPANEL2)
        {
        //   JPanel CreateQuizCard = new CreateQuizView();
            // CreateQuizCard.add(new JLabel("Create Quiz View Panel"));
             Model createQuizModel = new CreateQuizMod();
             view = new CreateQuizView();
             Controller createQuizController = new CreateQuizCtrl(createQuizModel,view);
            // createQuizController.attachButtons();
           // createQuizController.attachButtons();
        }
        else if(url == CARDPANEL3)
        {
        	// Code for setting up Model, View Controller:
        	Model ViewReportModel = new ViewReportMod();
        	view = new ViewReportView();
        	Controller ViewReportController = new ViewReportCtrl(ViewReportModel, view);
        	
        	// Call Controller method
        	//String reportName = (String)ViewReportButton.getSelectedItem();
        	((ViewReportCtrl)ViewReportController).generateReport(quizName);
        }
        else if(url == CARDPANEL4)
        {
            view.add(new JLabel("Take Quiz View Panel"));
        }
        else if(url == CARDPANEL5)
        {
        	// Code for setting up Model, View Controller:
        	Model model = new ViewQuizScoreModel();
        	view = new ViewQuizScoreView();
        	Controller controller = new ViewQuizScoreController(model, view); 
        	
        	// Call Controller method
        	//String scoreName = (String)ViewScoresButton.getSelectedItem();
        	//((ViewQuizScoreController)controller).generateScore(scoreName);
        }
    	
    	htmlPane.add(view);
    	htmlPane.updateUI(); // validate() or revalidate() ?
    }

    private void createCourseNodes(DefaultMutableTreeNode top)
    {
    	// TODO: get from Database the courses
    	/** dummy data first course **/
        DefaultMutableTreeNode category = new DefaultMutableTreeNode("Course 1");
        top.add(category);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode("Student 1");
        category.add(book);
        book = new DefaultMutableTreeNode("Student 2");
        category.add(book);
        book = new DefaultMutableTreeNode("Student 3");
        category.add(book);
        /** more dummy data **/
        category = new DefaultMutableTreeNode("Course 2");
        top.add(category);
        book = new DefaultMutableTreeNode("Student 4");
        category.add(book);
        book = new DefaultMutableTreeNode("Student 5");
        category.add(book);
    }
    
    private void viewQuizReportNodes(DefaultMutableTreeNode top)
    {
    	// TODO: get from Database the reports
    	/** dummy data first course **/
        DefaultMutableTreeNode category = new DefaultMutableTreeNode("Course 1");
        top.add(category);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode("Quiz Report 1");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Report 2");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Report 3");
        category.add(book);
        /** more dummy data **/
        category = new DefaultMutableTreeNode("Course 2");
        top.add(category);
        book = new DefaultMutableTreeNode("Quiz Report 4");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Report 5");
        category.add(book);
    }

    private void takeQuizNodes(DefaultMutableTreeNode top)
    {
    	// TODO: get from Database
    	/** dummy data first course **/
        DefaultMutableTreeNode category = new DefaultMutableTreeNode("Course 1");
        top.add(category);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode("Quiz 1");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz 2");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz 3");
        category.add(book);
        /** more dummy data **/
        category = new DefaultMutableTreeNode("Course 2");
        top.add(category);
        book = new DefaultMutableTreeNode("Quiz 4");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz 5");
        category.add(book);
    }

    private void viewQuizScoreNodes(DefaultMutableTreeNode top)
    {
    	// TODO: get from Database
    	/** dummy data first course **/
        DefaultMutableTreeNode category = new DefaultMutableTreeNode("Course 1");
        top.add(category);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode("Quiz Scores 1");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Scores 2");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Scores 3");
        category.add(book);
        /** more dummy data **/
        category = new DefaultMutableTreeNode("Course 2");
        top.add(category);
        book = new DefaultMutableTreeNode("Quiz Scores 4");
        category.add(book);
        book = new DefaultMutableTreeNode("Quiz Scores 5");
        category.add(book);
    }

    private class TreeListener implements TreeSelectionListener
    {
        /** Required by TreeSelectionListener interface. */
        public void valueChanged(TreeSelectionEvent e)
        {
        	String toShow = "";
        	DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        	
        	if(e.getSource() == CreateCourseTree)
        	{
    	        node = (DefaultMutableTreeNode)CreateCourseTree.getLastSelectedPathComponent();
    	        toShow = CARDPANEL1;
        	}
        	else if(e.getSource() == ViewReportTree)
        	{
    	        node = (DefaultMutableTreeNode)ViewReportTree.getLastSelectedPathComponent();
    	        toShow = CARDPANEL3;
        	}
        	else if(e.getSource() == TakeQuizTree)
        	{
    	        node = (DefaultMutableTreeNode)ViewReportTree.getLastSelectedPathComponent();
    	        toShow = CARDPANEL4;
        	}
        	else if(e.getSource() == ViewScoresTree)
        	{
    	        node = (DefaultMutableTreeNode)ViewReportTree.getLastSelectedPathComponent();
    	        toShow = CARDPANEL5;
        	}
        	else
        	{
        		System.out.println("error, unknown tree source");
        	}
        	
        	if (node == null) return;
        	
	        if (node.isLeaf())
	            displayURL(toShow, node.getParent().toString(), node.toString());
        }
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
          	if(e.getSource() == CreateQuizButton)
                 displayURL(CARDPANEL2, "No Course", "No Quiz");
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
        }
    }
}