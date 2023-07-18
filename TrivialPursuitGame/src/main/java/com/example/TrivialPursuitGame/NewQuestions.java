package com.example.TrivialPursuitGame;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Dialog;

public class NewQuestions extends Dialog{
    Button add_question_button;
    Button add_category_button;
    Button return_to_menu;
    
    ComboBox<String> available_categories_box;

    TextField question;
    TextField answer;
    TextField category;
    
    DBConnection db;

    public NewQuestions() {
        super();
        db = new DBConnection();
        //nothing really to setup here
        this.setTitle("Trivial Compute Question Page");
        buildUI();
        
    }

    private void buildUI() {
        Label prompt_catagory = new Label("Add a category!");
        Label prompt_question = new Label("Add a question!");

        add_category_button = new Button("Add Category");
        add_question_button = new Button("Add Question");
        return_to_menu = new Button("Return To Menu");

        add_category_button.setOnAction(e -> addCatagory());
        add_question_button.setOnAction(e -> addQuestion());
        return_to_menu.setOnAction(e -> returnToMenu());

        category = new TextField();
        question = new TextField();
        answer = new TextField();
        
        available_categories_box = new ComboBox();

        HBox addCatagoryBox = new HBox();
        addCatagoryBox.setPadding(new Insets(10));
        addCatagoryBox.getChildren().addAll(add_category_button, category);

        HBox addQuestionBox = new HBox();
        addQuestionBox.setPadding(new Insets(10));
        addQuestionBox.getChildren().addAll(add_question_button, question, answer, available_categories_box);

        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(prompt_catagory, addCatagoryBox, prompt_question, addQuestionBox, return_to_menu);
        
        updateCategoriesBox();
        
        getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());
        
        getDialogPane().setContent(mainBox);
    }

    private void addCatagory() {
        //add category logic
        System.out.println("Adding catagory");
        db.addCategory(category.getText());
        updateCategoriesBox();
        return;
    }

    private void addQuestion() {
        //add question logic
        System.out.println("Adding question");
        System.out.println(available_categories_box.getValue());
        db.addQuestion(question.getText(), answer.getText(), available_categories_box.getValue());
        return;
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
    	// placeholder categories, will be replaced with values from query
//    	String availableCategories[] = {"cat 1", "cat 2", "cat 3", "cat 4"};
    	
        
        ObservableList<String> availableCategoriesList = FXCollections.observableArrayList(
                availableCategories
            );
        
        available_categories_box.setItems(availableCategoriesList);
    
    }
}