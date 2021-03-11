package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.model.ItemModel;

public abstract class Item implements Drawable {
    private int id;
    private final ItemNames itemName;
    protected String message;
    private Cell cell;
    protected int points;
    private boolean inInventory;
    private int mapNumber;

    public Item(Cell cell, ItemNames itemName, int mapNumber){
        this.cell = cell;
        this.cell.setItem(this);
        this.itemName = itemName;
        this.mapNumber = mapNumber;
        this.inInventory = false;


    }


    public void setId(int id){
        this.id = id;
    };

    public int getId() {
        return id;
    }

    public ItemNames getItemName() {
        return itemName;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public String getName() {
        return itemName.getItemName();
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isInInventory() {return inInventory;    }

    public void setInInventory(boolean in) { this.inInventory = in;    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public void getImpactOnPlayer(){};
}
