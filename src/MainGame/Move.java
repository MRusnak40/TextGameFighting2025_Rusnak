package MainGame;

import AtributesOfPlayer.TalkToNPC;
import NPCs.NPC;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Move extends Command {
    private Room currentRoom;
    private int curretX = 0;
    private int curretY = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Room> rooms = new ArrayList<>();
    //private String filepath = "Rooms.txt";
    public boolean isMoved;

    @Override
    public String execute() {
        moving();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
/*
    public Move() {

        this.rooms=new ArrayList<>();
    }

 */

   /*
    public void game() {
        GameLoadingData g = new GameLoadingData();
        rooms = g.loadRoomsFromFile(filepath);

        System.out.println("---------------------------");
        // Check if rooms were loaded successfully
        if (rooms != null && !rooms.isEmpty()) {
            System.out.println("Rooms.txt loaded successfully. Number of rooms: " + rooms.size());
            // You can add further logic to start the game here
        } else {
            System.out.println("No rooms were loaded. Please check the file path and format.");
        }

    }


 */

    public void moving() {
        // game();
/*
        System.out.println("-------------");
        for (Room room : rooms) {
            System.out.println(room);
        }


 */


        System.out.println(" ");
        System.out.println("AKTUALNI MISTNOST:");
        System.out.println(getCurrentRoom());
        //sem priadat npc talk
        System.out.println("----------------→♫♫♫♫");
        System.out.println("Pocet dulezitych npc v mistnsoti:" + informationAboutTalking());
        System.out.println("-----------");
        isMoved = false;
        //is it nessesary?
        while (!isMoved) {

            System.out.println("Kam chces jit ↓,↑,→,←,: ");

            setDirection(scanner.next());
            scanner.nextLine();
        }

        System.out.println(getCurrentRoom());


    }

    /**
     * sets new coridores and new current room
     * @param direction- what player puts in
     */
    public void setDirection(String direction) {


        int prevX = curretX;
        int prevY = curretY;

        switch (direction.toLowerCase()) {
            case "left":
                curretX--;
                break;
            case "right":
                curretX++;
                break;
            case "up":
                curretY++;
                break;
            case "down":
                curretY--;
                break;
            default:
                System.out.println("Neplatný směr!");

                return;
        }

        if (!controllingPossibilites()) {
            curretX = prevX;
            curretY = prevY;
            System.out.println("ZKUS JINAM TUDY NE!");
            System.out.println("   ");
            isMoved = false;
        } else {
            isMoved = true;
        }

    }

    /**
     * tests if you can move on that position
     * @return false if you didnt moved
     */
    public boolean controllingPossibilites() {
        int index = 0;
        int counting = 0;
        for (Room room : rooms) {
            index++;
            counting++;

            if (curretX == room.getX() && curretY == room.getY()) {

                if (room.isVisible()) {

                    if (room.isUnlocekd()) {
                        index--;
                        System.out.println("♠♠♠----------♠♠♠");
                        System.out.println("PROSEL JSI DO DALSI MISTNOSTI");
                        System.out.println("♠♠♠----------♠♠♠");

                        currentRoom = rooms.get(index);
                        curretX = room.getX();
                        curretY = room.getY();
                        //potrebne sem pridat mluveni s npc
                        currentRoom.setWasThere(true);
                        System.out.println("-----------------");
                        System.out.println("Pocet dulezitych npc v mistnsoti:" + informationAboutTalking());
                        System.out.println("-----------");


                        return true;

                    } else {
                        System.out.println("↓↓↓↓↓↓↓↓↓↓↓");
                        System.out.println("Room je zamcena");
                        System.out.println("-------------------------");
                        return false;
                    }


                } else {
                    System.out.println("↓↓↓↓↓↓↓↓↓↓↓");
                    System.out.println("Mistnost neexistuje nebo je neviditelna na souradnicich " + curretX + "," + curretY);
                    System.out.println("-------------------------");
                    return false;
                }


            }


        }

        return false;
    }




    //pocet dulzitych npcs
    public int informationAboutTalking() {
        int counting = 0;


        for (NPC npc : currentRoom.getListOfNPCs()) {

            if (npc.isImportant() ) {
                counting++;
            }

        }

        return counting;
    }



    /*
public int informationAboutTalking() {
    int counting = 0;

    if (currentRoom != null && currentRoom.getListOfNPCs() != null) {
        for (NPC npc : currentRoom.getListOfNPCs()) {
            if (npc.isImportant() && !npc.isSpoken()) {
                counting++;
            }
        }
    }

    return counting;
}


     */
    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCurretX() {
        return curretX;
    }

    public void setCurretX(int curretX) {
        this.curretX = curretX;
    }

    public int getCurretY() {
        return curretY;
    }

    public void setCurretY(int curretY) {
        this.curretY = curretY;
    }


    @Override
    public String toString() {
        return "Move{" +
                "curretY=" + curretY +
                ", curretX=" + curretX +
                ", currentRoom=" + currentRoom +
                '}';
    }


}


