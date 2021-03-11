package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, armor, strength, x, y, game_state_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getArmor());
            statement.setInt(4, player.getStrength());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setInt(7, player.getGameStateId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(int game_state_id) {
        String sql = "SELECT player_name, hp, armor, strength, x, y, " +
                "game_state_id  FROM player WHERE game_state_id = ? ";

        PlayerModel playerModel = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,game_state_id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()){
                    playerModel = new PlayerModel(
                            rs.getString("player_name"),
                            rs.getInt("hp"),
                            rs.getInt("armor"),
                            rs.getInt("strength"),
                            rs.getInt("x"),
                            rs.getInt("y"),
                            rs.getInt("game_state_id"));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return playerModel;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
