package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

import static com.codecool.dungeoncrawl.Tiles.TILE_WIDTH;

public class GameCamera {
    private GameMap game;
    private float xOffset, yOffset;

    public GameCamera(GameMap game, float xOffset, float yOffset){
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Player e){
        xOffset = (e.getX() * 32f) - game.getWidth() * 32f / 2 + TILE_WIDTH / 2;
        yOffset = (e.getY() * 32f) - game.getHeight() * 32f / 2 + TILE_WIDTH / 2;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

}
