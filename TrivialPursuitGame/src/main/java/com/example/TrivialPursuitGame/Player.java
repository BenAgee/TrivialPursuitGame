package com.example.TrivialPursuitGame;

import javafx.scene.paint.Color;

public class Player extends MainApp
{
    private String name;
    private Color color;
    private int row;
    private int col;
    //private int scoreboardRow;
    //private int scoreboardCol;
    private Boolean hasRedHqToken;
    private Boolean hasBlueHqToken;
    private Boolean hasGreenHqToken;
    private Boolean hasYellowHqToken;
    private Boolean hasAllHqTokens;
    
    public String colorStr;

    @SuppressWarnings("exports")
	public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.hasRedHqToken = false;
        this.hasBlueHqToken = false;
        this.hasGreenHqToken = false;
        this.hasYellowHqToken = false;
        this.hasAllHqTokens = false;
        
        System.out.println("creating player object");
        
        System.out.println("player color: " + color);
        
        if(color == Color.RED)
        {
        	this.col = 4;
        	this.row = 0;
        	
        	colorStr = "Red";
        	
//        	this.scoreboardCol = 2;
//        	this.scoreboardRow = 2;
        } 
        else if(color == Color.YELLOW)
        {
        	this.col = 0;
        	this.row = 4;
        	
        	colorStr = "Yellow";
        	
//        	this.scoreboardCol = 6;
//        	this.scoreboardRow = 2;
        	
        }
        else if(color == Color.BLUE)
        {
        	this.col = 4;
        	this.row = 8;
        	
        	colorStr = "Blue";
        	
//        	this.scoreboardCol = 6;
//        	this.scoreboardRow = 6;
        }
        else if(color == Color.GREEN)
        {
        	this.col = 8;
        	this.row = 4;
        	
        	colorStr = "Green";
        	
//        	this.scoreboardCol = 2;
//        	this.scoreboardRow = 6;
        }
        
    }
    
    @SuppressWarnings({ "exports", "static-access" })
	public void collectHqToken(Color hqColor)
    {
    	if(hqColor == Color.RED)
    	{
    		hasRedHqToken = true;
    	}
    	else if(hqColor == Color.BLUE)
    	{
    		hasBlueHqToken = true;
    	}
    	else if(hqColor == Color.GREEN)
    	{
    		hasGreenHqToken = true;
    	}
    	else if(hqColor == color.YELLOW)
    	{
    		hasYellowHqToken = true;
    	}
    }
    
    public void checkIfPlayerHasAllHqTokens()
    {
    	if(hasRedHqToken && hasBlueHqToken && hasYellowHqToken && hasGreenHqToken)
    	{
    		hasAllHqTokens = true;
    	}
    }

    public String getName()
    {
        return name;
    }

    @SuppressWarnings("exports")
	public Color getColor()
    {
        return color;
    }

    public int getCurrentCategory()
    {
        return col % 4;
    }
    
    public int getRow()
    {
    	return row;
    }
    
    public int getCol()
    {
    	return col;
    }
    
    public void setHasRedHqToken(Boolean setVal)
    {
    	hasRedHqToken = setVal;
    }
    public void setHasBlueHqToken(Boolean setVal)
    {
    	hasBlueHqToken = setVal;
    }
    public void setHasGreenHqToken(Boolean setVal)
    {
    	hasRedHqToken  = setVal;
    }
    public void setHasYellowHqToken(Boolean setVal)
    {
    	hasRedHqToken  = setVal;
    }
    
    public Boolean getHasRedHqToken()
    {
    	return hasRedHqToken;
    }
    public Boolean getHasBlueHqToken()
    {
    	return hasRedHqToken;
    }
    public Boolean getHasGreenHqToken()
    {
    	return hasRedHqToken;
    }
    public Boolean getHasYellowHqToken()
    {
    	return hasRedHqToken;
    }

    public Boolean getHasAllHqTokens()
    {
    	return hasAllHqTokens;
    }
    
    public void setRow(int newRow)
    {
    	row = newRow;
    }
    
    public void setCol(int newCol)
    {
    	col = newCol;
    }
    
//    public int getScoreboardRow()
//    {
//    	return scoreboardRow;
//    }
//    
//    public int getScoreboardCol()
//    {
//    	return scoreboardCol;
//    }

}
