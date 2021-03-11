package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameJsonManager {

    private GameState gameState;
    private PlayerModel playerModel;
    private List<ItemModel> itemModels;
    private List<MonsterModel> monsterModels;

    public GameJsonManager(){

    }

    public void update(String currentMap, String saveName, Player player,
                       List<Item> items,List<Actor> monsters) {
        this.gameState = prepareGameState(currentMap, saveName);
        this.playerModel = preparePlayerModel(player);
        this.itemModels = prepareItemModels(items);
        this.monsterModels = prepareMonsterModels(monsters);

    }

    private GameState prepareGameState(String currentMap, String saveName){
        GameState gameState = new GameState(currentMap, new Timestamp(System.currentTimeMillis()), saveName);
        return gameState;
    }

    private PlayerModel preparePlayerModel(Player player){
        return new PlayerModel(player);
    }

    private ItemModel prepareItemModel(Item item){
        return new ItemModel(item, 0);
    }

    private List<ItemModel> prepareItemModels(List<Item> items){
        itemModels = new ArrayList<>();
        itemModels = items.stream()
                .map(item -> prepareItemModel(item))
                .collect(Collectors.toList());
        return itemModels;
    }

    private MonsterModel prepareMonsterModel(Actor monster){
        return new MonsterModel(monster,0);
    }

    private List<MonsterModel> prepareMonsterModels(List<Actor> monsters){
        monsterModels = new ArrayList<>();
        monsterModels = monsters.stream()
                .map(monster -> prepareMonsterModel(monster))
                .collect(Collectors.toList());
        return monsterModels;
    }


    private String serialize(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGson = gson.toJson(this);
        System.out.println(serializedGson);

        return serializedGson;
    }

    private void deserialize(String serializedString){
        GameJsonManager deserializedOutput = new Gson().fromJson(serializedString, GameJsonManager.class);
        System.out.println(deserializedOutput.toString());

        this.playerModel = deserializedOutput.getPlayerModel();
        this.itemModels = deserializedOutput.getItemModels();
        this.gameState = deserializedOutput.getGameState();
        this.monsterModels = deserializedOutput.getMonsterModels();
    }


    public void saveToProjectFile(File file){
        String serializedJson = serialize();
        try {
            writeToJsonFile(serializedJson, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importProject(File file){
        System.out.println("Now deserialization....");
        getDeserializedModels(file);
    }


    private void writeToJsonFile(String jsonString, File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        writer.write(jsonString);
        writer.close();
    }

    private String getFromJsonFile(File file) throws IOException {
        FileReader reader = new FileReader(file.getAbsolutePath());
        StringBuilder content = new StringBuilder();
        int nextChar;
        while ((nextChar = reader.read()) != -1) {
            content.append((char) nextChar);
        }
        return String.valueOf(content);
    }


    private void getDeserializedModels(File file){
        try {
            String serializedString = getFromJsonFile(file);
            deserialize(serializedString);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }



    @Override
    public String toString(){
        String fields = this.playerModel.getPlayerName() + " " +
                this.playerModel.getArmor() + " " +
                this.playerModel.getStrength() +" " +
                this.playerModel.getX() +" "
//                this.playerModel.getGameStateId() +"\n "

                ;

        String item = "";

        for (ItemModel im : itemModels){
            item += im.getName() + " " + im.getMapNumber() + " " + im.getX() + " " + im.isInInventory() + "\n";
        }

        return fields + item;
    }

    public GameState getGameState() {
        return gameState;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public List<MonsterModel> getMonsterModels() {
        return monsterModels;
    }


}
