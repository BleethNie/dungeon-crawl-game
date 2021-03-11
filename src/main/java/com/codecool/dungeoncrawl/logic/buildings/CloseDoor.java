package com.codecool.dungeoncrawl.logic.buildings;

import com.codecool.dungeoncrawl.logic.Cell;

public class CloseDoor extends Building {
    public CloseDoor(Cell cell, BuildingsName buildingsName) {
        super(cell, buildingsName);
    }

    @Override
    public String getTileName() {
        return BuildingsName.DOOR.getTileName();
    }
}
