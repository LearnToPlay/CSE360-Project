<<<<<<< HEAD
package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Instructor extends User {
	
	public Instructor() throws ClassNotFoundException {
		super();
		setInstructor(true);
	}

	public static ArrayList<Instructor> getAllInstructors() throws Exception {
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `username`, `last`, `first`, `isInstructor` FROM `user` WHERE `isInstructor`= 1");
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Instructor i = new Instructor();
			i.setUserName(results.getString("username"));
			i.setLastName(results.getString("last"));
			i.setFirstName(results.getString("first"));
			instructors.add(i);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return instructors;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ArrayList<Instructor> ins = Instructor.getAllInstructors();
			
			for (Instructor i : ins) {
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

public class Instructor extends User {
	
	public Instructor() throws ClassNotFoundException {
		super();
		setInstructor(true);
	}

	public static ArrayList<Instructor> getAllInstructors() throws Exception {
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `username`, `last`, `first`, `isInstructor` FROM `user` WHERE `isInstructor`= 1");
		
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			Instructor i = new Instructor();
			i.setUserName(results.getString("username"));
			i.setLastName(results.getString("last"));
			i.setFirstName(results.getString("first"));
			instructors.add(i);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return instructors;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ArrayList<Instructor> ins = Instructor.getAllInstructors();
			
			for (Instructor i : ins) {
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
