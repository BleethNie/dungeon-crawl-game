package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medicine extends Item {
    public Medicine(Cell cell, int mapNumber) {
        super(cell, ItemNames.MEDICINE, mapNumber);
        super.points = 6;
        super.message = "Your health increased by " + points + " !";

    }

    @Override
    public String getTileName() {
        return ItemNames.MEDICINE.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().increaseHealth(points);
    }
}
