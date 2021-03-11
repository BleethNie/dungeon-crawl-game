package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.converter.MonsterConverter;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Demon;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;
import com.codecool.dungeoncrawl.model.MonsterModel;

import java.util.ArrayList;
import java.util.List;

public class MonsterPlacer {
    private GameMap map;

    private int mapNumber;
    private int ghostCounter;
    private int demonCounter;
    private final int ghostNumber;
    private final int demonNumber;
    private List<Actor> monsters = new ArrayList<>();
    private int skeletonCounter;
    private final int skeletonNumber;
    private int id;


    public MonsterPlacer(GameMap map, int id) {
        this.map = map;
        this.mapNumber = setMapNumber(map.getMapNumber());
        this.ghostCounter = 0;
        this.demonCounter = 0;
        this.ghostNumber = 4;
        this.demonNumber = 2;
        this.skeletonCounter = 0;
        this.skeletonNumber = 3;
        this.id = id;
    }

    private int setMapNumber(int mapNumber) {
        int number =0;
        switch (mapNumber){
            case 1:
                number = 1;
                break;
            case 2:
                number = 2;
                break;
            default:
                number = 3;
        }
        return number;
    }

    private boolean enoughGhost() {
        return ghostCounter >= ghostNumber;
    }

    private boolean enoughDemon() {
        return demonCounter >= demonNumber;
    }

    private boolean enoughSkeleton() {
        return skeletonCounter >= skeletonNumber;
    }

    public void addGhostRandomly() {
        boolean cellFoundGhost = false;
        while (!cellFoundGhost) {
            int x = RandomProvider.getRandomNumberOfRange(1, map.getWidth() - 1);
            int y = RandomProvider.getRandomNumberOfRange(1, map.getHeight() - 1);
            Cell cell = map.getCell(x, y);
            if (cell.canAddItem()) {
                Ghost ghost = new Ghost(cell);
                cellFoundGhost = true;
                ghost.setId(id);
                ghost.setMapNumber(mapNumber);
                id++;
                monsters.add(ghost);
            }
        }
    }

    public void addDemonRandomly() {
        boolean cellFoundDemon = false;
        while (!cellFoundDemon) {
            int x = RandomProvider.getRandomNumberOfRange(1, map.getWidth() - 1);
            int y = RandomProvider.getRandomNumberOfRange(1, map.getHeight() - 1);
            Cell cell = map.getCell(x, y);
            if (cell.canAddItem()) {
                Demon demon = new Demon(cell);
                cellFoundDemon = true;
                demon.setId(id);
                demon.setMapNumber(mapNumber);
                id++;
                monsters.add(demon);
            }
        }
    }

    public void addSkeletonRandomly() {
        boolean cellSkeletonDemon = false;
        while (!cellSkeletonDemon) {
            int x = RandomProvider.getRandomNumberOfRange(1, map.getWidth() - 1);
            int y = RandomProvider.getRandomNumberOfRange(1, map.getHeight() - 1);
            Cell cell = map.getCell(x, y);
            if (cell.canAddItem()) {
                Skeleton skeleton = new Skeleton(cell);
                cellSkeletonDemon = true;
                skeleton.setId(id);
                skeleton.setMapNumber(mapNumber);
                id++;
                monsters.add(skeleton);
            }
        }
    }

    public void addAllMonsters() {
        while (!enoughGhost()) {
            addGhostRandomly();
            ghostCounter++;
        }
        while (!enoughDemon()) {
            addDemonRandomly();
            demonCounter++;
        }

        while (!enoughSkeleton()) {
            addSkeletonRandomly();
            skeletonCounter++;
        }
    }

    public List<Actor> getMonsters() {
        return monsters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addRecoveredMonsters(List<MonsterModel> models){
        for (MonsterModel model : models){
            Actor monster = MonsterConverter.recoverMonster(model, map);
            if (monster.isDead()) {
                monster.getCell().setActor(null);
            }
            monsters.add(monster);
        }

    }
}