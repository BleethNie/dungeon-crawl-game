package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.List;

public class ItemsFactory {
    private static int maxid = 0;

    private static List<Item> items = new ArrayList<>();

    public static Item createItem(Cell cell, ItemNames itemName, int mapNumber ){
        Item newItem = null;
        switch (itemName){
            case KEY:
                newItem = new Key(cell, mapNumber);
                break;
            case SWORD:
                newItem = new Sword(cell, mapNumber);
                break;
            case HELMET:
                newItem = new Helmet(cell, mapNumber);
                break;
            case SHIELD:
                newItem = new Shield(cell, mapNumber);
                break;
            case MEAT:
                newItem = new Meat(cell, mapNumber);
                break;
            case GLOVES:
                newItem = new Gloves(cell, mapNumber);
                break;
            case MEDICINE:
                newItem = new Medicine(cell, mapNumber);
                break;
            default:
                throw new IllegalArgumentException("itemName not available");
        }
        newItem.setId(generateId());
        items.add(newItem);
        return newItem;
    }

    private static int generateId(){
        return ++maxid;
    }
    public static List<Item> getItems() {
        return items;
    }
}
