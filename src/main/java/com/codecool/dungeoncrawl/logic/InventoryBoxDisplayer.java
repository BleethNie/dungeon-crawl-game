package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Tiles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class InventoryBoxDisplayer {

    private Inventory inventory;
    private ListView<String> inventoryListView;

    public InventoryBoxDisplayer(Inventory inventory, ListView<String> inventoryListView){
        this.inventory = inventory;
        this.inventoryListView = inventoryListView;
    }

    public void refresh(){
        ArrayList<String> itemsNames = inventory.getItemsNames();
        ObservableList<String> items = FXCollections.observableArrayList(itemsNames);

        inventoryListView.setItems(items);
        addIconsToListView(inventoryListView);
    }

    private void addIconsToListView(ListView<String> inventoryListView){
        inventoryListView.setCellFactory(param -> new ListCell<String>(){
            @Override
            public void updateItem(String name, boolean empty){
                super.updateItem(name, empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                } else {
                    Canvas canvas = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    context.fillRect(0,0,1,1);
                    Tiles.drawTileInInventory(context,name, 0,0);
                    setText(name);
                    setGraphic(canvas);
                }
            }
        });
    }
}
