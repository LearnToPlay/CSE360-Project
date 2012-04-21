<<<<<<< HEAD
package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Student extends User {
	
	public Student() throws ClassNotFoundException {
		super();
		setInstructor(false);
	}

	public static ArrayList<Student> getAllStudents() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `username`, `last`, `first`, `isInstructor` FROM `user` WHERE `isInstructor`= 0");
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Student s = new Student();
			s.setUserName(results.getString("username"));
			s.setLastName(results.getString("last"));
			s.setFirstName(results.getString("first"));
			students.add(s);
		}
				
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return students;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ArrayList<Student> sts = Student.getAllStudents();
			
			for (Student i : sts) {
				System.out.println(i.getUserName());
				System.out.println(i.getFirstName());
				System.out.println(i.getLastName());
				System.out.println(i.isInstructor());
			}
		} catch (Exception e) {
			
		}
	}

}
=======

package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Student extends User {
	
	public Student() throws ClassNotFoundException {
		super();
		setInstructor(false);
	}

	public static ArrayList<Student> getAllStudents() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `username`, `last`, `first`, `isInstructor` FROM `user` WHERE `isInstructor`= 0");
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Student s = new Student();
			s.setUserName(results.getString("username"));
			s.setLastName(results.getString("last"));
			s.setFirstName(results.getString("first"));
			students.add(s);
		}
				
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return students;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ArrayList<Student> sts = Student.getAllStudents();
			
			for (Student i : sts) {
				System.out.println(i.getUserName());
				System.out.println(i.getFirstName());
				System.out.println(i.getLastName());
				System.out.println(i.isInstructor());
			}
		} catch (Exception e) {
			
		}
	}
}
>>>>>>> 5cb929cc8ac103fb609cb934aa437661f1535d49
