package com.codecool.dungeoncrawl.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartPopUp {
    private static String title = "Start!";
    private static String playerName;

    public static void display(){

        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });

        /**
         * it blocks other windows and get focus to this window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);

        TextField nameField = new TextField();
        nameField.setPadding(new Insets(15,15,15,15));

        Label label = new Label();
        label.setPadding(new Insets(25,25,25,25));
        label.setText("What is your name?");
        label.setStyle("-fx-font-weight: bold;");


        Button startButton = new Button(title);
        startButton.setOnAction(e -> {
            playerName = nameField.getText();
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameField, startButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(25,25,25,25));
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121,77,96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public static String getPlayerName() {
        return playerName;
    }
}
