package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {

    public Key(Cell cell, int mapNumber){
        super(cell, ItemNames.KEY,mapNumber );
        super.message = "Key to secret door added to inventory";

    }

    @Override
    public String getTileName() {
        return ItemNames.KEY.getItemName();
    }


}
