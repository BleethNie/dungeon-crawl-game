package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonsterModel;

import java.util.List;

public interface MonsterDao {
    void add(MonsterModel monstersModel);
    void update(MonsterModel monstersModel);
    MonsterModel get(int id);
    List<MonsterModel> getAll(int gameStateId);
}
