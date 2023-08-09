package com.example.TrivialPursuitGame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Map;

import javafx.scene.control.Dialog;

public class QuestionDialog extends Dialog{
	
	String questionCategory;
	Boolean answerCorrect = false;
	String questionStr;
	String answerStr = "";
	Label answerResultLabel;
	
	Button correctButton;
	Button incorrectButton;
	Button displayAnswerButton;
	
	DBConnection db;
	
	public QuestionDialog(int catId)
	{
		super(); // invokes constructor of the Dialog class
		int category_id = catId;
		answerResultLabel = new Label("");
		
		db = new DBConnection();
		
		this.setTitle("Question Dialog");
		
		Map<String, String> QApair = db.getQuestion(category_id);
        
    	// get question from db in table corresponding to category
    	// get random question from table
    	// TODO track index of questions already asked to avoid repeats
        
        questionStr = QApair.keySet().toArray()[0].toString();
        answerStr = QApair.values().toArray()[0].toString();
        
        buildUI();
         
	}
	
	private void buildUI()
	{
		// Create the question label
        Label questionLabel = new Label(questionStr);
        
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
