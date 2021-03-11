package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Demon extends Actor {
    public Demon(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "demon";
    }
}
