package com.example.TrivialPursuitGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import com.example.TrivialPursuitGame.Board;
import javafx.scene.control.Label;
import javafx.application.Application;

import com.example.TrivialPursuitGame.*;
import java.lang.reflect.*;
import java.util.ArrayList;

class Test_DBConnection {

	@Test
	void testDBConnection() {
		DBConnection tDB = new DBConnection();
	}

	@Test
	void testGetAllQuestions() {
		DBConnection tDB = new DBConnection();
		
		 ArrayList<String> tQuestions = tDB.getAllQuestions();
		 assertFalse(tQuestions.isEmpty());
	}

	@Test
	void testAddQuestion() {
		DBConnection tDB = new DBConnection();
		
		 ArrayList<String> tQuestions = tDB.getAllQuestions();
		 assertFalse(tQuestions.isEmpty());
		 
		 int  tBeforeSize = tQuestions.size();
		 
		 assertEquals(tDB.addQuestion("Test Question", "test Answer", "Geography"),1);
		 
		 tQuestions = tDB.getAllQuestions();
		 
		 int tAfterSize = tQuestions.size();
		 
		 assertTrue(tAfterSize > tBeforeSize);
	}	

	@Test
	void testGetAllCategories() {
		DBConnection tDB = new DBConnection();
		
		 ArrayList<String> tCats = tDB.getAllQuestions();
		 assertFalse(tCats.isEmpty());
		 
	
	}

	@Test
	void testAddCategory() {
		DBConnection tDB = new DBConnection();
		
		 ArrayList<String> tCats = tDB.getAllQuestions();
		 assertFalse(tCats.isEmpty());
		 
		 int  tBeforeSize = tCats.size();
		 
		 tDB.addCategory("TestCat");
		 
		 tCats = tDB.getAllCategories();
		 
		 int tAfterSize = tCats.size();
		 
		 assertTrue(tAfterSize > tBeforeSize);
	}



}
