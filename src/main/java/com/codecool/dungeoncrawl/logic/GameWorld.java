package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<GameMap> levels;
    private GameMap currentMap;
    private List<Actor> monsterList;
    private List<Item> itemList;

    public GameWorld(List<GameMap> levels, GameMap currentMap, List<Actor> monsterList, List<Item> itemList) {
        this.levels = levels;
        this.currentMap = currentMap;
        this.monsterList = monsterList;
        this.itemList = itemList;
    }

    public void changeLevel() {
        if (currentMap.getPlayer().getCell().getBuilding() != null) {
            if (currentMap.getPlayer().getCell().getBuilding().getTileName().equals("ladder up")) {
                Player samePlayer = currentMap.getPlayer();
                currentMap = levels.get(levels.indexOf(currentMap) + 1);
                currentMap.setPlayer(samePlayer);
                currentMap.getCell(currentMap.getGoDownX(), currentMap.getGoDownY()).setActor(samePlayer);
                samePlayer.setCellForActor(currentMap.getCell(currentMap.getGoDownX(), currentMap.getGoDownY()));
                CurrentStatus.getInstance().setStatus("Level " + (levels.indexOf(currentMap) + 1));
            } else if (currentMap.getPlayer().getCell().getBuilding().getTileName().equals("ladder down")) {
                Player samePlayer = currentMap.getPlayer();
                currentMap = levels.get(levels.indexOf(currentMap) - 1);
                currentMap.setPlayer(samePlayer);
                currentMap.getCell(currentMap.getGoUpX(), currentMap.getGoUpY()).setActor(samePlayer);
                samePlayer.setCellForActor(currentMap.getCell(currentMap.getGoUpX(), currentMap.getGoUpY()));
                CurrentStatus.getInstance().setStatus("Level " + (levels.indexOf(currentMap) + 1));
            }
        }
    }

    public void moveMonsters() {
        for (Actor monster : monsterList) {
            if (monster.getHealth() > 0 && !monster.getTileName().equals("skeleton")) {
                try {
                    monster.move(RandomProvider.getRandomNumberOfRange(-1, 2), RandomProvider.getRandomNumberOfRange(-1, 2));
                } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {
            }
        }
    }
}

    public List<Actor> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<Actor> monsterList) {
        this.monsterList = monsterList;
    }

    public List<GameMap> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<GameMap> levels) {
        this.levels = levels;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void importWorld(GameWorld importedGame) {
        this.levels = importedGame.levels;
        this.currentMap = importedGame.currentMap;
        this.itemList = importedGame.itemList;
        this.monsterList = importedGame.monsterList;
    }
}
