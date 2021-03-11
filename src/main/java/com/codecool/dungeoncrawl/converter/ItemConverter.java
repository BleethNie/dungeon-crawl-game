package com.codecool.dungeoncrawl.converter;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.ItemModel;

public class ItemConverter {

    public static Item recoverItem(ItemModel itemModel, GameMap map){
        Cell cell = map.getCell(itemModel.getX(), itemModel.getY());
        ItemNames itemName = ItemNames.valueOf(itemModel.getName().toUpperCase());

        Item recoveredItem = null;

        switch (itemName){
            case KEY:
                recoveredItem = new Key(cell, itemModel.getMapNumber());
                break;

            case SWORD:
                recoveredItem = new Sword(cell, itemModel.getMapNumber());
                break;
            case HELMET:
                recoveredItem = new Helmet(cell, itemModel.getMapNumber());
                break;
            case SHIELD:
                recoveredItem = new Shield(cell, itemModel.getMapNumber());
                break;
            case MEAT:
                recoveredItem = new Meat(cell, itemModel.getMapNumber());
                break;
            case GLOVES:
                recoveredItem = new Gloves(cell, itemModel.getMapNumber());
                break;
            case MEDICINE:
                recoveredItem = new Medicine(cell, itemModel.getMapNumber());
                break;
            default:
                throw new IllegalArgumentException("itemName not available");
        }
        recoveredItem.setId(itemModel.getId());
        recoveredItem.setMessage(itemModel.getMessage());
        recoveredItem.setCell(cell);
        recoveredItem.setPoints(itemModel.getPoints());
        recoveredItem.setInInventory(itemModel.isInInventory());
        recoveredItem.setMapNumber(itemModel.getMapNumber());



        return recoveredItem;
    }

}
