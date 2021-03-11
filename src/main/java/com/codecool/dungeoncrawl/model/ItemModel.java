package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemModel extends BaseModel {


    private String name;
    private String message;
    private int x;
    private int y;
    private int points;
    private boolean inInventory;
    private int gameStateId;
    private int mapNumber;

    public ItemModel(Item item , int gameStateId){
        super.id = item.getId();
        this.name = item.getName();
        this.message = item.getMessage();
        this.x = item.getX();
        this.y = item.getY();
        this.points = item.getPoints();
        this.inInventory = item.isInInventory();
        this.gameStateId = gameStateId;
        this.mapNumber = item.getMapNumber();
    }

    public ItemModel(int id, String name, String message,int x, int y,
                      int points, boolean inInventory,int game_state_id, int mapNumber ){
        super.id = id;
        this.name = name;
        this.message = message;
        this.x = x;
        this.y = y;
        this.points = points;
        this.inInventory = inInventory;
        this.gameStateId = game_state_id;
        this.mapNumber = mapNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }

    public boolean isInInventory() {
        return inInventory;
    }

    @Override
    public int getId() {  return id; }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }
}
