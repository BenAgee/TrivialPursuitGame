package com.example.TrivialPursuitGame;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;

import javafx.scene.control.Dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionDialog extends Dialog{
	
	String questionCategory;
	Boolean answerCorrect = false;
	String questionStr = "";
	String answerStr = "";
	Label answerResultLabel;
	
	Button correctButton;
	Button incorrectButton;
	Button displayAnswerButton;
	
	public QuestionDialog(String category)
	{
		super(); // invokes constructor of the Dialog class
		questionCategory = category;
		answerResultLabel = new Label("initial answer");
		
		this.setTitle("Question Dialog");
		buildUI();
		
		
        
    	// get question from db in table corresponding to category
    	// get random question from table (check against already asked questions to not repeat.
    	// track index of questions already asked to avoid repeats
        
        questionStr = "sample question?";
        answerStr = "sample answer";
        
        //questionLabel.setText(questionStr);
        
	}
	
	private void buildUI()
	{
		// Create the question label
        Label questionLabel = new Label("Is JavaFX a framework for building user interfaces?");
        
        
        Label answerLabel = new Label("Answer: ");
        
        HBox answerBox = new HBox(10);
        answerBox.setPadding(new Insets(10));
        answerBox.getChildren().addAll(answerLabel, answerResultLabel);
        
        
        
        // Create the buttons
        correctButton = new Button("Correct");
        correctButton.setDisable(true);
        incorrectButton = new Button("Incorrect");
        incorrectButton.setDisable(true);
        displayAnswerButton = new Button("Display Answer");

        // Set button actions
        correctButton.setOnAction(e -> answerCorrectClicked());
        incorrectButton.setOnAction(e -> answerIncorrectClicked());
        displayAnswerButton.setOnAction(e -> showQuestionAnswer());

        // Create a layout for the popup dialog
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(displayAnswerButton, correctButton, incorrectButton);
        
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(questionLabel, answerBox, buttonBox);
        
        getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());

        
        getDialogPane().setContent(mainBox);
        
	}
	
	
	private void showQuestionAnswer() {
		answerResultLabel.setText(answerStr);
		correctButton.setDisable(false);
		incorrectButton.setDisable(false);
	}
	
	private void answerCorrectClicked()
	{
		answerCorrect = true;
		displayAnswerButton.setDisable(true);
		Stage stage = (Stage) correctButton.getScene().getWindow();
		stage.close();

	}
	
	private void answerIncorrectClicked()
	{
		answerCorrect = false;
		displayAnswerButton.setDisable(true);
		Stage stage = (Stage) incorrectButton.getScene().getWindow();
		stage.close();

	}

}
