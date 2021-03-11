package com.codecool.dungeoncrawl.converter;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.model.MonsterModel;

public class MonsterConverter {
    public static Actor recoverMonster(MonsterModel monsterModel, GameMap map) {
        Cell cell = map.getCell(monsterModel.getX(), monsterModel.getY());
        MonsterNames monsterName = MonsterNames.valueOf(monsterModel.getMonsterName().toUpperCase());

        Actor recoveredMonster = null;

        switch (monsterName) {
            case DEMON:
                recoveredMonster = new Demon(cell);
                break;
            case GHOST:
                recoveredMonster = new Ghost(cell);
                break;
            case SKELETON:
                recoveredMonster = new Skeleton(cell);
                break;
            default:
                throw new IllegalArgumentException("monster not available");
        }
        recoveredMonster.setId(monsterModel.getId());
        recoveredMonster.setHealth(monsterModel.getHp());
        recoveredMonster.setMapNumber(monsterModel.getMapNumber());
        recoveredMonster.setCell(cell);

        return recoveredMonster;
    }
}
