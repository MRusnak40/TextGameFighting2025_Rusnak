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
    ArrayList<Enemy> listOfEnemies= new ArrayList<>();
    ArrayList<NPC> listOfNPCs= new ArrayList<>();
    ArrayList<Items> itemsList= new ArrayList<>();
    ArrayList<Keys> keysList= new ArrayList<>();


    public Room(String nameOfRoom, int x, int y, boolean isPosibleGoLeft, boolean isPosibleGoRight, boolean isPosibleGoUp, boolean isPosibleGoDown, String description, boolean isUnlocekd, boolean isVisible, boolean wasThere) {
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
                ", listOfEnemies=" + listOfEnemies +
                ", listOfNPCs=" + listOfNPCs +
                ", itemsList=" + itemsList +
                ", keysList=" + keysList +
                '}';
    }
}
