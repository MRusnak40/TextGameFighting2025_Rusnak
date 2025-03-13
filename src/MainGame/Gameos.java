package MainGame;

import AtributesOfPlayer.Invertory;
import AtributesOfPlayer.PickUp;
import AtributesOfPlayer.SetCharacter;
import NPCs.Enemy;
import Rooms.Room;
import java.util.ArrayList;
import java.util.Scanner;
import Character.*;
public class Gameos {
    protected String direction;
    private String filepath = "Rooms.txt";
    ArrayList<Room> roomes = new ArrayList<>();
    Move move = new Move();
    Scanner scanner = new Scanner(System.in);
    Postava postava;
    SetCharacter lobby = new SetCharacter(postava);
    Enemy enemy;
    Choosing choose = new Choosing(move ,postava);



    public void loadGame() {
        choose.inicializace();
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
        move.setCurrentRoom(move.rooms.getFirst());


    }

    public void setCharacter() {

        postava=lobby.setNameOfCharacter();
    }


    public void gameLoop() {
        loadGame();
        System.out.println("Game starts in");
        int count=10;
        for (int i = 0; i < 10; i++) {
            System.out.println("   ");
            System.out.println("GOOD LUCK");
            System.out.println("   ");
            System.out.println(count--);

        }
        System.out.println("Hra byla spravne nactena ze souboru");
        System.out.println("------------------------");

        System.out.println("GAME HAS STARTED");
        System.out.println("HAVE A FUN");
        System.out.println("*******************************");
        setCharacter();

        choose.start();
    }


}
