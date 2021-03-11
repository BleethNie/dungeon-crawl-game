package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {

    @Test
    void givenTileGetTileNameShouldReturnDemon() {
        //given
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
        Actor skeleton = new Skeleton(gameMap.getCell(1, 1));

        //when
        String monsterName = skeleton.getTileName();

        // then
        assertEquals("skeleton", monsterName);
    }
}