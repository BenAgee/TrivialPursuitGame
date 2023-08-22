package com.example.TrivialPursuitGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class DBConnection{  
	static Connection con;
	
	final private String mysqlUsername = "trivia"; // changed from test
	final private String mysqlPw = "poiulkjh"; // changed from test
	final private String portNumber = "3306"; // changed from 2918
	
	public DBConnection() {
		con = connectDB();
		System.out.println("Connection Succesful!");
	}
	
	public ArrayList<String> getAllQuestions() {
		ArrayList<String> questions = new ArrayList<>(1000);
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("Select question from QUESTIONS");
			while(rs.next()) {
				System.out.println(rs.getString(1));
				questions.add(rs.getString(1));
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return questions;
	}
	
	public ArrayList<String> getQuestionsFromCategory(int category_id) 
	{
		ArrayList<String> questions = new ArrayList<>(1000);
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("Select question from QUESTIONS Where category_id = " + category_id + ";");
			while(rs.next()) {
				System.out.println(rs.getString(1));
				questions.add(rs.getString(1));
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return questions;
	}
	
	public int addQuestion(String question, String answer, String category) {
		PreparedStatement stmt;
		PreparedStatement getCategory;
		int category_id;
		
		try {
			getCategory = con.prepareStatement(
					"SELECT category_id FROM categories WHERE name = ?");
			getCategory.setString(1, category);
			ResultSet result = getCategory.executeQuery();
			result.next();
			category_id = result.getInt("category_id");

			System.out.println(category_id);
			stmt = con.prepareStatement(
					"INSERT INTO QUESTIONS (question, answer, category_id) VALUES (?,?,?)"); // fixed this, category was spelled "catagory"
			stmt.setString(1,question);
			stmt.setString(2, answer);
			stmt.setInt(3, category_id);
			stmt.executeUpdate();
			System.out.println("Question added!");
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public void deleteAllQuestions() {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM questions");
			System.out.println("Questions deleted!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllCategories() {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM categories");
			System.out.println("Cagtegories deleted!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCategory(String category_name) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(
					"INSERT INTO categories (name) VALUES (?)");
			stmt.setString(1, category_name);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCategory(String category_name) {
		PreparedStatement stmt;
		
		int catId = getCategoryIdFromName(category_name);
		
		try {
			stmt = con.prepareStatement(
					"delete from categories where category_id = " + catId + ";");
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		deleteAllQuestionsFromCategory(catId);
	}
	
	public void deleteQuestion(String question) {
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(
					"delete from questions where question = \'" + question + "\';");
			
			System.out.println(stmt);
			int result = stmt.executeUpdate();
			
			System.out.println("result: " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteQuestionByCategory(String category, String question) {
		PreparedStatement stmt;
		
		int catId = getCategoryIdFromName(category);
		
		try {
			stmt = con.prepareStatement(
					"delete from questions where question = \'" + question + "\' and category_id = "+ catId +";");
			
			System.out.println(stmt);
			int result = stmt.executeUpdate();
			
			System.out.println("result: " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteAllQuestionsFromCategory(int catId)
	{
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(
					"delete from questions where category_id = "+ catId +";");
			
			System.out.println(stmt);
			int result = stmt.executeUpdate();
			
			System.out.println("result: " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getAllCategories() {
		ArrayList<String> categories = new ArrayList<>(10);
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("Select * from CATEGORIES");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				categories.add(rs.getString(2));
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return categories;
	}
	
	public Map<String, String> getQuestion(int category_id)
	{
		Map<String, String> QApair = new HashMap<String, String>();
		
		System.out.println("cat id:" + category_id);
		// gets a random question and answer corresponding to the chosen category
		String qryStr = "Select question, answer from questions Where category_id = " + category_id + " order by rand() limit 1;";
		
		System.out.println(qryStr);
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(qryStr);
			
			rs.next(); // get first row in result set, should only have one row
			
			String question = rs.getString(1);
			String answer = rs.getString(2);
			System.out.println(question +", "+ answer);
			
			QApair.put(question, answer);

			
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return QApair;
	}
	
	public Boolean doesQuestionExist(String question)
	{
		Boolean questionExists = false;
		
		String qryStr = "Select question from questions Where question = \'" + question + "\'";
		
		System.out.println(qryStr);
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(qryStr);
			
			if(rs.next())
			{
				questionExists = true;
			}
			
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return questionExists;
	}
	
	public Boolean doesCategoryExist(String category)
	{
		Boolean categoryExists = false;
		
		String qryStr = "Select * from categories Where name = \'" + category + "\'";
		
		System.out.println(qryStr);
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(qryStr);
			
			if(rs.next())
			{
				categoryExists = true;
			}
			
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return categoryExists;
	}
	
	public Boolean doesCategoryHaveQuestions(String category)
	{
		Boolean hasQuestions = false;
		
		int catId = getCategoryIdFromName(category);
		
		String qryStr = "Select * from questions Where category_id = \'" + catId + "\'";
		
		System.out.println(qryStr);
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(qryStr);
			
			if(rs.next())
			{
				hasQuestions = true;
			}
			
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return hasQuestions;
	}
	
	public int getCategoryIdFromName(String categoryName)
	{
		int catId = 0;
		
		String qryStr = "Select * from categories Where name=\'" + categoryName + "\';";
		
		//System.out.println(qryStr);
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(qryStr);
			
			rs.next(); // get first row in result set, should only have one row
			
			catId = rs.getInt(1);
			
			
		} catch (Exception e) { 
			System.out.println(e);
		}
		
		return catId;
	}
	
	private Connection connectDB() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:"+ portNumber+"/TRIVIA_COMPUTE", mysqlUsername, mysqlPw);
			
			System.out.println("made connection");
			return con;
		} catch (Exception e) { 
			System.out.println(e);
		}
		return con;
	}
}  
