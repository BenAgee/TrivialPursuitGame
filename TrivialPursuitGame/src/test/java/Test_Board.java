import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

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

class Test_Board {
	
	
	@Test
	void testBoard() 
	{		
		MainApp tTesterApp = new MainApp();
		
	    try 
	    {   
			Class tTesterAppClass = tTesterApp.getClass();
	
			Field BoardVal =  tTesterAppClass.getDeclaredField("board");
			BoardVal.setAccessible(true);
			
			Object tBoardObj = BoardVal.get(tTesterApp);
			Board tBoard = (Board)tBoardObj;
			
			Class tBoardClass = tBoard.getClass();
			
			Field tBoardPaneVal = tBoardClass.getDeclaredField("boardPane");
			tBoardPaneVal.setAccessible(true);
			
			Object tBoardPaneObj = tBoardPaneVal.get(tBoard);
			GridPane tBoardPane = (GridPane)tBoardPaneObj;
			
			assertEquals(tBoardPane.getAlignment(),Pos.CENTER);
			
			
			
	    } 
	    catch(Exception e)
	    {
	          System.out.println(e.toString());
	    }
	}

	@Test
	void testAddPlayer() 
	{
		MainApp tTesterApp = new MainApp();
		
	    try 
	    {   
			Class tTesterAppClass = tTesterApp.getClass();
	
			Field BoardVal =  tTesterAppClass.getDeclaredField("board");
			BoardVal.setAccessible(true);
			
			Object tBoardObj = BoardVal.get(tTesterApp);
			Board tBoard = (Board)tBoardObj;
			
			Class tBoardClass = tBoard.getClass();
			
			Player tPlayer = new Player("player1",Color.RED);
			
			tBoard.addPlayer(tPlayer);
			
			Cell tCell = tBoard.getCells()[0][4];
			
			Class tCellClass = tCell.getClass();
			
			Field tPlayersVal = tCellClass.getDeclaredField("cellPlayers");
			Object tPlayersObj = tPlayersVal.get(tCell);
			
			List<Player> tPlayers = (List<Player>)tPlayersObj;
			
			assertEquals(tPlayers.size(),1);
			
	    } 
	    catch(Exception e)
	    {
	          System.out.println(e.toString());
	    }
	}



}
