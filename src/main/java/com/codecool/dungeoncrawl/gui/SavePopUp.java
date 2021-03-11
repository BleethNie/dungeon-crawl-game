package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.gui.guiControllers.ButtonExport;
import com.codecool.dungeoncrawl.logic.utils.LoadManager;
import com.codecool.dungeoncrawl.logic.utils.WrongNameException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SavePopUp {
    private static String title = "Save Game";
    private static String saveName;

    public static void display(LoadManager manager){

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

        Label label = new Label();
        label.setPadding(new Insets(25,25,25,25));
        label.setText("Name:");
        label.setStyle("-fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPadding(new Insets(15,15,15,15));


        Button saveButton = new Button(title);
        saveButton.setOnAction(e -> {
            saveName = nameField.getText();
            manager.setGameSaveName(saveName);
            try {
                manager.chooseSaveOption();
                window.close();
            } catch (WrongNameException wne){
                label.setText("This name is not availabe. Choose another one: ");
            }
        });


        Button exportButton = new ButtonExport(window, manager);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());

        cancelButton.setMinWidth(100);
        saveButton.setMinWidth(100);

        FlowPane buttonNode = new FlowPane(10, 10);
        buttonNode.setAlignment(Pos.CENTER);


        VBox layout = new VBox(10);
        buttonNode.getChildren().addAll(saveButton, exportButton, cancelButton);
        layout.getChildren().addAll(label, nameField, buttonNode);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(25,25,25,25));
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121,77,96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public static String getPlayerName() {
        return saveName;
    }

}

