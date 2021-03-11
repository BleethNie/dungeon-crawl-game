package com.codecool.dungeoncrawl.logic.utils;

import com.codecool.dungeoncrawl.ItemsPlacer;
import com.codecool.dungeoncrawl.MonsterPlacer;
import com.codecool.dungeoncrawl.converter.PlayerConverter;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.GameWorld;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameWorldFactory {
    private static final List<Actor> monsterList = new ArrayList<>();
    private static final List<GameMap> levels = new ArrayList<>();
    private static final File folder = new File("src/main/resources/levels");
    private static final File[] listOfFiles = folder.listFiles();
    private static Player player;

    public static GameWorld create() {

        int id = 1;
        for (File listOfFile : Objects.requireNonNull(listOfFiles)) {
            GameMap newMap =  createLevel(listOfFile.getName());

            ItemsPlacer newItemPlacer = new ItemsPlacer(newMap);
            MonsterPlacer monsterPlacer = new MonsterPlacer(newMap, id);
            newMap.setMonsterPlacer(monsterPlacer);
            newItemPlacer.addItemsRandomly();
            monsterPlacer.addAllMonsters();
            id = monsterPlacer.getId();
            monsterList.addAll(monsterPlacer.getMonsters());
        }
        createPlayer(levels.get(0), 8, 14);



        return new GameWorld(levels, levels.get(0), monsterList, ItemsFactory.getItems());
    }

    public static GameWorld importGame(GameState gameState, List<ItemModel> itemModels, List<MonsterModel> monsterModels, PlayerModel playerModel){
        levels.clear();
        ItemsFactory.getItems().clear();
        monsterList.clear();

        for (File listOfFile : Objects.requireNonNull(listOfFiles)) {
            GameMap newMap = createLevel(listOfFile.getName());

            ItemsPlacer newItemPlacer = new ItemsPlacer(newMap);
            newItemPlacer.addRecoveredItems(getItemsForLevel(itemModels, newMap.getMapNumber()));

            MonsterPlacer monsterPlacer = new MonsterPlacer(newMap, 1);

            monsterPlacer.addRecoveredMonsters(getMonstersForLevel(monsterModels, newMap.getMapNumber()));
            monsterList.addAll(monsterPlacer.getMonsters());
        }
        int index = Integer.parseInt(gameState.getCurrentMap().substring(3));
        GameMap currentMap = levels.get(index-1);
        PlayerConverter.recoveredPlayer(playerModel, currentMap, ItemsFactory.getItems());
        return new GameWorld(levels, currentMap, monsterList, ItemsFactory.getItems());
    }

    public static GameMap createLevel(String mapName) {
        GameMap newMap = MapLoader.loadMap("/levels/" + mapName);
        levels.add(newMap);
        int mapNumber = Integer.parseInt(String.valueOf(mapName.charAt(mapName.length() - 5)));
        newMap.setMapNumber(mapNumber);
        return newMap;
    }

    private static void createPlayer(GameMap map, int x, int y) {
        Cell cell = map.getCell(x, y);
        player = new Player(cell);
        map.setPlayer(player);
    }

    private static List<ItemModel> getItemsForLevel(List<ItemModel> itemModels, int mapNumber) {

        return itemModels.stream()
                .filter(itemModel -> itemModel.getMapNumber() == mapNumber)
                .collect(Collectors.toList());
    }

    private static List<MonsterModel> getMonstersForLevel(List<MonsterModel> monsterModels, int mapNumber) {
        return monsterModels.stream()
                .filter(monsterModel -> monsterModel.getMapNumber() == mapNumber)
                .collect(Collectors.toList());
    }
}
