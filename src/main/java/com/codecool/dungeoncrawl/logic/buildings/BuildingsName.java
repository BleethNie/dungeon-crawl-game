package com.codecool.dungeoncrawl.logic.buildings;

public enum BuildingsName {
    UP("ladder up"),
    DOWN("ladder down"),
    DOOR("close door"),
    OPENDOOR("open door");

    private final String buildingName;

    BuildingsName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTileName() {
        return buildingName;
    }

}
