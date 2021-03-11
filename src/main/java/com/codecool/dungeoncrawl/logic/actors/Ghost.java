package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }


    @Override
    public void move(int dx, int dy) {
        int x = RandomProvider.getRandomNumberOfRange(-7, 7);
        int y = RandomProvider.getRandomNumberOfRange(-7, 7);
        super.move(x, y);

    }
}

