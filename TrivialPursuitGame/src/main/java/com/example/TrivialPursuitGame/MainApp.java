package com.example.TrivialPursuitGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.Dialog;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MainApp extends Application
{

    private LinkedList<String> categoryList = new LinkedList<>();
    
    private String redCategory;
    private String blueCategory;
    private String greenCategory;
    private String yellowCategory;
    

    private Board board;
    private List<Player> allPlayers;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private Cell currentCell;
    private Boolean answerCorrect;

    private Label currentPlayerLabel;
    private Label categoryLabel;
    private Label questionLabel;
    private Label rollNumberLabel;
    private Button rollButton;
    private int rollNumber;   
    
    private Button moveUpButton;
    private Button moveDownButton;
    private Button moveLeftButton;
    private Button moveRightButton;
    
    private Stage primaryStage;

    public static void main(String[] args)
    {

    	launch(args);
        
    }

    @Override
    public void start(Stage primaryStageInput)
    {
    	primaryStage = primaryStageInput;
        primaryStage.setTitle("Trivial Pursuit");
       
        answerCorrect = false;

        board = new Board();
        allPlayers = new ArrayList<>();
        currentPlayerIndex = 0;
        
        

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(createHeader());
        root.setCenter(board.getBoardPane());
        root.setBottom(createFooter());

        StartGameDialog sgd = new StartGameDialog();
        sgd.showAndWait();
        
        System.out.println("added players: " + sgd.playerNameList);
        System.out.println("chosen category for red: " + sgd.redCategoryName);
        System.out.println("valid game: " + sgd.validGameParameters);
        
        if(!sgd.validGameParameters)
        {
        	return;
        }
        
        redCategory = sgd.redCategoryName;
        blueCategory = sgd.blueCategoryName;
        greenCategory = sgd.greenCategoryName;
        yellowCategory = sgd.yellowCategoryName;
        
        for(int playerCnt = 0; playerCnt < sgd.playerNameList.size(); playerCnt ++)
        {
        	Color playerColor = getPlayerColorFromInt(playerCnt);
        	String playerName = sgd.playerNameList.get(playerCnt);

        	addPlayer(playerName, playerColor);
        }

        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Add Dialog to ask how many players want to play. Enter Players Names, then add players below.

        //addPlayer("Player 1-Red", Color.RED);
        //addPlayer("Player 2-Blue", Color.BLUE);
        //addPlayer("Player 3-Green", Color.GREEN);
       // addPlayer("Player 4-Yellow", Color.YELLOW);
        
        // set categories based on input from game start dialog that still needs to be made
        
        
        setCategories();
        
        Random rand = new Random();
        if(allPlayers.size()>1)
        {
        	currentPlayerIndex = rand.nextInt(allPlayers.size()-1);
        }
        
        currentPlayer = allPlayers.get(currentPlayerIndex);
        	
        currentPlayerLabel.setText("Player's Turn: " + currentPlayer.getName() + "-" + currentPlayer.colorStr);
        
        currentCell = board.getCells()[currentPlayer.getRow()][currentPlayer.getCol()];
        
    }

    private HBox createHeader()
    {
        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(20);

        currentPlayerLabel = new Label();
        currentPlayerLabel.setStyle("-fx-font-size: 18px;");
        categoryLabel = new Label();
        categoryLabel.setStyle("-fx-font-size: 18px;");
        questionLabel = new Label();
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 14px;");
        questionLabel.setMaxWidth(200);

        headerBox.getChildren().addAll(currentPlayerLabel, categoryLabel, questionLabel);
        return headerBox;
    }

    private HBox createFooter()
    {
        HBox footerBox = new HBox();
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setSpacing(20);

        rollButton = new Button("Roll");
        rollButton.setOnAction(e -> rollButtonClicked());
        
        moveUpButton = new Button("Move Up");
        moveDownButton = new Button("Move Down");
        moveLeftButton = new Button("Move Left");
        moveRightButton = new Button("Move Right");
        
        disableMoveButtons();
        
        moveUpButton.setOnAction(e -> moveUp());
        moveDownButton.setOnAction(e -> moveDown());
        moveRightButton.setOnAction(e -> moveRight());
        moveLeftButton.setOnAction(e -> moveLeft());
        
        rollNumberLabel = new Label();
        rollNumberLabel.setStyle("-fx-background-color: white;");
        rollNumberLabel.setStyle("-fx-font-size: 14px;");
        rollNumberLabel.setStyle("-fx-border-color: black;");
        rollNumberLabel.setStyle("-fx-font-weight: bold;");
        rollNumberLabel.setPrefWidth(20);

        footerBox.getChildren().addAll(rollButton, rollNumberLabel, moveUpButton, moveDownButton, moveLeftButton, moveRightButton);

        return footerBox;
    }

    private void rollButtonClicked()
    {
        Random rand = new Random();
        rollNumber = rand.nextInt(6) + 1;
        
        rollNumber = 4; // used for troubleshooting
        
        setValidMovementDirections();

        rollNumberLabel.setText(Integer.toString(rollNumber));
        
        rollButton.setDisable(true);
        
    }
    
    private void moveUp()
    {
    	System.out.println("move up");
    	int currentRow = currentPlayer.getRow();
    	int currentCol = currentPlayer.getCol();
    	int newRow = currentRow - rollNumber;
    	int newCol = currentCol;
    	

    	if(newRow <= 0)
    		{
    			
    			int remainder = Math.abs(newRow);
    			
    			System.out.println("move right remainder: " + remainder);
    			
    			rollNumber = remainder;
    			rollNumberLabel.setText(Integer.toString(remainder));
    			movePiece(currentRow, currentCol, 0, newCol);
    			setValidMovementDirections();
    			
    			if(currentCol == 0)
				{
					moveRight();
				}
				else
				{
					moveLeft();
				}
    				
    		}
    		else
    		{
    			movePiece(currentRow, currentCol, newRow, newCol);
    			if(!currentCell.getIsRollAgainCell())
    			{
    				// launch question dialog
    				QuestionDialog questionDialog = new QuestionDialog(categoryList.get(currentCell.getCellCategoryIndex()));
    				questionDialog.showAndWait();
    				
    				answerCorrect = questionDialog.answerCorrect;
    				if(!answerCorrect)
    				{
    					changePlayers();
    				}
    				else
    				{
    					if(currentCell.getIsHqCell()  && answerCorrect)
    					{
    						//Before we change, update players scoreboard
    						managePlayerScoreboard();
    					}
    					resetBoard();
    				}
    			}
    			else
    			{
    				resetBoard();
    			}
    		}
    	
    	
    		
    }
    
	private void moveDown()
	{
	        	
		System.out.println("move down");
		int currentRow = currentPlayer.getRow();
    	int currentCol = currentPlayer.getCol();
    	int newRow = currentRow + rollNumber;
    	int newCol = currentCol;
    	
    	System.out.println("new row: " + newRow);
    	
    	System.out.println("currentCell color beginning of move:" + currentCell.getCellColor());
    	

		if(newRow > 8 )
		{
			
			int remainder = newRow - 8;
			
			rollNumber = remainder;
			rollNumberLabel.setText(Integer.toString(remainder));
			movePiece(currentRow, currentCol, 8, newCol);
			setValidMovementDirections();
			
			if(currentCol == 0)
			{
				moveRight();
			}
			else
			{
				moveLeft();
			}
			
		}
		else
		{
			movePiece(currentRow, currentCol, newRow, newCol);
			
			System.out.println("currentCell color after movePiece:" + currentCell.getCellColor());
			if(!currentCell.getIsRollAgainCell())
			{
				// launch question dialog
				QuestionDialog questionDialog = new QuestionDialog(categoryList.get(currentCell.getCellCategoryIndex()));
				questionDialog.showAndWait();
				
				answerCorrect = questionDialog.answerCorrect;
				if(!answerCorrect)
				{
					changePlayers();
				}
				else
				{
					if(currentCell.getIsHqCell()  && answerCorrect)
					{
						//Before we change, update players scoreboard
						managePlayerScoreboard();
					}
					resetBoard();
				}
			
			}
			else
			{
				resetBoard();
			}
		}	
		
		
        
    }
	
	private void moveRight()
	{
		System.out.println("move right");
		
		int currentRow = currentPlayer.getRow();
    	int currentCol = currentPlayer.getCol();
    	int newRow = currentRow;
    	int newCol = currentCol + rollNumber;

		if(newCol > 8)
		{

			int remainder = newCol - 8;
			
			
			rollNumber = remainder;
			rollNumberLabel.setText(Integer.toString(remainder));
			movePiece(currentRow, currentCol, newRow, 8);
			
			if(currentRow == 0)
			{
				moveDown();
			}
			else
			{
				moveUp();
			}
			
			setValidMovementDirections();
		}
		else
		{
			movePiece(currentRow, currentCol, currentRow, newCol);
			if(!currentCell.getIsRollAgainCell())
			{
				// launch question dialog
				QuestionDialog questionDialog = new QuestionDialog(categoryList.get(currentCell.getCellCategoryIndex()));
				questionDialog.showAndWait();
				
				answerCorrect = questionDialog.answerCorrect;
				if(!answerCorrect)
				{
					changePlayers();
				}
				else
				{
					if(currentCell.getIsHqCell()  && answerCorrect)
					{
						//Before we change, update players scoreboard
						managePlayerScoreboard();
					}
					resetBoard();
				}
			
			}
			else
			{
				resetBoard();
			}
    		
        }
		
		
    }
	
	private void moveLeft()
	{
    	System.out.println("move left");

    	int currentRow = currentPlayer.getRow();
    	int currentCol = currentPlayer.getCol();
    	int newRow = currentRow;
    	int newCol = currentCol - rollNumber;
    	
    	System.out.println("move left newCol: " + newCol);
    	

		if(newCol < 0)
		{

			int remainder = Math.abs(newCol);
			
			rollNumber = remainder;
			rollNumberLabel.setText(Integer.toString(remainder));
			movePiece(currentRow, currentCol, newRow, 0);
			setValidMovementDirections();
			
			if(currentRow == 0)
			{
				moveDown();
			}
			else
			{
				moveUp();
			}
			
		}
		else
		{
			movePiece(currentRow, currentCol, currentRow, newCol);
			if(!currentCell.getIsRollAgainCell())
			{
				// launch question dialog
				QuestionDialog questionDialog = new QuestionDialog(categoryList.get(currentCell.getCellCategoryIndex()));
				questionDialog.showAndWait();
				
				answerCorrect = questionDialog.answerCorrect;
				if(!answerCorrect)
				{
					changePlayers();
				}
				else
				{
					if(currentCell.getIsHqCell()  && answerCorrect)
					{
						//Before we change, update players scoreboard
						managePlayerScoreboard();
					}
					resetBoard();
				}
			
			}
			else
			{
				resetBoard();
			}
			
		}
		
		
        
    }
	
	private void movePiece(int currentRow, int currentCol, int newRow, int newCol)
	{
		System.out.println("currentCell color in movePiece: " + currentCell.getCellColor());
		System.out.println("moving piece from cell: " + "row: " + currentRow + " col: " + currentCol);
		//currentCell = board.cells[currentRow][currentCol];
        currentCell.removePlayer(currentPlayer);

        System.out.println("moving to cell: " + "row: " + newRow + " col: " + newCol);
        currentCell = board.getCells()[newRow][newCol];
        currentCell.addPlayer(currentPlayer);
        
        currentPlayer.setCol(newCol);
        currentPlayer.setRow(newRow);
        
        currentCell.setCol(newCol);
        currentCell.setRow(newRow);
        
        currentCell.setCellStyle();
        
        System.out.println("currentCell color at end of movePiece: " + currentCell.getCellColor());
	}
	
	private void disableMoveButtons()
	{
		moveUpButton.setDisable(true);
  		moveDownButton.setDisable(true);
  		moveLeftButton.setDisable(true);
  		moveRightButton.setDisable(true);
	}
	
	private void resetBoard()
	{
		disableMoveButtons();
		rollButton.setDisable(false);
		rollNumberLabel.setText("");
	}
	
	
	private void setValidMovementDirections()
	{
		int row = currentPlayer.getRow();
		int col = currentPlayer.getCol();
		
		// Current Player on HQ Spike and has all wedges, move towards center
		
		if(currentPlayer.getHasAllHqTokens())
		{
			if(col == 4 && row == 4)
			{
				disableMoveButtons();
			}
			else if(row == 4)
            {
				if(col > 0 && col < 8)
				{
					moveUpButton.setDisable(true);
			    	moveDownButton.setDisable(true);
			    	moveLeftButton.setDisable(false);
			    	moveRightButton.setDisable(false);
				}
				else if(col == 0)
				{
					moveUpButton.setDisable(true);
			    	moveDownButton.setDisable(true);
			    	moveLeftButton.setDisable(true);
			    	moveRightButton.setDisable(false);
				}
				else if(col == 8)
				{
					moveUpButton.setDisable(true);
			    	moveDownButton.setDisable(true);
			    	moveLeftButton.setDisable(false);
			    	moveRightButton.setDisable(true);
				}
				
            } 
            else if(col == 4)
            {
            	if(row > 0 && row < 8)
				{
					moveUpButton.setDisable(false);
			    	moveDownButton.setDisable(false);
			    	moveLeftButton.setDisable(true);
			    	moveRightButton.setDisable(true);
				}
				else if(row == 0)
				{
					moveUpButton.setDisable(true);
			    	moveDownButton.setDisable(false);
			    	moveLeftButton.setDisable(true);
			    	moveRightButton.setDisable(true);
				}
				else if(row == 8)
				{
					moveUpButton.setDisable(false);
			    	moveDownButton.setDisable(true);
			    	moveLeftButton.setDisable(true);
			    	moveRightButton.setDisable(true);
				}
            	
            }
            
		}
		else
		{
			
			if(col == 0)
			{
				moveLeftButton.setDisable(true);
				
				if(row == 0)
				{
					moveRightButton.setDisable(false);
					moveDownButton.setDisable(false);
					moveUpButton.setDisable(true);
				}
				else if(row == 8)
				{
					moveRightButton.setDisable(false);
					moveDownButton.setDisable(true);
					moveUpButton.setDisable(false);	
				}
				else
				{
					moveRightButton.setDisable(true);
					moveDownButton.setDisable(false);
					moveUpButton.setDisable(false);	
				}
			}
			else if(col == 8)
			{
				moveRightButton.setDisable(true);
				
				if(row == 0)
				{
					moveLeftButton.setDisable(false);
					moveDownButton.setDisable(false);
					moveUpButton.setDisable(true);
				}
				else if(row == 8)
				{
					moveLeftButton.setDisable(false);
					moveDownButton.setDisable(true);
					moveUpButton.setDisable(false);	
				}
				else
				{
					moveLeftButton.setDisable(true);
					moveDownButton.setDisable(false);
					moveUpButton.setDisable(false);	
				}
				
			}
			else if(row == 0)
			{
				moveUpButton.setDisable(true);
				
				if(col == 0)
				{
					moveLeftButton.setDisable(true);
					moveDownButton.setDisable(false);
					moveRightButton.setDisable(false);
				}
				else if(col == 8)
				{
					moveLeftButton.setDisable(false);
					moveDownButton.setDisable(false);
					moveRightButton.setDisable(true);	
				}
				else
				{
					moveLeftButton.setDisable(false);
					moveDownButton.setDisable(true);
					moveRightButton.setDisable(false);	
				}
			}
			else if(row == 8)
			{
				moveDownButton.setDisable(true);
				
				if(col == 0)
				{
					moveLeftButton.setDisable(true);
					moveRightButton.setDisable(false);
					moveUpButton.setDisable(false);
				}
				else if(col == 8)
				{
					moveLeftButton.setDisable(false);
					moveRightButton.setDisable(true);
					moveUpButton.setDisable(true);	
				}
				else
				{
					moveLeftButton.setDisable(false);
					moveRightButton.setDisable(false);
					moveUpButton.setDisable(true);	
				}
			}
			
		}
		
	}
	
	private void changePlayers()
	{

		currentPlayerIndex = (currentPlayerIndex + 1) % allPlayers.size();
		currentPlayer = allPlayers.get(currentPlayerIndex);
		currentPlayerLabel.setText("Player's Turn: " + currentPlayer.getName() + "-" + currentPlayer.colorStr);
		disableMoveButtons();
		rollNumberLabel.setText("");
		rollButton.setDisable(false);
		
		currentCell = board.getCells()[currentPlayer.getRow()][currentPlayer.getCol()];
		
		currentCell.setCellStyle();
		
		System.out.println("new cell in change players, row:" + currentPlayer.getRow() + " col: " + currentPlayer.getCol() + " color: " + currentPlayer.getColor());
	}
	
	public void managePlayerScoreboard()
	{
		System.out.println("manage scoreboard");
		int boardRow = currentPlayer.getRow();
		int boardCol = currentPlayer.getCol();
		
		Color playerColor = currentPlayer.getColor();
		Color hqTokenColor = currentCell.getCellColor();		
		if(playerColor == Color.RED)
		{
			currentCell = board.getCells()[2][2];
		}
		else if(playerColor == Color.BLUE)
		{
			currentCell = board.getCells()[6][6];
		}
		else if(playerColor == Color.GREEN)
		{
			currentCell = board.getCells()[6][2];
		}
		else if(playerColor == Color.YELLOW)
		{
			currentCell = board.getCells()[2][6];
		}
		
		currentCell.updatePlayerHqTokens(currentPlayer, hqTokenColor);
		
		//move cell back to where we were before moving to the scoreboard cell
		currentCell = board.getCells()[boardRow][boardCol];
		
		
	}

    private void addPlayer(String name, Color color)
    {
        Player player = new Player(name, color);
        System.out.println("adding player to global list");
        allPlayers.add(player);
        board.addPlayer(player);
        currentPlayer = player;
    }
    
    public int getCurrentPlayerIndex()
    {
    	return currentPlayerIndex;
    }
    
    public void setCategories()
    {
    	categoryList.clear();
    	categoryList.add(redCategory);
    	categoryList.add(blueCategory);
    	categoryList.add(greenCategory);
    	categoryList.add(yellowCategory);
    	
    }
    
    private Color getPlayerColorFromInt(int colorInt)
    {
    	Color playerColor = Color.RED;
    	
    	switch(colorInt)
    	{
	    	case 0:
	    		playerColor = Color.RED;
	    		break;
	    	case 1:
	    		playerColor = Color.BLUE;
	    		break;
	    	case 2:
	    		playerColor = Color.GREEN;
	    		break;
	    	case 3:
	    		playerColor = Color.YELLOW;
	    		break;
		
    	}
    	
    	return playerColor;
    }

}
