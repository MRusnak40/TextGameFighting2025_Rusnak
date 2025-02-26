package MainGame;

import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    Scanner scanner = new Scanner(System.in);
ArrayList<Room>rooms = new ArrayList<>();
private String filepath="Rooms.txt" ;

public void game(){
GameLoadingData g = new GameLoadingData();
rooms=g.loadRoomsFromFile(filepath);

    System.out.println("---------------------------");
    // Check if rooms were loaded successfully
    if (rooms != null && !rooms.isEmpty()) {
        System.out.println("Rooms.txt loaded successfully. Number of rooms: " + rooms.size());
        // You can add further logic to start the game here
    } else {
        System.out.println("No rooms were loaded. Please check the file path and format.");
    }

}



public void play(){
 game();

    System.out.println("-------------");
    for(Room room : rooms){
        System.out.println(room);

    }

}





}
