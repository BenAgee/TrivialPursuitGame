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
	
	// TODO Determine proper type to return question data
	public void getAllQuestions() {
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("Select * from QUESTIONS");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
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
			int i = stmt.executeUpdate();
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
			int i = stmt.executeUpdate("DELETE FROM questions");
			System.out.println("Questions deleted!");
			
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
			int result = stmt.executeUpdate();
			
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
	
	public int getCategoryIdFromName(String categoryName)
	{
		int catId = 0;
		
		String qryStr = "Select category_id from categories Where name=\'" + categoryName + "\';";
		
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
