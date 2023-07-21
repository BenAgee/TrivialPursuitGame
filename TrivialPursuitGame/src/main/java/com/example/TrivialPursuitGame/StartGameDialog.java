package com.example.TrivialPursuitGame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import javafx.scene.control.Dialog;

import java.util.ArrayList;
import java.util.LinkedList;


public class StartGameDialog extends Dialog{


	Button startGameButton;
	Button addQuestionButton;
	Button addPlayerButton;
	
	DBConnection db;
	
	Boolean validGameParameters = false;
	
	LinkedList<String> playerNameList;
	
	String redCategoryName;
	String blueCategoryName;
	String greenCategoryName;
	String yellowCategoryName;
	
	ComboBox<String> redInput;
    ComboBox<String> blueInput;
    ComboBox<String> greenInput;
    ComboBox<String> yellowInput;
    
	TextField playerInput;
	
	
	public StartGameDialog()
	{
		super(); // invokes constructor of the Dialog class
		playerNameList = new LinkedList<>();
		
		db = new DBConnection();
		
		this.setTitle("Trivial Compute Start Page");
		buildUI();
		
        
	}
	
	private void buildUI()
	{
		// Create the question label
        Label headerLabel = new Label("Select game categories and add players");
        
        
        Label redCategoryLabel = new Label("Red Category: ");
        Label blueCategoryLabel = new Label("Blue Category: ");
        Label greenCategoryLabel = new Label("Green Category: ");
        Label yellowCategoryLabel = new Label("Yellow Category: ");
        
        // get possible categories to drop into combobox
        // get tables from db will do the trick
        // convert query to array list below to add to combo boxes
        
        
        ArrayList<String> availableCategories = db.getAllCategories();
        
        ObservableList<String> availableCategoriesList = FXCollections.observableArrayList(
                availableCategories
            );
        
        redInput = new ComboBox<String>(availableCategoriesList);
        blueInput = new ComboBox<String>(availableCategoriesList);
        greenInput = new ComboBox<String>(availableCategoriesList);
        yellowInput = new ComboBox<String>(availableCategoriesList);
        
        HBox redBox = new HBox(10);
        redBox.setPadding(new Insets(10));
        redBox.getChildren().addAll(redCategoryLabel, redInput);
        
        HBox blueBox = new HBox(10);
        blueBox.setPadding(new Insets(10));
        blueBox.getChildren().addAll(blueCategoryLabel, blueInput);
        
        HBox greenBox = new HBox(10);
        greenBox.setPadding(new Insets(10));
        greenBox.getChildren().addAll(greenCategoryLabel, greenInput);
        
        HBox yellowBox = new HBox(10);
        yellowBox.setPadding(new Insets(10));
        yellowBox.getChildren().addAll(yellowCategoryLabel, yellowInput);
        
        
        
        // Create the buttons
        addPlayerButton = new Button("Add Player");
        playerInput = new TextField();
        
        HBox addPlayerBox = new HBox();
        addPlayerBox.setPadding(new Insets(10));
        addPlayerBox.getChildren().addAll(addPlayerButton, playerInput);
        
        startGameButton = new Button("Start Game");
        startGameButton.setDisable(true);

		addQuestionButton = new Button("Add Questions");

		HBox buttonBox = new HBox();
		buttonBox.setPadding(new Insets(10));
		buttonBox.getChildren().addAll(startGameButton, addQuestionButton);

        // Set button actions
        addPlayerButton.setOnAction(e -> addPlayerButtonClicked());
        startGameButton.setOnAction(e -> startGameButtonClicked());
		addQuestionButton.setOnAction(e -> addQuestionClicked());

        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(headerLabel, redBox, blueBox, greenBox, yellowBox, addPlayerBox, buttonBox);
        
        getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());
        
        getDialogPane().setContent(mainBox);
        
	}

	private void addQuestionClicked()
	{
		NewQuestions nq = new NewQuestions();
		nq.showAndWait();
		
		updateCategories();
		
	}
	
	private void startGameButtonClicked()
	{
		// add error handling to ensure categories are selected and at least 1 player has been added.
		
		if(!playerNameList.isEmpty() && !redInput.getValue().isBlank() && !blueInput.getValue().isBlank() && !greenInput.getValue().isBlank() && !yellowInput.getValue().isBlank())
		{
			redCategoryName = redInput.getValue();
			blueCategoryName = blueInput.getValue();
			greenCategoryName = greenInput.getValue();
			yellowCategoryName = yellowInput.getValue();
			
			validGameParameters = true;
			
			Stage stage = (Stage) startGameButton.getScene().getWindow();
			stage.close();
		}
		else
		{
			System.out.println("failed input verification test");
		}
		
		

	}
	
	private void addPlayerButtonClicked()
	{
		String playerName = playerInput.getText();
		
		
		if(!playerName.isBlank() && !playerNameList.contains(playerName))
		{
			playerNameList.add(playerName);
			
			sendInfoAlert("Player Added: " + playerName);	
			
			playerInput.setText("");
			
			startGameButton.setDisable(false);
		}
		else
		{
			sendErrorAlert("You must enter an unused and non-blank player name.");
		}
		
		
		
	}
	
	private void sendInfoAlert(String alertMessage)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(alertMessage);
		alert.show();
	}
	
	private void sendErrorAlert(String alertMessage)
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Information Alert");
		alert.setContentText(alertMessage);
		alert.show();
	}
	
	private void updateCategories()
	{
		ArrayList<String> availableCategories = db.getAllCategories();
        
        ObservableList<String> availableCategoriesList = FXCollections.observableArrayList(
                availableCategories
            );
        
        redInput.setItems(availableCategoriesList);
        blueInput.setItems(availableCategoriesList);
        greenInput.setItems(availableCategoriesList);
        yellowInput.setItems(availableCategoriesList);
	}
	

}
