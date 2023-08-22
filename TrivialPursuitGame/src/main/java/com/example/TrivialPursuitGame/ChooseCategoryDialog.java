package com.example.TrivialPursuitGame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class ChooseCategoryDialog{
	
	String questionCategory;
	
	Button selectCategoryButton;
	int chosenCategoryId;
	ComboBox<String> chooseCategoryBox;
	
	DBConnection db;
	
	public ChooseCategoryDialog()
	{
		super(); // invokes constructor of the Dialog class

		
		db = new DBConnection();
        
        buildUI();
         
	}
	
	private void buildUI()
	{
		Stage chooseCategoryStage = new Stage();
		chooseCategoryStage.setTitle("Choose Category");

        // Create content for the second window
        StackPane chooseCategoryLayout = new StackPane();
		
		// Create the question label
        Label categoryLabel = new Label("Select Category");
        
        // Create the buttons
        selectCategoryButton = new Button("Select Category");

        ArrayList<String> availableCategories = db.getAllCategories();
        
        ObservableList<String> availableCategoriesList = FXCollections.observableArrayList(
                availableCategories
            );
        
        chooseCategoryBox = new ComboBox<String>(availableCategoriesList);
        
        // Set button actions
        selectCategoryButton.setOnAction(e -> selectCategoryClicked());
        
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(categoryLabel, chooseCategoryBox, selectCategoryButton);
        
        chooseCategoryLayout.getChildren().addAll(mainBox);
        
        chooseCategoryStage.setScene(new Scene(chooseCategoryLayout, 200, 200));
        
        chooseCategoryStage.showAndWait();
        
        //getDialogPane().getScene().getWindow().setOnCloseRequest(event -> this.close());

        //getDialogPane().setContent(mainBox);
        
	}
	
	private void selectCategoryClicked()
	{
		String chosenCategory = chooseCategoryBox.getValue();
		
		chosenCategoryId = db.getCategoryIdFromName(chosenCategory);
		
		Stage stage = (Stage) selectCategoryButton.getScene().getWindow();
		stage.close();

	}
	


}
