package com.codecool.dungeoncrawl.logic.buildings;

import com.codecool.dungeoncrawl.logic.Cell;

public class LadderDown extends Building {
    public LadderDown(Cell cell, BuildingsName buildingsName) {
        super(cell, buildingsName);
    }

    @Override
    public String getTileName() {
        return BuildingsName.DOWN.getTileName();
    }
}
