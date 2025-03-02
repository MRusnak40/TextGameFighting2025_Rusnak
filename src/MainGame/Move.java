package MainGame;

import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Move extends Command{
    private Room currentRoom;
    private int curretX = 0;
    private int curretY = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Room> rooms = new ArrayList<>();
    //private String filepath = "Rooms.txt";

    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }



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

        System.out.println("-------------");
        for (Room room : rooms) {
            System.out.println(room);
        }

        currentRoom = rooms.get(0);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        while (true) {
            System.out.println("Kam chces jit ");
            setDirection(scanner.nextLine());
            System.out.println(currentRoom);
        }
    }


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
        }
    }





    public boolean controllingPossibilites() {
        int index = 0;
        for (Room room : rooms) {
            index++;


            if (curretX == room.getX() && curretY == room.getY()) {

                if (room.isVisible()) {

                    if (room.isUnlocekd()) {
                        index--;
                        System.out.println("-------------------------");
                        System.out.println("PROSEL JSI DO DALSI MISTNOSTI");
                        System.out.println("-------------------------");
                        currentRoom = rooms.get(index);
                        curretX = room.getX();
                        curretY = room.getY();
                        return true;

                    } else {
                        System.out.println("-------------------------");
                        System.out.println("Room je zamcena");
                        System.out.println("-------------------------");
                        return false;
                    }


                } else {
                    System.out.println("-------------------------");
                    System.out.println("Mistnost neexistuje nebo je neviditelna na souradnicich " + curretX + "," + curretY);
                    System.out.println("-------------------------");
                    return false;
                }


            } else {

            }

        }

        return false;
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


