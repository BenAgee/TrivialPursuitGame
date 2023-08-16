package com.example.TrivialPursuitGame;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import javafx.scene.control.Dialog;

public class HelpMenu extends Dialog{
	
	Button exitHelpButton;
	
	public HelpMenu()
	{
		super(); // invokes constructor of the Dialog class

		
		this.setTitle("Help Menu");
		
        buildUI();
         
	}
	
	private void buildUI()
	{
        
        // Create a TextArea to hold the help text
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        String helpTextString = "Trivia Compute Help Menu\n\n"
        		+ "Building a Question Set:\n\n"
        		+ "The first time you play the game, you will have to create your questions set "
        		+ "Start the game and click on the \"Manage Question Set\" button. A new window will open"
        		+ "that will allow you to add categories and questions to the data set. "
        		+ "You will need to add at least four categories, and at least one question per"
        		+ "category to the data set in order to play the game. Once you have created your data set,"
        		+ "you can exit the menu to return to the \"Start Game\" window. You can use the \"Manage Question Set\""
        		+ "window between games to add new questions or categories, and delete and questions or "
        		+ "categories you no longer want to include.\n\n"
        		+ "Starting The Game:\n\n"
        		+ "To begin the game, select a category to associate with each color. Next, add 1 - 4 players to the game. "
        		+ "Now you may start the game.\n\n"
        		+ "Gameplay:\n\n"
        		+ "When the game starts, a random player will be selected to go first. They can roll the dice, "
        		+ "and the valid movement buttons will be enabled for you to choose which dirction you want to move. "
        		+ "After moving, a question will be shown corresponding to the category you land on. You will answer "
        		+ "the question, display the answer, then select if the answer was correct or not. If you are correct,"
        		+ "you will get to continue your turn, if not the game will move on to the next player. Gameplay "
        		+ "continues until a player has collected all headquarters tokens and answered a final question"
        		+ "correctly in the center square.\n\n"
        		+ "Headquarters Squares:\n\n"
        		+ "The four special headquarters squares are the way players can score points. If a player answers "
        		+ "a question correctly on one of these squares, their scoreboard will show a collected headquarters "
        		+ "token corresponding to that category. When a player collects a token for all four categories, they "
        		+ "can then move towared the center of the board, where they can win the game by answering a question,"
        		+ "from the categeory of their choice, in the center square.";
        textArea.setText(helpTextString);

        // Create a ScrollPane and add the TextArea to it
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create a layout to hold the ScrollPane
        StackPane root = new StackPane();
        root.getChildren().add(scrollPane);
        
        getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());

        getDialogPane().setContent(root);
        
	}

}
