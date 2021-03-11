package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.logic.utils.LoadManager;
import com.codecool.dungeoncrawl.logic.utils.WrongNameException;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonExport extends Button {

    public ButtonExport(Stage primaryStage, LoadManager manager){
        super("Export");
        this.setTooltip(new Tooltip("Choose where to save game"));
        this.setMinWidth(100);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Export pressed");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("dungeon_crawl.json");
            File savingFile = fileChooser.showSaveDialog(primaryStage);
            manager.setSavingFile(savingFile);
            try {
                manager.chooseSaveOption();
            } catch (WrongNameException wrongNameException) {
                wrongNameException.printStackTrace();
            }
            primaryStage.close();

        });
    }
}
