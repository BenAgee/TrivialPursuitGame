import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.example.TrivialPursuitGame.*;
import java.lang.reflect.*;
import java.util.List;




class Test_MainApp   {

	@Test
	void testMain() 
	{
		
		MainApp tTesterApp = new MainApp();
		
	    try 
	    {   
			Class tTesterAppClass = tTesterApp.getClass();
			
			Field StageVal = tTesterAppClass.getDeclaredField("primaryStage");
			StageVal.setAccessible(true);
			Field AnswerCorrectVal = tTesterAppClass.getDeclaredField("answerCorrect");
			AnswerCorrectVal.setAccessible(true);
			Field BoardVal =  tTesterAppClass.getDeclaredField("board");
			BoardVal.setAccessible(true);
			Field AllPlayersVal = tTesterAppClass.getDeclaredField("allPlayers");
			AllPlayersVal.setAccessible(true);	
			
			Object tTestStageObj = StageVal.get(tTesterApp);
			Stage tTestStage = (Stage)tTestStageObj;
			
			Object tAnswerCorrectObj = AnswerCorrectVal.get(tTesterApp);
			Boolean tAnswerCorrect = (Boolean)tAnswerCorrectObj;
			
			Object tBoardObj = BoardVal.get(tTesterApp);
			Board tBoard = (Board)tBoardObj;
			
			Object tAllPlayersObj = AllPlayersVal.get(tTesterApp);
			List<Player> tAllPlayers = (List<Player>) tAllPlayersObj;
			
					
			assertEquals(tTestStage.getTitle(),"Trivial Pursuit");
			assertEquals(tAnswerCorrect,false);
			assertTrue(tBoard != null);
			assertTrue(tAllPlayers != null);
			
	
	    } 
	    catch(Exception e)
	    {
	          System.out.println(e.toString());
	    }
	}

	@Test
	void testAddPlayer() {
	
		MainApp tTesterApp = new MainApp();
		
	    try 
	    {
	    	Class tTesterAppClass = tTesterApp.getClass();
	    	Method tAddPlayerMethod = tTesterAppClass.getDeclaredMethod("addPlayer", String.class, Color.class); 
	    	
	    	tAddPlayerMethod.invoke(tTesterApp, "Player1",Color.RED);
	    	
			Field AllPlayersVal = tTesterAppClass.getDeclaredField("allPlayers");
			AllPlayersVal.setAccessible(true);	
			
			Field BoardVal = tTesterAppClass.getDeclaredField("board");
			BoardVal.setAccessible(true);
	    	
			Object tAllPlayersObj = AllPlayersVal.get(tTesterApp);
			List<Player> tAllPlayers = (List<Player>) tAllPlayersObj;
			
			Object tBoardObj = BoardVal.get(tTesterApp);
			Board tBoard = (Board)tBoardObj;
			
			assertEquals(tAllPlayers.size(),1);
			
			Player tAddedPlayer = tAllPlayers.get(0);
			
			assertEquals(tAddedPlayer.getColor(),Color.RED);
			assertEquals(tAddedPlayer.getName(),"Player1");
			
	    	tAddPlayerMethod.invoke(tTesterApp, "Player2",Color.GREEN);
	    	
	    	assertEquals(tAllPlayers.size(),1);
			Player tAddedPlayer2 = tAllPlayers.get(1);
			
			assertEquals(tAddedPlayer.getColor(),Color.GREEN);
			assertEquals(tAddedPlayer.getName(),"Player2");
	    	
	    } 
	    catch(Exception e)
	    {
	          System.out.println(e.toString());
	    }
	}
	
}
