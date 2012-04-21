package edu.asu.cse360.data;

import java.sql.*;
import java.util.ArrayList;

public class QuizContent extends SQLEntity {

	private int questionNumber;
	public static String questionLabel = "Type question here...";
	private String quizName;
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private Answers correctAnswer;
	
	public QuizContent() {
	}

	
	public String getQuizName() {
		return quizName;
	}


	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}


	public String getAnswerA() {
		return answerA;
	}


	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}


	public String getAnswerB() {
		return answerB;
	}


	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}


	public String getAnswerC() {
		return answerC;
	}


	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}


	public String getAnswerD() {
		return answerD;
	}


	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}


/*** Getter and Setter ***/
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setCorrectAnswer(Answers correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Answers getCorrectAnswer() {
		return correctAnswer;
	}

	public int insert() throws SQLException {
		StringBuffer query =new StringBuffer();
		
		query.append("INSERT INTO `quiz_content`(`quiz`, `question_num`, `question_text`, `choice_A`, `choice_B`, `choice_C`, `choice_D`, `correct_choice`) VALUES (");
		query.append(quotes(quizName));
		query.append(",");
		query.append(getQuestionNumber());
		query.append(",");
		query.append(quotes(this.getQuestion()));
		query.append(",");
		query.append(quotes(this.getAnswerA()));
		query.append(",");
		query.append(quotes(this.getAnswerB()));
		query.append(",");
		query.append(quotes(this.getAnswerC()));
		query.append(",");
		query.append(quotes(this.getAnswerD()));
		query.append(",");
		query.append(quotes(this.getCorrectAnswer().toString()));
		query.append(")");
		
		return super.insert(query.toString());
	}


	public int update() throws SQLException {
		StringBuffer query =new StringBuffer();
		
		query.append("UPDATE `quiz_content` SET `quiz`=");
		query.append(quotes(quizName));
		query.append(",`question_num`=");
		query.append(getQuestionNumber());
		query.append(",`question_text`=");
		query.append(quotes(this.getQuestion()));
		query.append(",`choice_A`=");
		query.append(quotes(this.getAnswerA()));
		query.append(",`choice_B`=");
		query.append(quotes(this.getAnswerB()));
		query.append(",`choice_C`=");
		query.append(quotes(this.getAnswerC()));
		query.append(",`choice_D`=");
		query.append(quotes(this.getAnswerD()));
		query.append(",`correct_choice`=");
		query.append(quotes(this.getCorrectAnswer().toString()));
		query.append(" WHERE `quiz`= ");
		query.append(quotes(quizName));
		query.append(" AND `question_num`=");
		query.append(getQuestionNumber());
		
		return super.update(query.toString());
	}


	public int delete() throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("DELETE FROM `quiz_content` WHERE `quiz`= ");
		query.append(quotes(quizName));
		query.append(" AND `question_num`=");
		query.append(getQuestionNumber());
		
		return super.delete(query.toString());
	}

	public boolean select() throws Exception {
		boolean success = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz`, `question_num`, `question_text`, `choice_A`, `choice_B`, `choice_C`, `choice_D`, `correct_choice` FROM `quiz_content` WHERE `quiz`= ");
		query.append(quotes(this.getQuizName()));
		query.append(" AND `question_num`=");
		query.append(getQuestionNumber());
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			this.setQuestion(results.getString("question_text"));
			this.setAnswerA(results.getString("choice_A"));
			this.setAnswerB(results.getString("choice_B"));
			this.setAnswerC(results.getString("choice_C"));
			this.setAnswerD(results.getString("choice_D"));
			this.setCorrectAnswer(Answers.getEnum(results.getString("correct_choice")));
			success = true;;
		}
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
						
		return success;
	}
		
	public static ArrayList<QuizContent> getQuizContents(String quiz) throws Exception {
		ArrayList<QuizContent> qContents = new ArrayList<QuizContent>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT `quiz`, `question_num`, `question_text`, `choice_A`, `choice_B`, `choice_C`, `choice_D`, `correct_choice` FROM `quiz_content` WHERE `quiz`= ");
		query.append(quotes(quiz));
	
		ResultSet results = null;
		Connection con = getConnection();
		// issue the SQL query to the database
	    Statement statement = con.createStatement();
	    // get the result of the SQL query
	    results = statement.executeQuery(query.toString());
	    
		while (results.next()) {
			QuizContent qc = new QuizContent();
			qc.setQuizName(quiz);
			qc.setQuestionNumber(results.getInt("question_num"));
			qc.setQuestion(results.getString("question_text"));
			qc.setAnswerA(results.getString("choice_A"));
			qc.setAnswerB(results.getString("choice_B"));
			qc.setAnswerC(results.getString("choice_C"));
			qc.setAnswerD(results.getString("choice_D"));
			qc.setCorrectAnswer(Answers.getEnum(results.getString("correct_choice")));
			qContents.add(qc);
		}
		
	    if(statement != null) {
	    	statement.close();
	    }
	    
	    SQLEntity.returnConnection(con);
				
		return qContents;
		
	}
	
	public static void main(String [] args) {
		try {
			QuizContent q = new QuizContent();
			
			q.setQuizName("Quiz 2");
			q.setQuestionNumber(2);
			
			q.delete();
/*			q.setQuestion("Why are cows always happy?");
			q.setAnswerA("Crazy?");
			q.setAnswerB("Stupid?");
			q.setAnswerC("Republican?");
			q.setAnswerD("Democrat?");
			q.setCorrectAnswer(Answers.getEnum("A"));
	
//			q.select();
			q.insert();
			System.out.println(q.getQuizName());
			System.out.println(q.getQuestionNumber());
			System.out.println(q.getQuestion());
			System.out.println(q.getAnswerA());
			System.out.println(q.getAnswerB());
			System.out.println(q.getAnswerC());
			System.out.println(q.getAnswerD());
			System.out.println(q.getCorrectAnswer());
			
//			q.setCorrectAnswer(Answers.getEnum("B"));
			
						
//			u.setLastName("Smith");
//			q.update();
			
//			System.out.println(u.delete());
			
			q.commit();
			
			ArrayList<QuizContent> c = QuizContent.getQuizContents("Quiz 1");
			
			for (QuizContent q : c) {
				System.out.println(q.getQuizName());
				System.out.println(q.getQuestionNumber());
				System.out.println(q.getQuestion());
				System.out.println(q.getAnswerA());
				System.out.println(q.getAnswerB());
				System.out.println(q.getAnswerC());
				System.out.println(q.getAnswerD());
				System.out.println(q.getCorrectAnswer());
				System.out.println("\n");
			}
*/			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
