package com.example.TrivialPursuitGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class Cell extends MainApp
{
    private StackPane cellPane;
    private List<Player> cellPlayers;
    private int row;
    private int col;
    private Boolean isRollAgainCell;
    private Boolean isHqCell;
    private int cellCategoryId;
    private String categoryStr;
    private Color cellColor;
    
    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
        
        isRollAgainCell = false;
        cellPane = new StackPane();
        cellPlayers = new ArrayList<>();
        
        setCellStyle();
    }
    
    public void addPlayer(Player player)
    {
    	
        cellPlayers.add(player);
        updatePlayerTokens();
    }

    public void removePlayer(Player player)
    {
        cellPlayers.remove(player);
        
        updatePlayerTokens();
    }
    
    public void setCellStyle()
    {
    	
    	cellPane.setPrefSize(100, 100);
    	
    	//center cell
    	if(row == 4 && col == 4)
    	{
    		cellColor = Color.BLACK;
        	final String styleStr = "-fx-background-color: black;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
        	

            cellPane.setStyle(styleStr);
            Label centerLabel = new Label("TCG");
            centerLabel.setTextFill(Color.WHITE);
            cellPane.getChildren().add(centerLabel);
            StackPane.setAlignment(centerLabel, Pos.CENTER);
            
    	}
    	
    	if((row== 0 && col == 0) || (row== 0 && col == 8) || (row== 8 && col == 0) || (row== 8 && col == 8))
		{
			isRollAgainCell = true;
			cellColor = Color.WHITE;
        	final String styleStr = "-fx-background-color: white;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
            Label label = new Label("Roll Again");
            cellPane.getChildren().add(label);
            StackPane.setAlignment(label, Pos.CENTER);
		}
    	
        if((row == 0 && col == 4) || (row== 4 && col == 0) || (row== 4 && col == 8) || (row== 8 && col == 4))
		{
			isHqCell = true;
			Label hqLabel = new Label("HQ");
			cellPane.getChildren().add(hqLabel);
			StackPane.setAlignment(hqLabel, Pos.CENTER);
		}
        else
        {
        	isHqCell = false;
        }
        
        // Red Cells
        if((row == 0 && col == 4) || (row == 1 && col == 0) || (row == 1 && col == 8) || (row == 4 && col == 3) || (row == 4 && col == 7) 
        	|| (row == 5 && col == 0) || (row == 5 && col == 8) || (row == 6 && col == 4) || (row == 8 && col == 2) || (row == 8 && col == 6) )
        {
        	
        	cellColor = Color.RED;
        	final String styleStr = "-fx-background-color: red;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);

        } // Blue Cells
        else if((row == 0 && col == 2) || (row == 0 && col == 6) || (row == 2 && col == 4) || (row == 3 && col == 0) || (row == 3 && col == 8) 
            	|| (row == 4 && col == 1) || (row == 4 && col == 5) || (row == 7 && col == 0) || (row == 7 && col == 8) || (row == 8 && col == 4) )
        {
        	cellColor = Color.BLUE;
        	final String styleStr = "-fx-background-color: blue;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }	// Green Cells
        else if((row == 0 && col == 3) || (row == 0 && col == 7) || (row == 2 && col == 0) || (row == 3 && col == 4) || (row == 4 && col == 2) 
        	|| (row == 4 && col == 8) || (row == 6 && col == 0) || (row == 7 && col == 4) || (row == 8 && col == 3) || (row == 8 && col == 7) )
        {
        	
        	cellColor = Color.GREEN;
        	final String styleStr = "-fx-background-color: green;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        	
        } // Yellow Cells
        else if((row == 0 && col == 1) || (row == 0 && col == 5) || (row == 1 && col == 4) || (row == 2 && col == 8) || (row == 4 && col == 0) 
            	|| (row == 4 && col == 6) || (row == 5 && col == 4) || (row == 6 && col == 8) || (row == 8 && col == 1) || (row == 8 && col == 5) )
        {
        	
        	cellColor = Color.YELLOW;
        	final String styleStr = "-fx-background-color: yellow;\n"
            		+ "-fx-border-color: black;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }
        //check for score cells
        else if(row == 2 && col == 2)
        {
        	// create for small sub cells inside;
        	cellColor = Color.WHITE;
        	final String styleStr = "-fx-background-color: white;\n"
            		+ "-fx-border-color: red;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }
        else if(row == 2 && col == 6)
        {
        	cellColor = Color.WHITE;
        	final String styleStr = "-fx-background-color: white;\n"
            		+ "-fx-border-color: yellow;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }
        else if(row == 6 && col == 2)
        {
        	cellColor = Color.WHITE;
        	final String styleStr = "-fx-background-color: white;\n"
            		+ "-fx-border-color: green;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }
        else if(row == 6 && col == 6)
        {
        	cellColor = Color.WHITE;
        	final String styleStr = "-fx-background-color: white;\n"
            		+ "-fx-border-color: blue;\n"
            		+ "-fx-border-width: 5;\n";
            
            cellPane.setStyle(styleStr);
        }
        
        //System.out.println("sell color after style is set: " + cellColor +  " cellCategoryIndx: " + cellCategoryIndex);
        

    }
    
    private void updatePlayerTokens()
    {
    	cellPane.getChildren().clear();
    	
    	setCellStyle();
    	
    	final String styleStr = ""
        		+ "-fx-border-color: black;\n"
        		+ "-fx-border-width: 5;\n";
    	
        for (Player player : cellPlayers) {
            Circle playerToken = new Circle(10, player.getColor());
            playerToken.setStyle(styleStr);
            cellPane.getChildren().add(playerToken);
        }
        
    }

    public void updatePlayerHqTokens(Player player, Color hqTokenColor)
    {
    	System.out.println("updatePlayerHqTokens called, cellColor: " + cellColor + " cellCategoryIndx: " + cellCategoryId);
        //cellPane.getChildren().clear();
        
    	
        if(hqTokenColor == Color.RED)
		{
        	System.out.println("got red player to add HQ token");
        	//color top left child red
        	Rectangle playerToken = new Rectangle(10,10,30,30);
//        	final String styleStr = "-fx-background-color: red;\n"
//            		+ "-fx-border-color: black;\n"
//            		+ "-fx-border-width: 1;\n";
//        	playerToken.setStyle(styleStr);
        	playerToken.setFill(Color.RED);
        	cellPane.getChildren().add(playerToken);
        	player.collectHqToken(Color.RED);
			
		}
		if(hqTokenColor == Color.BLUE)
		{
			//color bottom right child cell blue
			Rectangle playerToken = new Rectangle(40,35,30,30);
        	playerToken.setFill(Color.BLUE);
        	cellPane.getChildren().add(playerToken);
        	player.collectHqToken(Color.BLUE);
		}
		if(hqTokenColor == Color.GREEN)
		{
			//color bottom left child green
			Rectangle playerToken = new Rectangle(10,35,30,30);
        	playerToken.setFill(Color.GREEN);
        	cellPane.getChildren().add(playerToken);
        	player.collectHqToken(Color.GREEN);
			
		}
		if(hqTokenColor == Color.YELLOW)
		{
			//color top right child cell yellow
			Rectangle playerToken = new Rectangle(45,10,30,30);
        	playerToken.setFill(Color.YELLOW);
        	cellPane.getChildren().add(playerToken);
        	player.collectHqToken(Color.YELLOW);
		}
		player.checkIfPlayerHasAllHqTokens();
            
		System.out.println("cell color: "  + cellColor);
        
    }
    
    public void setCol(int newCol)
    {
    	col = newCol;
    }
    
    public void setRow(int newRow)
    {
    	row = newRow;
    }

    public StackPane getPane()
    {
        return cellPane;
    }
    
    public Boolean getIsRollAgainCell()
    {
    	return isRollAgainCell;
    }
    
    public Boolean getIsHqCell()
    {
    	return isHqCell;
    }
    
    public int getCellCategoryId()
    {
    	return cellCategoryId;
    }
    
    public void setCategoryId(int id)
    {
    	cellCategoryId = id;
    }
    
    public void formatScoreboardName(String playerName)
    {
    	cellPane.getChildren().clear();
    	Label playerNameLabel = new Label(playerName);
    	cellPane.getChildren().add(playerNameLabel);
    	
    	StackPane.setAlignment(playerNameLabel, Pos.BOTTOM_CENTER);

    }
    
    public Color getCellColor()
    {
    	return cellColor;
    }
    
    public String getCellCategory()
    {
    	return categoryStr;
    }
    
    public void setCategory(String category)
    {
    	categoryStr = category;
    }
    
}