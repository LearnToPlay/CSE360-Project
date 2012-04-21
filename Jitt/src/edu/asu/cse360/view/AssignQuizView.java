package edu.asu.cse360.view;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

public class AssignQuizView extends JPanel
{
	JLabel title;
	JComboBox timeBox1, timeBox2, timeBox3, timeBox4,
			timeBox5, timeBox6, monthBox1, monthBox2;
	JTable dates1, dates2;
	ListSelectionModel rowSM1, rowSM2;
	JButton assignButton, cancelButton;
	JTextField text;
	
	public AssignQuizView()
	{
		title = new JLabel("Assign Quiz");
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Dimension d = new Dimension(200,180);
		
		JLabel quizNameLab = new JLabel("Please Enter a Name for the Quiz: ");
		text = new JTextField(20);
		JPanel enterNamePanel = new JPanel();
		enterNamePanel.add(quizNameLab);
		enterNamePanel.add(text);
		
		JLabel assignClassLab = new JLabel("Assign the Quiz to a class: ");
		JComboBox assignClassBox = new JComboBox();
		assignClassBox.addItem("Class 1");
		assignClassBox.addItem("Class 2");
		assignClassBox.addItem("Class 3");
		JPanel assignClass = new JPanel();
		assignClass.add(assignClassLab);
		assignClass.add(assignClassBox);

		String[] months = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		String[] weekdays = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		Object[][] specificDates = new Object[5][7];
		for(int i = 0; i < 31; i++)
		{
			if(i < 7)
				specificDates[0][i] = i+1;
			else if(i < 14)
				specificDates[1][i-7] = i+1;
			else if(i < 21)
				specificDates[2][i-14] = i+1;
			else if(i < 28)
				specificDates[3][i-21] = i+1;
			else if(i < 35)
				specificDates[4][i-28] = i+1;
		}
		
		//start time
		JLabel timeStartLab = new JLabel("Set Start Time");
		timeStartLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JLabel colon1 = new JLabel(" : ");
		timeBox1 = new JComboBox(); // Hour
		for(int i = 1; i < 13; i++)
			timeBox1.addItem(i);
		timeBox2 = new JComboBox(); // Minutes
		for(int i = 0; i < 60; i++)
			if(i<10)
				timeBox2.addItem("0"+i);
			else
				timeBox2.addItem(i);
		timeBox3 = new JComboBox(); // Meridium
		timeBox3.addItem("pm");
		timeBox3.addItem("am");
		
		JPanel time1 = new JPanel();
		time1.add(timeBox1);
		time1.add(colon1);
		time1.add(timeBox2);
		time1.add(timeBox3);		
		
		monthBox1 = new JComboBox(months);
		dates1 = new JTable(specificDates, weekdays);
		dates1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dates1.getModel().addTableModelListener(new listener());
		dates1.setCellSelectionEnabled(true);
		//dates1.setEnabled(false); // disables editing and selection
		//rowSM1 = dates1.getSelectionModel();
		//rowSM1.addListSelectionListener(new listener());
		
		JPanel calendar1 = new JPanel();
		calendar1.setPreferredSize(d);
		calendar1.setLayout(new BoxLayout(calendar1, BoxLayout.Y_AXIS));
		calendar1.add(timeStartLab);		
		calendar1.add(time1);
		calendar1.add(monthBox1);
		calendar1.add(dates1.getTableHeader());
		calendar1.add(dates1);
		
		// end time
		JLabel timeEndLab = new JLabel("Set End Time");
		timeEndLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JLabel colon2 = new JLabel(" : ");
		timeBox4 = new JComboBox(); // Hour
		for(int i = 1; i < 13; i++)
			timeBox4.addItem(i);
		timeBox5 = new JComboBox(); // Minutes
		for(int i = 0; i < 60; i++)
			if(i<10)
				timeBox5.addItem("0" + i);
			else
				timeBox5.addItem(i);
		timeBox6 = new JComboBox(); // Meridium
		timeBox6.addItem("pm");
		timeBox6.addItem("am");
		
		JPanel time2 = new JPanel();
		time2.add(timeBox4);
		time2.add(colon2);
		time2.add(timeBox5);
		time2.add(timeBox6);		
		
		monthBox2 = new JComboBox(months);
		dates2 = new JTable(specificDates, weekdays);
		dates2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dates2.getModel().addTableModelListener(new listener());
		//rowSM2 = dates2.getSelectionModel();
		//rowSM2.addListSelectionListener(new listener());
		
		JPanel calendar2 = new JPanel();
		calendar2.setPreferredSize(d);
		calendar2.setLayout(new BoxLayout(calendar2, BoxLayout.Y_AXIS));
		calendar2.add(timeEndLab);
		calendar2.add(time2);
		calendar2.add(monthBox2);
		calendar2.add(dates2.getTableHeader());
		calendar2.add(dates2);
		
		JPanel flow = new JPanel();
		flow.add(calendar1);
		flow.add(calendar2);
		
		// can't forget to add the buttons on the bottom
		assignButton = new JButton("Assign");
		// assignButton.addActionListener(new ButtonListener());
		cancelButton = new JButton("Cancel");
		// cancelButton.addActionListener(new ButtonListener());
		JPanel buttons = new JPanel();
		buttons.add(assignButton);
		buttons.add(cancelButton);
		
		// put everything together
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(title);
		add(enterNamePanel);
		add(assignClass);
		add(flow);
		add(buttons);	
	}
	
	public JButton getAssignButton(){
		return assignButton;
	}
	
	public void addComponentToPane(Container pane)
    {
		pane.add(this);
	}
	
	private class listener implements TableModelListener // ListSelectionListener
	{
		/*
		public void valueChanged(ListSelectionEvent e)
		{
			// why is this this method called twice?
			if(e.getSource() == rowSM1)
			{
				System.out.println("Start Date: Column " + dates1.getSelectedColumn()
						+ " and Row " + dates1.getSelectedRow());
			}
			else if(e.getSource() == rowSM2)
			{
				System.out.println("End Date: Column " + dates2.getSelectedColumn()
						+ " and Row " + dates2.getSelectedRow());
			}
	    }*/
		
		public void tableChanged(TableModelEvent e)
		{
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        String columnName = model.getColumnName(column);
	        Object data = model.getValueAt(row, column);
	        System.out.println("Day: " + columnName + "; value: " + data);
		}
	}
}