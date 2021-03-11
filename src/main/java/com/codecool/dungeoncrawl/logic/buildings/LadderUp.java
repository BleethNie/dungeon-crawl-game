package com.codecool.dungeoncrawl.logic.buildings;

import com.codecool.dungeoncrawl.logic.Cell;

public class LadderUp extends Building{
    public LadderUp(Cell cell, BuildingsName buildingsName) {
        super(cell, buildingsName);
    }

    @Override
    public String getTileName() {
        return BuildingsName.UP.getTileName();
    }
}
