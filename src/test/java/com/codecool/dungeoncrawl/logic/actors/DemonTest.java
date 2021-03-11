package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemonTest {

    @Test
    void givenTileGetTileNameShouldReturnDemon() {
        //given
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Actor demon = new Demon(gameMap.getCell(1, 1));

        //when
        String monsterName = demon.getTileName();

        // then
        assertEquals("demon", monsterName);
    }
}