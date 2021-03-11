package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.gui.guiControllers.ButtonImport;
import com.codecool.dungeoncrawl.logic.utils.LoadManager;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewGameLoadGamePopup {

    public static void display(LoadManager manager){

        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });
        FileChooser fileChooser = new FileChooser();

        window.initModality(Modality.APPLICATION_MODAL);
        String title = "Hello in Dungeon Crawl!";
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(300);

        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(200, 50);
        newGameButton.setOnAction(e -> {
            manager.chooseLoadOption();
            window.close();
        });

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setPrefSize(200, 50);
        loadGameButton.setOnAction(e -> {
            SavedGameList.display(manager);
            window.close();
        });

        Button importButton = new ButtonImport(fileChooser, window, manager);

        VBox layout = new VBox(50);
        layout.getChildren().addAll(newGameButton, loadGameButton, importButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121,77,96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}

