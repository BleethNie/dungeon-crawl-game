package com.codecool.dungeoncrawl.logic.buildings;

import com.codecool.dungeoncrawl.logic.Cell;

public class OpenDoor extends Building{
    public OpenDoor(Cell cell, BuildingsName buildingsName) {
        super(cell, buildingsName);
    }

    @Override
    public String getTileName() {
        return BuildingsName.OPENDOOR.getTileName();
    }
}
