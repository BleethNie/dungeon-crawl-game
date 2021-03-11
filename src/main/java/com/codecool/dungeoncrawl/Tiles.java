package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.ItemNames;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("ghost", new Tile(24, 7));
        tileMap.put("demon", new Tile(26, 5));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("sword", new Tile(1,30));
        tileMap.put("helmet", new Tile(4,22));
        tileMap.put("ladder up", new Tile(21, 1));
        tileMap.put("ladder down", new Tile(21, 0));
        tileMap.put(ItemNames.SHIELD.getItemName(), new Tile(7,25));
        tileMap.put(ItemNames.GLOVES.getItemName(), new Tile(10,22));
        tileMap.put(ItemNames.MEDICINE.getItemName(), new Tile(25, 23));
        tileMap.put(ItemNames.MEAT.getItemName(), new Tile(17, 28));
        tileMap.put("close door", new Tile(12, 17));
        tileMap.put("open door", new Tile(13, 17));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, GameCamera gameCamera) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
            (int) (x * TILE_WIDTH - gameCamera.getXOffset()), (int) (y * TILE_WIDTH - gameCamera.getYOffset()), TILE_WIDTH, TILE_WIDTH);
    }

    public static Tile getTile(String itemName){
        return tileMap.get(itemName);
    }

    public static void drawTileInInventory(GraphicsContext context, String name,int x,int y){
        Tile tile = getTile(name);
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
