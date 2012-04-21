package edu.asu.cse360.view;

import java.awt.*;
import javax.swing.*;

public class CreateCourseView extends View
{
	JList studentList;
	JComboBox semester, availableStudents;
	JButton addButton, deleteButton, saveButton, cancelButton;
	JTextField courseName;
	
	public CreateCourseView()
	{
		JLabel lab1 = new JLabel("Course Name: ");
		JLabel lab2 = new JLabel("Semeseter: ");
		JLabel lab3 = new JLabel("Students: ");
		JLabel lab4 = new JLabel("Select Student: ");
		
		// get data from database
		String[] availableSemesters = {"Summer | 2012", "Fall | 2012", "Spring | 2013"};
		String[] students = {"Betty", "Hakim", "Bob", "Phani", "Susan", "Lupe"};
		
		courseName = new JTextField(10);
		semester = new JComboBox(availableSemesters);
		studentList = new JList(students);
		JScrollPane scroll = new JScrollPane(studentList);
		Dimension d = new Dimension(100,100);
		scroll.setPreferredSize(d);
		deleteButton = new JButton("Delete");
		availableStudents = new JComboBox(students);
		addButton = new JButton("Add");
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		
		/*
		addButton.addActionListener(new ButtonListener());
		deleteButton.addActionListener(new ButtonListener());
		saveButton.addActionListener(new ButtonListener());
		cancelButton.addActionListener(new ButtonListener());
		 * */
		
		JPanel j = new JPanel();
		JPanel k = new JPanel();
		JPanel l = new JPanel();
		JPanel m = new JPanel();
		JPanel n = new JPanel();
		
		j.add(lab1); j.add(courseName);
		k.add(lab2); k.add(semester);
		l.add(lab3); l.add(scroll); l.add(deleteButton);
		m.add(lab4); m.add(availableStudents); m.add(addButton);
		n.add(saveButton); n.add(cancelButton);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(j); add(k); add(l); add(m); add(n);
		
		// msg:
		// Error!
		// A Course already exists with that name.
		// Please enter another file name.
	}
	
	public void addComponentToPane(Container pane)
    {
		pane.add(this);
	}
}