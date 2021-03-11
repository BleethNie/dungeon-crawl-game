package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class GameState extends BaseModel {
    private Timestamp savedAt;
    private String currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private String saveName;


    public GameState(String currentMap, Timestamp savedAt, String saveName) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.saveName = saveName;

    }


    public Timestamp getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Timestamp savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
