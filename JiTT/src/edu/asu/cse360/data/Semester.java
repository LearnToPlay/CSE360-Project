<<<<<<< HEAD
package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Semester extends SQLEntity {

	String semName;
	java.sql.Timestamp semStart;
	java.sql.Timestamp semEnd;
	
	public Semester() throws ClassNotFoundException {
		super();
	}

	public String getName() {
		return semName;
	}

	public void setName(String semName) {
		this.semName = semName;
	}

	public java.sql.Timestamp getStartTime() {
		return semStart;
	}

	public void setStartTime(java.sql.Timestamp semStart) {
		this.semStart = semStart;
	}

	public java.sql.Timestamp getEndTime() {
		return semEnd;
	}

	public void setEndTime(java.sql.Timestamp semEnd) {
		this.semEnd = semEnd;
	}

	int insert() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getName() == null || this.getName().length() < 1) {
			throw new SQLException("Cannot persist a semeter with no name.");
		}
				
		query.append("INSERT INTO `semester`(`semester_name`, `start`, `end`) VALUES (");
		query.append(quotes(getName()));
		query.append(", '");
		query.append(this.getStartTime());
		query.append("', '");
		query.append(this.getEndTime());
		query.append("')");
		
		System.out.println(query.toString());
		
		return super.insert(query.toString());
	}

	public int update() throws Exception {
		int retValue = 0;
		StringBuffer query = new StringBuffer();
		if(getName() == null || getName().length() < 1) {
			throw new SQLException("Cannot persist a semester with no name.");
		}
		
		query.append("UPDATE `semester` SET `start`='");
		query.append(this.getStartTime());
		query.append("', `end`='");
		query.append(this.getEndTime());
		query.append("' WHERE `semester_name`=");
		query.append(quotes(this.getName()));
		query.append("");
		
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    retValue =  statement.executeUpdate(query.toString());
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
		return retValue;
	}

	public int delete() throws SQLException {
		int retValue = 0;
		StringBuffer query = new StringBuffer();
		if(getName() == null || getName().length() < 1) {
			throw new SQLException("Cannot delete a semester with no name.");
		}
		
		query.append("DELETE FROM `semester` WHERE `semester_name`=");
		query.append(quotes(getName()));
				
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    retValue =  statement.executeUpdate(query.toString());
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
		return retValue;
	}

	public boolean select() throws Exception{
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `semester_name`, `start`, `end` FROM `semester` WHERE `semester_name`= ");
		query.append(quotes(this.getName()));
		System.out.println(query.toString());
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setStartTime(results.getTimestamp("start"));
			this.setEndTime(results.getTimestamp("end"));
			success = true;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return success;
	}

	public static ArrayList<Semester> getAllSemesters() throws Exception {
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `semester_name`, `start`, `end` FROM `semester` WHERE 1");
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    

		while (results.next()) {
			Semester s = new Semester();
			s.setName(results.getString("semester_name"));
			s.setStartTime(results.getTimestamp("start"));
			s.setEndTime(results.getTimestamp("end"));
			semesters.add(s);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return semesters;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semester sem = null;
		
		// insert Semester
		try {
			/*
			sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			
			int i = sem.insert();
			
			sem.commit();
			
			System.out.println("number of records inserted " + i);
			
			
			sem = new Semester();
			sem.setName("Summer - 2012");
			sem.select();
//			if (sem.select()) {
				System.out.println("Semester: " + sem.getName());
				System.out.println("Semester start: " + sem.getStartTime());
				System.out.println("Semester end: " + sem.getEndTime());
				
//			} else {
	//			System.out.println("not found");
		//	}
			
			sem.commit();
			
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
				sem.update();
//				if (sem.select()) {
					System.out.println("Semester: " + sem.getName());
					System.out.println("Semester start: " + sem.getStartTime());
					System.out.println("Semester end: " + sem.getEndTime());
					
//				} else {
		//			System.out.println("not found");
			//	}
				
				sem.commit();
			
			
	
//				sem = new Semester();
//				sem.setName("Summer - 2012");
//				System.out.println(sem.delete());
				
//				sem.commit();
			*/
			
			ArrayList<Semester> sems = Semester.getAllSemesters();
			
			for (Semester s : sems) {
				System.out.println("Semester: " + s.getName());
				System.out.println("Semester start: " + s.getStartTime());
				System.out.println("Semester end: " + s.getEndTime());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
=======

package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Semester extends SQLEntity {

	String semName;
	java.sql.Timestamp semStart;
	java.sql.Timestamp semEnd;
	
	public Semester() throws ClassNotFoundException {
		super();
	}

	public String getName() {
		return semName;
	}

	public void setName(String semName) {
		this.semName = semName;
	}

	public java.sql.Timestamp getStartTime() {
		return semStart;
	}

	public void setStartTime(java.sql.Timestamp semStart) {
		this.semStart = semStart;
	}

	public java.sql.Timestamp getEndTime() {
		return semEnd;
	}

	public void setEndTime(java.sql.Timestamp semEnd) {
		this.semEnd = semEnd;
	}

	int insert() throws SQLException {
		StringBuffer query = new StringBuffer();
		if(this.getName() == null || this.getName().length() < 1) {
			throw new SQLException("Cannot persist a semeter with no name.");
		}
				
		query.append("INSERT INTO `semester`(`semester_name`, `start`, `end`) VALUES (");
		query.append(quotes(getName()));
		query.append(", '");
		query.append(this.getStartTime());
		query.append("', '");
		query.append(this.getEndTime());
		query.append("')");
		
		System.out.println(query.toString());
		
		return super.insert(query.toString());
	}

	public int update() throws Exception {
		int retValue = 0;
		StringBuffer query = new StringBuffer();
		if(getName() == null || getName().length() < 1) {
			throw new SQLException("Cannot persist a semester with no name.");
		}
		
		query.append("UPDATE `semester` SET `start`='");
		query.append(this.getStartTime());
		query.append("', `end`='");
		query.append(this.getEndTime());
		query.append("' WHERE `semester_name`=");
		query.append(quotes(this.getName()));
		query.append("");
		
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    retValue =  statement.executeUpdate(query.toString());
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
		return retValue;
	}

	public int delete() throws SQLException {
		int retValue = 0;
		StringBuffer query = new StringBuffer();
		if(getName() == null || getName().length() < 1) {
			throw new SQLException("Cannot delete a semester with no name.");
		}
		
		query.append("DELETE FROM `semester` WHERE `semester_name`=");
		query.append(quotes(getName()));
				
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    retValue =  statement.executeUpdate(query.toString());
	    
	    if(statement != null) {
	    	statement.close();
	    }
	    
		return retValue;
	}

	public boolean select() throws Exception{
		boolean success = false;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT `semester_name`, `start`, `end` FROM `semester` WHERE `semester_name`= ");
		query.append(quotes(this.getName()));
		System.out.println(query.toString());
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setStartTime(results.getTimestamp("start"));
			this.setEndTime(results.getTimestamp("end"));
			success = true;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return success;
	}

	public static ArrayList<Semester> getAllSemesters() throws Exception {
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `semester_name`, `start`, `end` FROM `semester` WHERE 1");
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    

		while (results.next()) {
			Semester s = new Semester();
			s.setName(results.getString("semester_name"));
			s.setStartTime(results.getTimestamp("start"));
			s.setEndTime(results.getTimestamp("end"));
			semesters.add(s);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return semesters;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semester sem = null;
		
		// insert Semester
		try {
			/*
			sem = new Semester();
			sem.setName("Summer - 2012");
			sem.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
			
			int i = sem.insert();
			
			sem.commit();
			
			System.out.println("number of records inserted " + i);
			
			
			sem = new Semester();
			sem.setName("Summer - 2012");
			sem.select();
//			if (sem.select()) {
				System.out.println("Semester: " + sem.getName());
				System.out.println("Semester start: " + sem.getStartTime());
				System.out.println("Semester end: " + sem.getEndTime());
				
//			} else {
	//			System.out.println("not found");
		//	}
			
			sem.commit();
			
			sem.setEndTime(new java.sql.Timestamp(System.currentTimeMillis() + 360000L));
				sem.update();
//				if (sem.select()) {
					System.out.println("Semester: " + sem.getName());
					System.out.println("Semester start: " + sem.getStartTime());
					System.out.println("Semester end: " + sem.getEndTime());
					
//				} else {
		//			System.out.println("not found");
			//	}
				
				sem.commit();
			
			
	
//				sem = new Semester();
//				sem.setName("Summer - 2012");
//				System.out.println(sem.delete());
				
//				sem.commit();
			*/
			
			ArrayList<Semester> sems = Semester.getAllSemesters();
			
			for (Semester s : sems) {
				System.out.println("Semester: " + s.getName());
				System.out.println("Semester start: " + s.getStartTime());
				System.out.println("Semester end: " + s.getEndTime());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
>>>>>>> 5cb929cc8ac103fb609cb934aa437661f1535d49
