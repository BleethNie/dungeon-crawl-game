package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Demon;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.buildings.BuildingsName;
import com.codecool.dungeoncrawl.logic.buildings.CloseDoor;
import com.codecool.dungeoncrawl.logic.buildings.LadderDown;
import com.codecool.dungeoncrawl.logic.buildings.LadderUp;
import com.codecool.dungeoncrawl.logic.items.Helmet;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFile) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int goUpX = scanner.nextInt();
        int goUpY = scanner.nextInt();
        int goDownX = scanner.nextInt();
        int goDownY = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'u':
                            cell.setType(CellType.FLOOR);
                            new LadderUp(cell, BuildingsName.UP);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new LadderDown(cell, BuildingsName.DOWN);
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new CloseDoor(cell, BuildingsName.DOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        map.setGoUpX(goUpX);
        map.setGoUpY(goUpY);
        map.setGoDownX(goDownX);
        map.setGoDownY(goDownY);
        return map;
    }

}
