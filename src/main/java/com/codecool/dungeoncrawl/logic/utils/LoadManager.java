package com.codecool.dungeoncrawl.logic.utils;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.gui.SavePopUp;
import com.codecool.dungeoncrawl.gui.StartPopUp;
import com.codecool.dungeoncrawl.logic.GameWorld;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadManager {

    private File file;
    private File savingFile;
    private String gameSavedName;
    private String gameSaveName;

    private GameJsonManager jsonManager;
    private GameDatabaseManager dbManager;


    private GameWorld gameWorld;

    public LoadManager(GameDatabaseManager dbManager, GameJsonManager jsonManager,
                        GameWorld gameWorld){
        this.file = null;
        this.savingFile = null;
        this.gameSavedName = null;
        this.gameSaveName = null;
        this.jsonManager = jsonManager;
        this.dbManager = dbManager;
        this.gameWorld = gameWorld;

    }


    public void chooseLoadOption(){
        if (file != null){
            jsonManager.importProject(file);
            gameWorld.importWorld(GameWorldFactory.importGame(jsonManager.getGameState(),
                    jsonManager.getItemModels(), jsonManager.getMonsterModels(),
                    jsonManager.getPlayerModel()));
            this.file = null;
        } else if (this.gameSavedName != null){
            int gameStateId = dbManager.getIdForSaveName(gameSavedName);
            GameState gameState = dbManager.getGameState(gameStateId);
            PlayerModel playerModel = dbManager.getPlayerModel(gameStateId);
            List<ItemModel> itemModels = dbManager.getAllItemModels(gameStateId);
            List<MonsterModel> monsterModels = dbManager.getAllMonstersModels(gameStateId);

            gameWorld.importWorld(GameWorldFactory.importGame(gameState, itemModels, monsterModels, playerModel ));

            gameSavedName = null;
        } else {
            StartPopUp.display();
            gameWorld.getCurrentMap().getPlayer().setName(StartPopUp.getPlayerName());

        }
    }

    public void chooseSaveOption() throws WrongNameException {
        if (savingFile != null){
            jsonManager.update(String.format("map%s", gameWorld.getCurrentMap().getMapNumber()), SavePopUp.getPlayerName(),
                    gameWorld.getCurrentMap().getPlayer(), gameWorld.getItemList(), gameWorld.getMonsterList());
            jsonManager.saveToProjectFile(savingFile);
            savingFile = null;
        } else if (gameSaveName != null){
            if (nameIsAvailable(gameSaveName)){
                dbManager.saveGameState(String.format("map%s", gameWorld.getCurrentMap().getMapNumber()), SavePopUp.getPlayerName(), gameWorld.getCurrentMap().getPlayer());
                dbManager.savePlayer(gameWorld.getCurrentMap().getPlayer());
                dbManager.saveItems(gameWorld.getItemList(), gameWorld.getCurrentMap().getPlayer().getId());
                dbManager.saveMonsters(gameWorld.getMonsterList(), gameWorld.getCurrentMap().getPlayer().getId());
                gameSaveName = null;
            } else {
                throw new WrongNameException("wrong name");
            }
        }
    }

    private boolean nameIsAvailable(String gameSaveName) {
        List<String> savedNames = dbManager.getAllSavedNames();
        return  !(savedNames.contains(gameSaveName));
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setGameSaveName(String gameSaveName) {
        this.gameSaveName = gameSaveName;
    }

    public void setSavingFile(File savingFile) {
        this.savingFile = savingFile;
    }

    public void setGameSavedName(String gameSavedName) {
        this.gameSavedName = gameSavedName;
    }
}

