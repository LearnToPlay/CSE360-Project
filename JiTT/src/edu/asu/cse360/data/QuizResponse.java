package edu.asu.cse360.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizResponse extends SQLEntity {
	
	private String quizName;
	private String courseName;
	private String studentName;
	private Answers response;
	private int questionNum;
	
	public QuizResponse() {
	
	}
	
	
	public int insert() throws SQLException {
		StringBuffer query =new StringBuffer();

		query.append("INSERT INTO `quiz_response`(`quiz`, `course`, `username`, `question_num`, `response`) VALUES (");
		query.append(quotes(getQuizName()));
		query.append(",");
		query.append(quotes(this.getCourseName()));
		query.append(",");
		query.append(quotes(this.getStudentName()));
		query.append(",");
		query.append(getQuestionNum());
		query.append(",");
		query.append(quotes(this.getResponse().toString()));
		
		return super.insert(query.toString());
	}

	public int update() throws SQLException {
		StringBuffer query =new StringBuffer();
		query.append("UPDATE `quiz_response` SET `quiz`=");
		query.append(quotes(getQuizName()));
		query.append(",`course`=");
		query.append(quotes(getCourseName()));
		query.append(",`username`=");
		query.append(quotes(getStudentName()));
		query.append(",`question_num`=");
		query.append(getQuestionNum());
		query.append(",`response`=");
		query.append(quotes(getResponse().toString()));
		query.append(" WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
		
		return super.update(query.toString());
	}

	public int delete() throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("DELETE FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
		
		return super.delete(query.toString());
	}

	public boolean select() throws Exception {
		boolean success = false;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT `quiz`, `course`, `username`, `question_num`, `response` FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(getQuizName()));
		query.append(" AND `course`=");
		query.append(quotes(getCourseName()));
		query.append(" AND `username`=");
		query.append(quotes(getStudentName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNum());
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setResponse(Answers.getEnum(results.getString("response")));
			success = true;;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
						
		return success;
	}
		
	public static ArrayList<QuizResponse> getQuizResponses(String quiz, String course, String user) throws Exception {
		ArrayList<QuizResponse> qResponses = new ArrayList<QuizResponse>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz`, `course`, `username`, `question_num`, `response` FROM `quiz_response` WHERE `quiz`= ");
		query.append(quotes(quiz));
		query.append(" AND `course`=");
		query.append(quotes(course));
		query.append(" AND `username`=");
		query.append(quotes(user));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			QuizResponse qr = new QuizResponse();
			qr.setQuizName(results.getString("quiz"));
			qr.setCourseName(results.getString("course"));
			qr.setStudentName(results.getString("username"));
			qr.setQuestionNum(results.getInt("question_num"));
			qr.setResponse(Answers.getEnum(results.getString("response")));
			qResponses.add(qr);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return qResponses;
		
	}
	
	public String getQuizName() {
		return quizName;
	}



	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public Answers getResponse() {
		return response;
	}



	public void setResponse(Answers response) {
		this.response = response;
	}



	public int getQuestionNum() {
		return questionNum;
	}



	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
