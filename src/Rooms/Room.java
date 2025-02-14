package Rooms;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import NPCs.Enemy;
import NPCs.NPC;

import java.util.ArrayList;

public class Room {
    protected String nameOfRoom;
    ArrayList<Enemy> listOfEnemies= new ArrayList<>();
    ArrayList<NPC> listOfNPCs= new ArrayList<>();
    ArrayList<Items> itemsList= new ArrayList<>();
    ArrayList<Keys> keysList= new ArrayList<>();
    protected Room leftRoom;
    protected Room rightRoom;
    protected Room downRoom;
    protected Room upRoom;
    protected boolean isUnlocekd;
    protected boolean isVisible;


    public Room(String nameOfRoom, Room leftRoom, Room rightRoom, Room downRoom, Room upRoom, boolean isUnlocekd, boolean isVisible) {
        this.nameOfRoom = nameOfRoom;
        this.leftRoom = leftRoom;
        this.rightRoom = rightRoom;
        this.downRoom = downRoom;
        this.upRoom = upRoom;
        this.isUnlocekd = isUnlocekd;
        this.isVisible = isVisible;


    }

    public Room() {
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
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

    public Room getLeftRoom() {
        return leftRoom;
    }

    public void setLeftRoom(Room leftRoom) {
        leftRoom = leftRoom;
    }

    public Room getRightRoom() {
        return rightRoom;
    }

    public void setRightRoom(Room rightRoom) {
        rightRoom = rightRoom;
    }

    public Room getDownRoom() {
        return downRoom;
    }

    public void setDownRoom(Room downRoom) {
        this.downRoom = downRoom;
    }

    public Room getUpRoom() {
        return upRoom;
    }

    public void setUpRoom(Room upRoom) {
        this.upRoom = upRoom;
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

    @Override
    public String toString() {
        return "Room{" +
                "nameOfRoom='" + nameOfRoom + '\'' +
                ", listOfEnemies=" + listOfEnemies +
                ", listOfNPCs=" + listOfNPCs +
                ", itemsList=" + itemsList +
                ", keysList=" + keysList +
                ", leftRoom=" + leftRoom +
                ", rightRoom=" + rightRoom +
                ", downRoom=" + downRoom +
                ", upRoom=" + upRoom +
                ", isUnlocekd=" + isUnlocekd +
                ", isVisible=" + isVisible +
                '}';
    }
}
