package MainGame;

import AtributesOfPlayer.SetCharacter;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class Gameos {
    protected String direction;
    private String filepath = "Rooms.txt";
    ArrayList<Room> roomes = new ArrayList<>();
    Move move = new Move();
    Scanner scanner = new Scanner(System.in);


    public void loadGame() {

        GameLoadingData g = new GameLoadingData();
        roomes = g.loadRoomsFromFile(filepath);
        move.rooms = roomes;
        System.out.println("---------------------------");
        // Check if rooms were loaded successfully
        if (move.rooms != null && !move.rooms.isEmpty()) {
            System.out.println("Rooms.txt loaded successfully. Number of rooms: " + move.rooms.size());
            // You can add further logic to start the game here
        } else {
            System.out.println("No rooms were loaded. Please check the file path and format.");
        }
        move.setCurrentRoom(move.rooms.get(0));


    }

    public void setCharacter() {
        SetCharacter lobby = new SetCharacter();
        lobby.setNameOfCharacter();
    }


    public void gameLoop() {
        loadGame();

        for (int i = 0; i < 10; i++) {
            System.out.println("   ");
        }
        System.out.println("Hra byla spravne nactena ze souboru");
        System.out.println("------------------------");

        System.out.println("GAME HAS STARTED");
        setCharacter();
        while(move.isMoved==false) {
            move.execute();
        }
    }


}
