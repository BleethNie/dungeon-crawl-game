package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.gui.*;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.gui.guiControllers.ButtonPickUp;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.logic.utils.GameWorldFactory;
import com.codecool.dungeoncrawl.logic.utils.LoadManager;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Main extends Application {
    private final GameWorld gameWorld = GameWorldFactory.create();
    private GameCamera gameCamera;
    private Canvas canvas;
    GraphicsContext context;
    private GameMenu gameMenu;


    GameDatabaseManager dbManager;
    GameJsonManager jsonManager;
    private LoadManager loadManager;



    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        loadManager = createLoadManager();
        NewGameLoadGamePopup.display(loadManager);
        setupDbManager();

        GameMap map = gameWorld.getCurrentMap();

        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();

        gameCamera = new GameCamera(map, 0, 0);

        gameMenu = new GameMenu(map.getPlayer());


        CurrentStatus.getInstance().bind(gameMenu.getStatus()::setMessage);
        map.getPlayer().setOnHealthChange((Integer health) -> gameMenu.getHealthLabel().setText("" + health));
        map.getPlayer().setOnStrengthChange((Integer strength) -> gameMenu.getStrengthLabel().setText("" + strength));
        map.getPlayer().setOnArmorChange((Integer armor) -> gameMenu.getArmorLabel().setText("" + armor));


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(gameMenu);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private LoadManager createLoadManager(){

        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }

        jsonManager = new GameJsonManager();

        return  new LoadManager(dbManager, jsonManager, gameWorld);

    }


    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                react(Direction.UP);
                break;
            case DOWN:
                react(Direction.DOWN);
                break;
            case LEFT:
                react(Direction.LEFT);
                break;
            case RIGHT:
                react(Direction.RIGHT);
                break;
        }

    }

    private void react(Direction direction) {
        gameWorld.getCurrentMap().getPlayer().move(direction.getX(), direction.getY());
        gameWorld.changeLevel();
        gameWorld.moveMonsters();
        refresh();

    }

    private void refresh() {
        GameMap map = gameWorld.getCurrentMap();
        if (map.getPlayer().isDead()){
            EndPopUp.display();
        }
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameCamera.centerOnEntity(map.getPlayer());
        gameMenu.getInventoryBoxDisplayer().refresh();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, gameCamera);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, gameCamera);
                } else if (cell.getBuilding() != null) {
                    Tiles.drawTile(context, cell.getBuilding(), x, y, gameCamera);
                } else {
                    Tiles.drawTile(context, cell, x, y, gameCamera);
                }
            }
        }
        gameMenu.getPickUpButton().setButtonDisable(map.getPlayer().getCell());
    }


    private void onKeyReleased(KeyEvent keyEvent) {
//        GameMap map = gameWorld.getCurrentMap();
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        } else if (saveCombination.match(keyEvent)) {
            SavePopUp.display(loadManager);
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
