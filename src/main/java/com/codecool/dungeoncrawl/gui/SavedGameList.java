package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.utils.LoadManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SavedGameList {


    public static void display(LoadManager manager) {


        System.out.println();
        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });

        window.initModality(Modality.APPLICATION_MODAL);
        String title = "Load Game";
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);

        /**
         * do zmienienia gdy się będą zaczytywały savy z db
         */

        Label label = new Label();
        label.setPadding(new Insets(25, 25, 25, 25));
        label.setText("Saved game name: ");
        label.setStyle("-fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPadding(new Insets(15, 15, 15, 15));


        Button loadGameButton = new Button("Load Game");
        loadGameButton.setPrefSize(200, 50);
        loadGameButton.setOnAction(e -> {
            String savedName = nameField.getText();
            manager.setGameSavedName(savedName);
            manager.chooseLoadOption();
            window.close();
        });


        /**
         * koniec :  do zmienienia gdy się będą zaczytywały savy z db
         */


        VBox layout = new VBox(50);
        layout.getChildren().addAll(label, nameField, loadGameButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121, 77, 96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}

