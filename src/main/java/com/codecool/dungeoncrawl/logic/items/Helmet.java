package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {
    public Helmet(Cell cell, int mapNumber){
        super(cell, ItemNames.HELMET, mapNumber);
        super.points = 4;
        super.message = "New helmet added to inventory \n +" + points + " to armor !";

    }

    @Override
    public String getTileName() {
        return ItemNames.HELMET.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().increaseArmor(points);
    }
}
