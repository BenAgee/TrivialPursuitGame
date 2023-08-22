import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;


import com.example.TrivialPursuitGame.*;
import java.lang.reflect.*;

class Test_Player {

	@Test
	void testPlayer() {
		Player tPlayer = new Player("test1",Color.RED);
		
		assertFalse(tPlayer.getHasRedHqToken());
		assertFalse(tPlayer.getHasBlueHqToken());
		assertFalse(tPlayer.getHasGreenHqToken());
		assertFalse(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		assertEquals(tPlayer.getRow(),0);
		assertEquals(tPlayer.getCol(),4);
		assertEquals(tPlayer.getName(),"test1");
		assertEquals(tPlayer.getColor(),Color.RED);
		
	}

	@Test
	void testCollectHqToken() {
		
		Player tPlayer = new Player("test1",Color.RED);
		
		assertFalse(tPlayer.getHasRedHqToken());
		assertFalse(tPlayer.getHasBlueHqToken());
		assertFalse(tPlayer.getHasGreenHqToken());
		assertFalse(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		tPlayer.collectHqToken(Color.RED);
		
		assertTrue(tPlayer.getHasRedHqToken());
		assertFalse(tPlayer.getHasBlueHqToken());
		assertFalse(tPlayer.getHasGreenHqToken());
		assertFalse(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		tPlayer.collectHqToken(Color.BLUE);
		
		assertTrue(tPlayer.getHasRedHqToken());
		assertTrue(tPlayer.getHasBlueHqToken());
		assertFalse(tPlayer.getHasGreenHqToken());
		assertFalse(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		tPlayer.collectHqToken(Color.GREEN);
		
		assertTrue(tPlayer.getHasRedHqToken());
		assertTrue(tPlayer.getHasBlueHqToken());
		assertTrue(tPlayer.getHasGreenHqToken());
		assertFalse(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		tPlayer.collectHqToken(Color.YELLOW);
		
		assertTrue(tPlayer.getHasRedHqToken());
		assertTrue(tPlayer.getHasBlueHqToken());
		assertTrue(tPlayer.getHasGreenHqToken());
		assertTrue(tPlayer.getHasYellowHqToken());
		assertFalse(tPlayer.getHasAllHqTokens());
		
		tPlayer.checkIfPlayerHasAllHqTokens();
		
		assertTrue(tPlayer.getHasAllHqTokens());
		
	}



	@Test
	void testGetCurrentCategory() {
		
		Player tPlayer = new Player("test1",Color.RED);
		
		assertEquals(tPlayer.getCurrentCategory(),0);
		
		tPlayer.setCol(3);
		
		assertEquals(tPlayer.getCurrentCategory(),3);
		
		tPlayer.setCol(2);
		
		assertEquals(tPlayer.getCurrentCategory(),2);
		
		tPlayer.setCol(1);
		
		assertEquals(tPlayer.getCurrentCategory(),1);
	}




}
