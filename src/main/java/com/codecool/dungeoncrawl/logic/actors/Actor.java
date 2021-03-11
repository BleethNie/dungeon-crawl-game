package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.CurrentStatus;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.function.Consumer;

public abstract class Actor implements Drawable {
    private Cell cell;
    private final int maxHealth = 10;
    private int health = 10;
    private int strength = 1;
    private int armor = 0;
    private static Consumer<Integer> onHealthChange = null;
    private static Consumer<Integer> onStrengthChange = null;
    private static Consumer<Integer> onArmorChange = null;
    private int id;
    private int mapNumber;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void fight(int dx, int dy) {
        Cell cell = getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor player = cell.getActor();
        Actor monster = nextCell.getActor();
        int hit = player.getStrength();

        int monsterHp = monster.getHealth();
        monster.setHealth(monsterHp - hit);
        player.counterAttack();

//        uncomment below for testing in command line:
        StringBuilder currentFightStatus = new StringBuilder();
        currentFightStatus.append("hit " + player.getStrength() + "\n");
        currentFightStatus.append("monsterHP " + monsterHp + "\n");
        currentFightStatus.append("armor " + player.getArmor() + "\n");
        currentFightStatus.append("playerHP " + player.getHealth());

        CurrentStatus.getInstance().setStatus(currentFightStatus.toString());

        if (monsterHp <= 0) {
            nextCell.setActor(null);
            CurrentStatus.getInstance().setStatus("You have won the fight with "
                    + monster.getClass().getSimpleName() + " !");
        }
    }

    public void counterAttack() {
        Cell cell = getCell();
        Actor player = cell.getActor();
        int playerHp = player.getHealth();
        int playerArmor = player.getArmor();

        if (playerArmor >= 2) {
            player.decreaseArmor(2);
        } else if (playerArmor == 1) {
            player.decreaseArmor(1);
            player.decreaseHealth(1);
        }  else {
            player.decreaseHealth(2);
        }
    }

    public void setOnHealthChange(Consumer<Integer> onHealthChange){
        this.onHealthChange = onHealthChange;
    }

    public void increaseHealth(int points) {
        this.health += points;
        if (this.onHealthChange != null){
            this.onHealthChange.accept(this.health);
        }
    }

    public void decreaseHealth(int points) {
        this.health -= points;
        if (this.onHealthChange != null){
            this.onHealthChange.accept(this.health);
        }
    }

    public void setOnStrengthChange(Consumer<Integer> onStrengthChange){
        this.onStrengthChange = onStrengthChange;
    }

    public void increaseStrength(int points) {
        this.strength += points;
        if (this.onStrengthChange != null){
            this.onStrengthChange.accept(this.strength);
        }
    }

    public void setOnArmorChange(Consumer<Integer> onArmorChange){
        this.onArmorChange = onArmorChange;
    }

    public void increaseArmor(int points) {
        this.armor += points;
        if (this.onArmorChange != null){
            this.onArmorChange.accept(this.armor);
        }
    }

    public void decreaseArmor(int points) {
        this.armor -= points;
        if (this.onArmorChange != null){
            this.onArmorChange.accept(this.armor);
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setCellForActor(Cell cell){
        this.cell = cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
