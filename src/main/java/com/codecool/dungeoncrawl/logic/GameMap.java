package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.MonsterPlacer;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private int mapNumber;
    private int width;
    private int height;
    private Cell[][] cells;
    private MonsterPlacer monsterPlacer;
    int goUpX;
    int goUpY;
    int goDownX;
    int goDownY;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGoUpX() {
        return goUpX;
    }

    public void setGoUpX(int goUpX) {
        this.goUpX = goUpX;
    }

    public int getGoUpY() {
        return goUpY;
    }

    public void setGoUpY(int goUpY) {
        this.goUpY = goUpY;
    }

    public int getGoDownX() {
        return goDownX;
    }

    public void setGoDownX(int goDownX) {
        this.goDownX = goDownX;
    }

    public int getGoDownY() {
        return goDownY;
    }

    public void setGoDownY(int goDownY) {
        this.goDownY = goDownY;
    }

    public MonsterPlacer getMonsterPlacer() {
        return monsterPlacer;
    }

    public void setMonsterPlacer(MonsterPlacer monsterPlacer) {
        this.monsterPlacer = monsterPlacer;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }
}
