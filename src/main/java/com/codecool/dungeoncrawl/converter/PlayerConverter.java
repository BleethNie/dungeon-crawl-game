package com.codecool.dungeoncrawl.converter;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerConverter {

    public static Player recoveredPlayer(PlayerModel playerModel, GameMap map, List<Item> items){
        Cell cell = map.getCell(playerModel.getX(), playerModel.getY());

        Player recoveredPlayer = new Player(cell);
        recoveredPlayer.setName(playerModel.getPlayerName());
        recoveredPlayer.setStrength(playerModel.getStrength());
        recoveredPlayer.setHealth(playerModel.getHp());
        recoveredPlayer.setArmor(playerModel.getArmor());
        recoveredPlayer.setInventory(recoverInventory(items));
        map.setPlayer(recoveredPlayer);

        return  recoveredPlayer;
    }

    private static Inventory recoverInventory(List<Item> items){
        List<Item> inInventory = items.stream()
                .filter(item -> item.isInInventory())
                .collect(Collectors.toList());

        Inventory inventory = new Inventory();
        inventory.setInventoryList(new ArrayList<>(inInventory));

        return inventory;
    }
}
