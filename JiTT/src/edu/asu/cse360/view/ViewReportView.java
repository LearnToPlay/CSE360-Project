
package edu.asu.cse360.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import edu.asu.cse360.data.*;

public class ViewReportView extends View
{
	static final long serialVersionUID = 1l;
	
	private Color wrong = Color.red;
	private Color right = Color.green;
	private String reportName;
	private JPanel report;

    public ViewReportView()
    { }
    
    public void setReportName(String name)
    {
    	reportName = name;
    }

    public String getReportName()
    {
    	return reportName;
    }
   
    // should only be called from control
    public void showReport(Quiz key, ArrayList<double[]> percents)
    {
    	report = new JPanel(); // clear old report, if any
    	report.setLayout(new BoxLayout(report, BoxLayout.Y_AXIS));
    	JLabel title = new JLabel("<html><h1>" + getReportName() + "</h1></html>");
    	report.add(title); // title of report

    	// TODO: ask Chris how he's planning to show his quizzes
    	// get from instructor key quiz in module
    	
    	// for each QuizContent in key:
    		int correctAnswer = 0;
	    	JLabel answerA = new JLabel("A) Answer 1");
	    	JLabel answerB = new JLabel("B) Answer 2");
	    	JLabel answerC = new JLabel("C) Answer 3");
	    	JLabel answerD = new JLabel("D) Answer 4");
	    	
	    	// determine correctAnswer to color green
	    	switch(correctAnswer)
	    	{
	    	case 0: answerA.setForeground(right); break;
	    	case 1: answerB.setForeground(right); break;
	    	case 2: answerC.setForeground(right); break;
	    	case 3: answerD.setForeground(right); break;
	    	default: System.out.println("error, answer not enumerated type"); return;
	    	}

	    	// add each question to report
	    	JPanel question = new JPanel();
	    	question.setLayout(new BoxLayout(question, BoxLayout.Y_AXIS));
	    	question.add(new JLabel("Question 1"));
	    	question.add(answerA);
	    	question.add(answerB);
	    	question.add(answerC);
	    	question.add(answerD);
	    	report.add(question);
	    	
	    	// DrawBarGraph(percents[i], answer)
	    	JPanel barGraph = DrawBarGraph(0.1, correctAnswer);
	    	report.add(barGraph);
	    	
	    	// More Dummy Filler Data: {
	    	JLabel question2 = new JLabel("<html><br>Another Question 2" +
	    			"<br>A) Answer 1" +
	    			"<br>B) Answer 2" +
	    			"<br><font color = \"green\">C) Answer 3</font>" +
	    			"<br>D) Answer 4</html>");
	    	JPanel barGraph2 = DrawBarGraph(0.1, 2);
	    	JLabel question3 = new JLabel("<html><br>Yet Another Generic Question That no one cares about 3" +
	    			"<br>A) Answer 1" +
	    			"<br><font color = \"green\">B) Answer 2</font>" +
	    			"<br>C) Answer 3" +
	    			"<br>D) Answer 4</html>");
	    	JPanel barGraph3 = DrawBarGraph(0.1, 1);
	    	report.add(question2);
	    	report.add(barGraph2);
	    	report.add(question3);
	    	report.add(barGraph3);
	    	// }
	    
	    // place on this JPanel's view
	    add(report, BorderLayout.CENTER);
    	//validate() or revalidate()???
    	revalidate();
    }
    
    // code reused online
    public JPanel DrawBarGraph(double percent, int correctAnswer)
    {
    	double[] values = {1,2,4,3};
    	Graph bar = new Graph(values, correctAnswer);
		return bar;
    }
    
    // from ChartPanel.java example
    private class Graph extends JPanel
    {
		private static final long serialVersionUID = 1L;
		private String[] answers = {"A", "B", "C", "D"}; // should this be replaced by percentages?
    	private double[] values;
		private int correct;
	    
		// pre-set dimensions
		private Dimension d = new Dimension(300,100);
	    private int clientWidth = (int)d.getWidth();
		private int clientHeight = (int)d.getHeight();
		private int bottom = 30; // left side of bars, 50?
		private int barHeight = clientHeight/4;
		private int x = 10; // x position for labels
		private int labelHeight = -10; // 10 originally
		
		public Graph(double[] v, int correct)
		{
		    values = v;
		    this.correct = correct;
		    this.setPreferredSize(d);
    	}
    	  
    	public void paintComponent(Graphics g)
    	{
    		super.paintComponent(g);
    		if (values.length != 4)
      			return;
    		double minValue = 0;
    		double maxValue = 0;
    		for (int i = 0; i < values.length; i++)
    		{
      			if (minValue > values[i])
        			minValue = values[i];
      			if (maxValue < values[i])
        			maxValue = values[i];
    		}
    		if (maxValue == minValue)
      			return;
    		
    		double scale = (clientWidth-bottom)/(maxValue-minValue);
    		
    		for (int i=0; i<values.length; i++)
    		{
      			int valueY = i*barHeight+1;
      			int height = (int)(values[i]*scale);
      			if (values[i]<0)
        			height = -height;
      			
      			if(i == correct)
      				g.setColor(right);
      			else
      				g.setColor(wrong);
	      		g.fillRect(bottom, valueY, height, barHeight-2);
	      		g.setColor(Color.black);
	      		g.drawRect(bottom, valueY, height, barHeight-2);
	      		
	      		int y = i*barHeight+(barHeight-labelHeight)/2;
	      		g.drawString(answers[i], x, y);
    		}
      	}
    }
} // end of ViewReportView.java

