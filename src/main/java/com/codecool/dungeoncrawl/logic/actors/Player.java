package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.buildings.BuildingsName;
import com.codecool.dungeoncrawl.logic.buildings.OpenDoor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemNames;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;

import java.util.ArrayList;

public class Player extends Actor {

    private String name;
    private Inventory inventory;

    public Player(Cell cell) {

        super(cell);
        this.inventory = new Inventory();
        this.setHealth(50);
        this.setStrength(3);
    }

    public String getTileName() {
        return "player";
    }

    public void addToInventory(Item item){
        if (!item.getName().equals(ItemNames.MEAT.getItemName())){
            inventory.add(item);
            item.setInInventory(true);

        } else {
            ItemsFactory.getItems().remove(item);
        }
        item.getImpactOnPlayer();
    }

    public ArrayList<String> getInventoryItemsNames(){
        return inventory.getItemsNames();
    }

    public ArrayList<Item> getInventoryItems(){
        return inventory.getItems();
    }

    public void removeItemFromInventory(Item item){
        inventory.remove(item);
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void move(int dx, int dy) {
        Cell cell = getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.FLOOR) && nextCell.getBuilding() != null
                && nextCell.getBuilding().getTileName().equals("close door")) {
            if (getInventoryItemsNames().contains("key")) {
                cell.setActor(null);
                setCell(cell);
                nextCell.setActor(this);
                cell = nextCell;
                cell.setBuilding(new OpenDoor(cell, BuildingsName.OPENDOOR));
                setCell(cell);
                inventory.removeKey();
                CurrentStatus.getInstance().setStatus("Door has been opened");
            } else {
                CurrentStatus.getInstance().setStatus("You need a key");
            }
        } else if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null || (
                DevelopersNames.getDevelopersNames().contains(this.getName()) && (
                        nextCell.getType().equals(CellType.EMPTY) || nextCell.getType().equals(CellType.WALL))
                )) {
            cell.setActor(null);
            setCell(cell);
            nextCell.setActor(this);
            cell = nextCell;
            setCell(cell);
        } else if(nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() != null) {
            fight(dx, dy);
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
