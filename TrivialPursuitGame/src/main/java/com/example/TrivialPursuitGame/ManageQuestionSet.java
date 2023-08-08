package com.example.TrivialPursuitGame;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Dialog;

public class ManageQuestionSet extends Dialog{
    Button add_question_button;
    Button add_category_button;
    Button return_to_menu;
    
    Button delete_question_button;
    Button delete_category_button;
    Button delete_all_questions_button;
    Button delete_all_categories_button;
    
    ComboBox<String> available_categories_box;
    ComboBox<String> delete_category_box;
    ComboBox<String> delete_question_box;
    ComboBox<String> delete_question_by_category_box;

    TextField question;
    TextField answer;
    TextField category;
    
    DBConnection db;

    public ManageQuestionSet() {
        super();
        db = new DBConnection();
        //nothing really to setup here
        
        System.out.println("db connected:" + db.toString());
        this.setTitle("Trivial Compute Question Page");
        buildUI();
        
    }

    private void buildUI() {
        Label prompt_category = new Label("Add a Category:");
        Label prompt_question = new Label("Add a Question:");
        Label prompt_delete_category = new Label("Delete Categories:");
        Label prompt_delete_question = new Label("Delete Questions:");

        add_category_button = new Button("Add Category");
        add_question_button = new Button("Add Question");
        delete_category_button = new Button("Delete Category");
        delete_question_button = new Button("Delete Question");
        delete_all_categories_button = new Button("Delete All Categories");
        delete_all_questions_button = new Button("Delete All Questions");
        return_to_menu = new Button("Return To Menu");
        
        available_categories_box = new ComboBox<String>();
        delete_category_box = new ComboBox<String>();
        delete_question_box = new ComboBox<String>();
        delete_question_by_category_box = new ComboBox<String>();
        
        delete_question_by_category_box.setOnAction(e -> updateQuestionsByCategory());

        add_category_button.setOnAction(e -> addCategory());
        add_question_button.setOnAction(e -> addQuestion());
        delete_category_button.setOnAction(e -> deleteCategory());
        //delete_question_button.setOnAction(e -> deleteQuestion());
        delete_question_button.setOnAction(e -> deleteQuestionByCategory());
        delete_all_categories_button.setOnAction(e -> deleteAllCategories());
        delete_all_questions_button.setOnAction(e -> deleteAllQuestions());
        return_to_menu.setOnAction(e -> returnToMenu());

        category = new TextField();
        question = new TextField();
        answer = new TextField();

        HBox addCategoryBox = new HBox();
        addCategoryBox.setPadding(new Insets(10));
        addCategoryBox.getChildren().addAll(add_category_button, category);

        HBox addQuestionBox = new HBox();
        addQuestionBox.setPadding(new Insets(10));
        addQuestionBox.getChildren().addAll(add_question_button, question, answer, available_categories_box);
        
        HBox deleteCategoryBox = new HBox();
        deleteCategoryBox.setPadding(new Insets(10));
        deleteCategoryBox.getChildren().addAll(delete_all_categories_button, delete_category_box, delete_category_button);

//        HBox deleteQuestionBox = new HBox();
//        deleteQuestionBox.setPadding(new Insets(10));
//        deleteQuestionBox.getChildren().addAll(delete_question_button, delete_question_box, delete_all_questions_button);

        HBox deleteQuestionByCategoryBox = new HBox();
        deleteQuestionByCategoryBox.setPadding(new Insets(10));
        deleteQuestionByCategoryBox.getChildren().addAll(delete_all_questions_button, delete_question_by_category_box, delete_question_box, delete_question_button);
        
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(prompt_category, addCategoryBox, prompt_question, addQuestionBox, prompt_delete_category, deleteCategoryBox, prompt_delete_question, deleteQuestionByCategoryBox, return_to_menu);
        
        updateCategoriesBox();
        
        getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());
        
        getDialogPane().setContent(mainBox);
    }

    private void addCategory() {
    	
    	
    	String cat = category.getText();
    	
    	Boolean cleanInputs = true;
    	
    	if(!validInput(cat))
    	{
    		sendErrorAlert("Empty Category Input");
    		cleanInputs = false;
    	}
    	else if (db.doesCategoryExist(cat))
    	{
    		sendErrorAlert("Duplicate Category, category already in the set");
    		cleanInputs = false;
    	}
    	
    	if(cleanInputs)
    	{
	        db.addCategory(cat);
	        category.setText("");
	        updateCategoriesBox();
    	}
    }

    private void addQuestion() {

    	String cat = available_categories_box.getValue();
    	String q = question.getText();
    	String a = answer.getText();
    	
    	Boolean cleanInputs = true;
    	
    	if(db.doesQuestionExist(q))
    	{
    		sendErrorAlert("Duplicate Question, Question already is present in the set");
    		cleanInputs = false;
    	}

    	if(!validInput(cat))
    	{
    		sendErrorAlert("Empty Category Selection");
    		cleanInputs = false;
    	}
    	if(!validInput(q))
    	{
    		sendErrorAlert("Empty Question Input");
    		cleanInputs = false;
    	}
    	if(!validInput(a))
    	{
    		sendErrorAlert("Empty Answer Input");
    		cleanInputs = false;
    	}
    	
    	if(cleanInputs)
    	{
    		db.addQuestion(q, a, cat);
            question.setText("");
            answer.setText("");
            available_categories_box.setValue(null);
            delete_question_by_category_box.setValue(null);
            
            //sendInfoAlert("Question Added");
    		
    	}
        
    }
    
    private void deleteCategory()
    {
    	// TODO add error handling to make sure a category is selected
    	
    	String cat = delete_category_box.getValue();
    	
    	if(validInput(cat))
    	{
    		db.deleteCategory(cat);
    	}
    	else
    	{
    		sendErrorAlert("Empty Category Selection");
    	}
    	
    	updateCategoriesBox();
    }
    
//    private void deleteQuestion()
//    {
//    	// TODO add error handling to make sure a question is selected
//    	db.deleteQuestion(delete_question_box.getValue());
//    	
//    	updateQuestionsBox();
//    }
    
    private void deleteQuestionByCategory()
    {
    	// TODO add error handling to make sure a question and category is selected
    	String cat = delete_question_by_category_box.getValue();
    	String q = delete_question_box.getValue();
    	db.deleteQuestionByCategory(cat, q);
    	updateQuestionsByCategory();
    }
    
    private void deleteAllCategories()
    {
    	db.deleteAllCategories();
    	delete_category_box.setItems(null);
    }
    
    private void deleteAllQuestions()
    {
    	db.deleteAllQuestions();
    	delete_question_box.setItems(null);
    }

    private void returnToMenu() {
        //go back to start game dialog
        Stage stage = (Stage) return_to_menu.getScene().getWindow();
        stage.close();
    }
    
    private void updateCategoriesBox()
    {
    	// add logic to query categories table and add results as options to the available_categories_box
    	ArrayList<String> availableCategories = db.getAllCategories();
    	for (String category : availableCategories) {
    		System.out.println(category);
    	}
 
        ObservableList<String> availableCategoriesList = FXCollections.observableArrayList(
                availableCategories
            );
        
        available_categories_box.setItems(availableCategoriesList);
        delete_category_box.setItems(availableCategoriesList);
        delete_question_by_category_box.setItems(availableCategoriesList);
    
    }
    
    private void updateQuestionsByCategory()
    {
    	String cat = delete_question_by_category_box.getValue();
    	System.out.println("selected cat value: " + cat);
    	
		int catId = db.getCategoryIdFromName(cat);
    	ArrayList<String> availableQuestions = db.getQuestionsFromCategory(catId);
    	for (String question : availableQuestions) {
    		System.out.println(question);
    	}
 
        ObservableList<String> availableQuestionsList = FXCollections.observableArrayList(
        		availableQuestions
            );
        
        delete_question_box.setItems(availableQuestionsList);
    	
    }
    
    private Boolean validInput(String input)
    {
    	Boolean inputValid = false;
    	
    	if(input != null)
    	{
    		if(!input.isBlank() && !input.isEmpty())
        	{
        		inputValid = true;
        	}
    	}
    	
    	return inputValid;
    }
    
    private void sendErrorAlert(String alertMessage)
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Error Alert");
		alert.setContentText(alertMessage);
		alert.show();
	}
    
    private void sendInfoAlert(String alertMessage)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(alertMessage);
		alert.show();
	}
    
//    private void updateQuestionsBox()
//    {
//    	// add logic to query categories table and add results as options to the available_categories_box
//    	ArrayList<String> availableQuestions = db.getAllQuestions();
//    	for (String question : availableQuestions) {
//    		System.out.println(category);
//    	}
// 
//        ObservableList<String> availableQuestionsList = FXCollections.observableArrayList(
//        		availableQuestions
//            );
//        
//        delete_question_box.setItems(availableQuestionsList);
//    
//    }
}