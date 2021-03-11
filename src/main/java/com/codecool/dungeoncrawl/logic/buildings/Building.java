package com.codecool.dungeoncrawl.logic.buildings;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Building implements Drawable {
    private Cell cell;
    private BuildingsName buildingsName;

    public Building(Cell cell, BuildingsName buildingsName) {
        this.cell = cell;
        this.cell.setBuilding(this);
        this.buildingsName = buildingsName;
    }

    public Cell getCell() {
        return cell;
    }
}
