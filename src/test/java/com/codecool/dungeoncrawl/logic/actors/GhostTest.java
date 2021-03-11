package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);

    @Test
    void givenTileGetTileNameShouldReturnGhost() {
        //given
        Actor ghost = new Ghost(gameMap.getCell(1, 1));

        //when
        String monsterName = ghost.getTileName();

        // then
        assertEquals("ghost", monsterName);
    }


    @Test
    void givenMoveShouldMoveGhostInRangeSeven() {
        //given
        int startX = 1;
        int startY = 1;
        int maxLimitX = 8;
        int maxLimitY = 8;
        int minLimitX = 0;
        int minLimitY = 0;
        Actor ghost = new Ghost(gameMap.getCell(startX, startY));

        //when
        ghost.move(startX,startY);
        Cell cell = ghost.getCell();
        int xAfterMove = cell.getX();
        int yAfterMove = cell.getY();

        //then
        assertTrue(xAfterMove <= maxLimitX && xAfterMove >= minLimitX);
        assertTrue(yAfterMove <= maxLimitY && yAfterMove >= minLimitY);

    }
}