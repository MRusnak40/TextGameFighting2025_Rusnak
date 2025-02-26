package Rooms;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import NPCs.Enemy;
import NPCs.NPC;

import java.util.ArrayList;

public  class    Room {
    protected String  nameOfRoom;
    protected int x;
    protected int y;
    protected boolean isPosibleGoLeft;
    protected boolean isPosibleGoRight;
    protected boolean isPosibleGoUp;
    protected boolean isPosibleGoDown;
    protected String description;
    protected boolean isUnlocekd;
    protected boolean isVisible;
    protected boolean wasThere;
    protected int nuberOfEnemies;
    protected int numberOfNPCs;
    protected int numberOfItems;
    protected int numberOfKeys;
    ArrayList<Enemy> listOfEnemies;
    ArrayList<NPC> listOfNPCs;
    ArrayList<Items> itemsList;
    ArrayList<Keys> keysList;


    public Room(String nameOfRoom, int x, int y, boolean isPosibleGoLeft, boolean isPosibleGoRight, boolean isPosibleGoUp, boolean isPosibleGoDown, String description, boolean isUnlocekd, boolean isVisible, boolean wasThere, int nuberOfEnemies, int numberOfNPCs, int numberOfItems, int numberOfKeys) {
        this.nameOfRoom = nameOfRoom;
        this.x = x;
        this.y = y;
        this.isPosibleGoLeft = isPosibleGoLeft;
        this.isPosibleGoRight = isPosibleGoRight;
        this.isPosibleGoUp = isPosibleGoUp;
        this.isPosibleGoDown = isPosibleGoDown;
        this.description = description;
        this.isUnlocekd = isUnlocekd;
        this.isVisible = isVisible;
        this.wasThere = wasThere;
        this.nuberOfEnemies = nuberOfEnemies;
        this.numberOfNPCs = numberOfNPCs;
        this.numberOfItems = numberOfItems;
        this.numberOfKeys = numberOfKeys;
        this.listOfEnemies = new ArrayList<>();
        this.listOfNPCs = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.keysList = new ArrayList<>();
    }

    public int getNuberOfEnemies() {
        return nuberOfEnemies;
    }

    public void setNuberOfEnemies(int nuberOfEnemies) {
        this.nuberOfEnemies = nuberOfEnemies;
    }

    public int getNumberOfNPCs() {
        return numberOfNPCs;
    }

    public void setNumberOfNPCs(int numberOfNPCs) {
        this.numberOfNPCs = numberOfNPCs;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPosibleGoLeft() {
        return isPosibleGoLeft;
    }

    public void setPosibleGoLeft(boolean posibleGoLeft) {
        isPosibleGoLeft = posibleGoLeft;
    }

    public boolean isPosibleGoRight() {
        return isPosibleGoRight;
    }

    public void setPosibleGoRight(boolean posibleGoRight) {
        isPosibleGoRight = posibleGoRight;
    }

    public boolean isPosibleGoUp() {
        return isPosibleGoUp;
    }

    public void setPosibleGoUp(boolean posibleGoUp) {
        isPosibleGoUp = posibleGoUp;
    }

    public boolean isPosibleGoDown() {
        return isPosibleGoDown;
    }

    public void setPosibleGoDown(boolean posibleGoDown) {
        isPosibleGoDown = posibleGoDown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUnlocekd() {
        return isUnlocekd;
    }

    public void setUnlocekd(boolean unlocekd) {
        isUnlocekd = unlocekd;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isWasThere() {
        return wasThere;
    }

    public void setWasThere(boolean wasThere) {
        this.wasThere = wasThere;
    }

    public ArrayList<Enemy> getListOfEnemies() {
        return listOfEnemies;
    }

    public void setListOfEnemies(ArrayList<Enemy> listOfEnemies) {
        this.listOfEnemies = listOfEnemies;
    }

    public ArrayList<NPC> getListOfNPCs() {
        return listOfNPCs;
    }

    public void setListOfNPCs(ArrayList<NPC> listOfNPCs) {
        this.listOfNPCs = listOfNPCs;
    }

    public ArrayList<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public ArrayList<Keys> getKeysList() {
        return keysList;
    }

    public void setKeysList(ArrayList<Keys> keysList) {
        this.keysList = keysList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "nameOfRoom='" + nameOfRoom + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", isPosibleGoLeft=" + isPosibleGoLeft +
                ", isPosibleGoRight=" + isPosibleGoRight +
                ", isPosibleGoUp=" + isPosibleGoUp +
                ", isPosibleGoDown=" + isPosibleGoDown +
                ", description='" + description + '\'' +
                ", isUnlocekd=" + isUnlocekd +
                ", isVisible=" + isVisible +
                ", wasThere=" + wasThere +
                ", nuberOfEnemies=" + nuberOfEnemies +
                ", numberOfNPCs=" + numberOfNPCs +
                ", numberOfItems=" + numberOfItems +
                ", numberOfKeys=" + numberOfKeys +
                ", listOfEnemies=" + listOfEnemies +
                ", listOfNPCs=" + listOfNPCs +
                ", itemsList=" + itemsList +
                ", keysList=" + keysList +
                '}';
    }
}
