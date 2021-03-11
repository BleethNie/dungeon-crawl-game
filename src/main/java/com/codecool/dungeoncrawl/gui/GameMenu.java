package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.gui.guiControllers.ButtonPickUp;
import com.codecool.dungeoncrawl.logic.InventoryBoxDisplayer;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameMenu extends GridPane {
    private final Label nameLabel;
    private final Label healthLabel = new Label();
    private final Label strengthLabel = new Label();
    private final Label armorLabel = new Label();
    private final HBox hbox = new HBox();
    private final StatusLine status = new StatusLine("Let's start the game!");
    private final ListView<String> inventoryListView = new ListView<>();
    private final HBox infoBox = new HBox(status);
    private final HBox inventoryHBox = new HBox(inventoryListView);
    private final ButtonPickUp pickUpButton;
    private final InventoryBoxDisplayer inventoryBoxDisplayer;

    public GameMenu(Player player) {
        super();
        this.setPrefWidth(200);
        this.setPadding(new Insets(10));
        nameLabel = new Label();
        initNameLabel(player.getName());
        initHealthLabel(player.getHealth());
        this.inventoryBoxDisplayer = new InventoryBoxDisplayer(player.getInventory(), inventoryListView);
        this.pickUpButton = new ButtonPickUp(player, inventoryBoxDisplayer);
        healthLabel.setText("" + player.getHealth());
        strengthLabel.setText("" + player.getStrength());
        armorLabel.setText("" + player.getArmor());
        inventoryListView.setPrefHeight(240);
        inventoryListView.setFocusTraversable(false);


        hbox.getChildren().add(pickUpButton);
        hbox.setPadding(new Insets(35, 0, 35, 0));
        hbox.setAlignment(Pos.CENTER);
        uiAddElements();
    }

    private void initHealthLabel(int health) {

    }

    private void initNameLabel(String name) {
        nameLabel.setText(name);
        nameLabel.setStyle("-fx-font-weight: bold;");
        add(new Label("Player: "), 0, 0);
        add(nameLabel, 1, 0);
    }

    private void uiAddElements() {
        this.add(new Label("Health: "), 0, 1);
        this.add(healthLabel, 1, 1);
        this.add(new Label("Strength: "), 0, 2);
        this.add(strengthLabel, 1, 2);
        this.add(new Label("Armor: "), 0, 3);
        this.add(armorLabel, 1, 3);

        this.add(new Label("Inventory: "), 0, 4);
        this.add(inventoryHBox, 0, 5, 2, 1);
        this.add(hbox, 0, 6, 2, 1);
        this.add(new Label("Status: "), 0, 7);
        this.add(infoBox, 0, 8, 2, 1);
    }

    public void setNameLabel(String name){
        nameLabel.setText(name);
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public Label getStrengthLabel() {
        return strengthLabel;
    }


    public Label getArmorLabel() {
        return armorLabel;
    }

    public StatusLine getStatus() {
        return status;
    }

    public ButtonPickUp getPickUpButton() {
        return pickUpButton;
    }

    public InventoryBoxDisplayer getInventoryBoxDisplayer() {
        return inventoryBoxDisplayer;
    }
}
