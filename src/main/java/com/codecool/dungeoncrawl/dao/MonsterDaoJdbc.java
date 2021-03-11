package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonsterModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonsterDaoJdbc implements MonsterDao {
    private DataSource dataSource;

    public MonsterDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(MonsterModel monstersModel) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO monsters (id, monster_name, hp, x, y, game_state_id, map_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, monstersModel.getId());
            statement.setString(2, monstersModel.getMonsterName());
            statement.setInt(3, monstersModel.getHp());
            statement.setInt(4, monstersModel.getX());
            statement.setInt(5, monstersModel.getY());
            statement.setInt(6, monstersModel.getGameStateId());
            statement.setInt(7, monstersModel.getMapNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(MonsterModel monstersModel) {

    }

    @Override
    public MonsterModel get(int id) {
        return null;
    }

    @Override
    public List<MonsterModel> getAll(int gameStateId) {
        String sql = "SELECT *  FROM monsters " +
                "WHERE game_state_id = ?";

        List<MonsterModel> monsterModels = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, gameStateId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    MonsterModel monsterModel = new MonsterModel(rs.getInt("id"),
                            rs.getString("monster_name"),
                            rs.getInt("hp"),
                            rs.getInt("x"),
                            rs.getInt("y"),
                            rs.getInt("game_state_id"),
                            rs.getInt("map_number"));
                    monsterModels.add(monsterModel);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return monsterModels;
    }
}
