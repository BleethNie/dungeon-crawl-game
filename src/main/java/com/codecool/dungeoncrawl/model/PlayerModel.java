package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int x;
    private int y;
    private int armor;
    private int strength;
    private int gameStateId;

    public PlayerModel(String playerName,int hp, int armor, int strength,
                       int x, int y, int gameStateId) {
        this.playerName = playerName;
        this.hp = hp;
        this.armor = armor;
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.gameStateId = gameStateId;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();

        this.hp = player.getHealth();
        this.armor = player.getArmor();
        this.strength = player.getStrength();
        this.gameStateId = player.getId();

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }
}
