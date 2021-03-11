package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoJdbc implements ItemDao {
    private DataSource dataSource;
    private PlayerDao playerDao;

    public ItemDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;

    }

    @Override
    public void add(ItemModel item) {
        String sql = "INSERT INTO items (id, item_name, message, x, y, points, " +
                "inventory, game_state_id, map_number) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,item.getId());
            statement.setString(2,item.getName());
            statement.setString(3,item.getMessage());
            statement.setInt(4,item.getX());
            statement.setInt(5,item.getY());
            statement.setInt(6,item.getPoints());
            statement.setBoolean(7, item.isInInventory());
            statement.setInt(8, item.getGameStateId());
            statement.setInt(9, item.getMapNumber());

            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ItemModel item) {
        String sql = "UPDATE items SET  inventory = ? " +
                "WHERE id = ? AND game_state_id = ? AND map_number = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setBoolean(1,item.isInInventory());
            statement.setInt(2, item.getId());
            statement.setInt(3,item.getGameStateId());
            statement.setInt(4, item.getMapNumber());

            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemModel get(int id, int gameStateId, int mapNumber) {
        String sql = "SELECT id, item_name, message, x, y, points, " +
                "inventory, game_state_id, map_number  FROM items " +
                "WHERE id = ? AND game_state_id = ? AND map_number = ?";

        ItemModel itemModel = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.setInt(2, gameStateId);
            statement.setInt(3, mapNumber);


            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
//                    itemModel = createItemModel(rs);
                    itemModel = new ItemModel(rs.getInt("id"),
                                                rs.getString("item_name"),
                                                rs.getString("message"),
                                                rs.getInt("x"),
                                                rs.getInt("y"),
                                                rs.getInt("points"),
                                                rs.getBoolean("inventory"),
                                                rs.getInt("game_state_id"),
                                                rs.getInt("map_number"));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return itemModel;
    }

    public List<ItemModel> getAll(int gameStateId) {
        String sql = "SELECT id, item_name, message, x, y, points, " +
                "inventory, game_state_id, map_number  FROM items " +
                "WHERE game_state_id = ?";

        List<ItemModel> itemsModels = new ArrayList<>();


        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, gameStateId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    ItemModel itemModel = new ItemModel(rs.getInt("id"),
                            rs.getString("item_name"),
                            rs.getString("message"),
                            rs.getInt("x"),
                            rs.getInt("y"),
                            rs.getInt("points"),
                            rs.getBoolean("inventory"),
                            rs.getInt("game_state_id"),
                            rs.getInt("map_number"));
                    itemsModels.add(itemModel);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return itemsModels;
    }

}
