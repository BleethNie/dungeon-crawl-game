package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.logic.utils.LoadManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonImport extends Button {

    public ButtonImport(FileChooser fileChooser, Stage primaryStage, LoadManager manager){
        super("Import");
        this.setTooltip(new Tooltip("Choose the file to import"));
        this.setPrefSize(200, 50);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {


            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Import pressed");
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            manager.setFile(selectedFile);
            manager.chooseLoadOption();

            primaryStage.close();
            });
    }

}
