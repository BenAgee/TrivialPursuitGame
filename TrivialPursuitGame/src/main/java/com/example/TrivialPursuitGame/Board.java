package com.example.TrivialPursuitGame;

import java.util.LinkedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends MainApp
{
    private GridPane boardPane;
    private Cell[][] cells;
    private LinkedList<Integer> validColumnsAndRows = new LinkedList<>();

    public Board()
    {
        boardPane = new GridPane();
        boardPane.setAlignment(Pos.CENTER);
        //boardPane.setHgap(10);
        //boardPane.setVgap(10);
        //boardPane.setPadding(new Insets(10));
        
        final String boardStyleStr = "-fx-background-color: white;\n"
        		+ "-fx-border-color: black;\n"
        		+ "-fx-border-width: 5;\n";
        
        //boardPane.setStyle(boardStyleStr);

        
        
        
        validColumnsAndRows.add(0);
        validColumnsAndRows.add(4);
        validColumnsAndRows.add(8);

        cells = new Cell[9][9];

        
        for (int col = 0; col < 9; col++)
        {
            for (int row = 0; row < 9; row++)
            {
                
                // Valid board cells and scorboard cells;
                if(validColumnsAndRows.contains(row) || validColumnsAndRows.contains(col) 
                		|| (row == 2 && col == 2) || (row == 2 && col == 6) || (row == 6 && col == 2) || (row == 6 && col == 6))
                {
                	Cell cell = new Cell(row, col);
                	cells[row][col] = cell;
                    boardPane.add(cell.getPane(), col, row);
                }
                
            }
        }
    }

    public void addPlayer(Player player)
    {
        System.out.println("adding player to board");
        
        System.out.println("checking player color to add: " + player.getColor());
        if(player.getColor() == Color.RED)
        {
        	Cell startCell = cells[0][0];
            startCell.addPlayer(player);
        } 
        else if(player.getColor()  == Color.YELLOW)
        {
        	Cell startCell = cells[0][8];
            startCell.addPlayer(player);
        	
        }
        else if(player.getColor()  == Color.BLUE)
        {
        	Cell startCell = cells[8][8];
            startCell.addPlayer(player);
        }
        else if(player.getColor()  == Color.GREEN)
        {
        	Cell startCell = cells[8][0];
            startCell.addPlayer(player);
        }
    }

    public GridPane getBoardPane()
    {
        return boardPane;
    }
    
    public Cell[][] getCells()
    {
    	return cells;
    }
}