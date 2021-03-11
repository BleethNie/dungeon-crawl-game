package com.codecool.dungeoncrawl.logic.actors;

public enum MonsterNames {
    DEMON("demon"),
    GHOST("ghost"),
    SKELETON("skeleton");



    private final String monsterName;

    MonsterNames(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getItemName() {
        return monsterName;
    }
}
