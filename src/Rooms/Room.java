package Rooms;

import NPCs.Enemy;
import NPCs.NPC;

import java.util.ArrayList;

public class Room {
    protected String nameOfRoom;
    ArrayList<Enemy> listOfEnemies;
    ArrayList<NPC> listOfNPCs;
   protected Room LeftRoom;
   protected Room RightRoom;
   protected Room DownRoom;
   protected Room UpRoom;

    public Room(String nameOfRoom, ArrayList<Enemy> listOfEnemies, ArrayList<NPC> listOfNPCs, Room leftRoom, Room rightRoom, Room downRoom, Room upRoom) {
        this.nameOfRoom = nameOfRoom;
        this.listOfEnemies = listOfEnemies;
        this.listOfNPCs = listOfNPCs;
        LeftRoom = leftRoom;
        RightRoom = rightRoom;
        DownRoom = downRoom;
        UpRoom = upRoom;
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
        return LeftRoom;
    }

    public void setLeftRoom(Room leftRoom) {
        LeftRoom = leftRoom;
    }

    public Room getRightRoom() {
        return RightRoom;
    }

    public void setRightRoom(Room rightRoom) {
        RightRoom = rightRoom;
    }

    public Room getDownRoom() {
        return DownRoom;
    }

    public void setDownRoom(Room downRoom) {
        DownRoom = downRoom;
    }

    public Room getUpRoom() {
        return UpRoom;
    }

    public void setUpRoom(Room upRoom) {
        UpRoom = upRoom;
    }
}
