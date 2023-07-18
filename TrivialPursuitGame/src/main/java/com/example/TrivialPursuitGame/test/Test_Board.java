package com.example.TrivialPursuitGame.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import com.example.TrivialPursuitGame.Board;


class Test_Board {

	@Test
	void testBoard() 
	{
		Board tBoard = new Board();
		GridPane tBoardPane = tBoard.getBoardPane();
		
		assertEquals(tBoardPane.getAlignment(), Pos.CENTER); 
	
	}

	@Test
	void testAddPlayer() 
	{
		assertTrue(true);
	}

	@Test
	void testGetBoardPane() 
	{
		assertTrue(true);
	}

	@Test
	void testGetCells() 
	{
		assertTrue(true);
	}

}
