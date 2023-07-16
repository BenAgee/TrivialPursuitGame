package com.example.TrivialPursuitGame;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;

import javafx.scene.control.Dialog;

public class NewQuestions extends Dialog{
    Button add_question_button;
    Button add_catagory_button;
    Button return_to_menu;

    TextField question;
    TextField answer;
    TextField catagory;

    public NewQuestions() {
        super();
        //nothing really to setup here
        this.setTitle("Trivial Compute Question Page");
        buildUI();
    }

    private void buildUI() {
        Label prompt_catagory = new Label("Add a catagory!");
        Label prompt_question = new Label("Add a question!");

        add_catagory_button = new Button("Add Catagory");
        add_question_button = new Button("Add Question");

        add_catagory_button.setOnAction(e -> addCatagory());
        add_question_button.setOnAction(e -> addQuestion());
        return_to_menu.setOnAction(e -> returnToMenu());

        catagory = new TextField();
        question = new TextField();
        answer = new TextField();

        HBox addCatagoryBox = new HBox();
        addCatagoryBox.setPadding(new Insets(10));
        addCatagoryBox.getChildren().addAll(add_catagory_button, catagory);

        HBox addQuestionBox = new HBox();
        addCatagoryBox.setPadding(new Insets(10));
        addCatagoryBox.getChildren().addAll(add_question_button, question, answer, catagory);

        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(prompt_catagory, addCatagoryBox, prompt_question, addQuestionBox, return_to_menu);
    }

    private void addCatagory() {
        //add catagory logic
        System.out.println("Adding catagory");
        return;
    }

    private void addQuestion() {
        //add question logic
        System.out.println("Adding question");
        return;
    }

    private void returnToMenu() {
        //go back to start game dialog
        Stage stage = (Stage) return_to_menu.getScene().getWindow();
        stage.close();
    }
}