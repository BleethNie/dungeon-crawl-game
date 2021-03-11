package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class GameDatabaseManager {

    private PlayerDao playerDao;
    private ItemDao itemDao;
    private GameStateDao gameStateDao;
    private MonsterDao monsterDao;


    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        monsterDao = new MonsterDaoJdbc(dataSource);
    }

    public void saveGameState(String currentMap, String saveName, Player player) {
        GameState gameState = new GameState(currentMap, new Timestamp(System.currentTimeMillis()), saveName);
        gameStateDao.add(gameState);
        player.setId(gameState.getId());

        System.out.println(gameStateDao.getAll());
    }

    public List<String> getAllSavedNames() {
        return gameStateDao.getAllSavedNames();
    }

    public int getIdForSaveName(String saveName){
        return gameStateDao.getGameId(saveName);
    }

    public GameState getGameState(int gameStateId){
        return gameStateDao.get(gameStateId);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }

    public PlayerModel getPlayerModel(int gameStateId){
        return playerDao.get(gameStateId);
    }

    private ItemModel getItemModel(Item item, int gameStateId){
        ItemModel itemModel = new ItemModel(item, gameStateId);
        return itemModel;
    }

    public void saveItems(List<Item> itemList, int gameStateId){
        for (Item item : itemList){
            ItemModel itemModel = getItemModel(item, gameStateId);
            itemDao.add(itemModel);
        }
    }

    public void updateItems(List<Item> itemList, int gameStateId){
        for (Item item : itemList){
            ItemModel itemModel = getItemModel(item, gameStateId);
            itemDao.update(itemModel);
        }
    }

    public ItemModel getItemModel(int id,int gameStatusId, int mapNumber ){
        return itemDao.get(id, gameStatusId, mapNumber);
    }


    public List<ItemModel> getAllItemModels(int gameStatusId){
        return itemDao.getAll(gameStatusId);
    }

    public List<MonsterModel> getAllMonstersModels(int gameStatusId){
        return monsterDao.getAll(gameStatusId);
    }

    private MonsterModel getMonsterModel(Actor actor, int gameStateId){
        MonsterModel monsterModel = new MonsterModel(actor, gameStateId);
        return monsterModel;
    }

    public void saveMonsters(List<Actor> monsterList, int gameStateId) {
        for (Actor actor : monsterList) {
            MonsterModel monsterModel = getMonsterModel(actor, gameStateId);
            monsterDao.add(monsterModel);
        }
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();


        // update for your data base
        String serverName = System.getenv("LOCAL_HOST");
        int portNumber = 5432;
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
