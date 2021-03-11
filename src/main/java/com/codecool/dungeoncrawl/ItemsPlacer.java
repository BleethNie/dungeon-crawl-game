package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.converter.ItemConverter;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemNames;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;
import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsPlacer {

    private static Map<Integer, ItemNames[]> itemsContainer = new HashMap<>();
    static {
        itemsContainer.put(1, new ItemNames[]{ItemNames.KEY, ItemNames.SWORD, ItemNames.HELMET, ItemNames.MEAT});
        itemsContainer.put(2,new ItemNames[]{ItemNames.MEAT, ItemNames.GLOVES, ItemNames.SHIELD,ItemNames.SWORD, ItemNames.HELMET});
        itemsContainer.put(3, new ItemNames[]{ItemNames.MEDICINE,ItemNames.HELMET, ItemNames.SWORD});
    }

    private GameMap map;

    private int mapNumber;

    public ItemsPlacer(GameMap map) {
        this.map = map;
        this.mapNumber = setMapNumber(map.getMapNumber());
    }

    private int setMapNumber(int mapNumber) {
        int number =0;
        switch (mapNumber){
            case 1:
                number = 1;
                break;
            case 2:
                number = 2;
                break;
            default:
                number = 3;
        }
        return number;
    }


    public void addItemsRandomly(){
        for (ItemNames name : itemsContainer.get(mapNumber)){
            if (name.equals(ItemNames.KEY)){
                Cell cell = map.getCell(3,1);
                ItemsFactory.createItem(cell,name,mapNumber );
            } else {
                boolean cellFound = false;
                while (!cellFound){
                    int x = RandomProvider.getRandomNumberOfRange(1,map.getWidth() -1);
                    int y = RandomProvider.getRandomNumberOfRange(1,map.getHeight() -1);
                    Cell cell = map.getCell(x,y);
                    if (cell.canAddItem()){
                        ItemsFactory.createItem(cell, name, mapNumber);
                        cellFound = true;
                    }
                }
            }

        }
    }

    public void addRecoveredItems(List<ItemModel> models){
        for (ItemModel model : models){
            Item item = ItemConverter.recoverItem(model,map);
            // updating list of all items in ItemFactory
            if (item.isInInventory()) {
                item.getCell().setItem(null);
            }

            ItemsFactory.getItems().add(item);
        }

    }
}
