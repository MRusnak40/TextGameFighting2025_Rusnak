package MainGame;

import AtributesOfPlayer.Invertory;
import AtributesOfPlayer.PickUp;
import AtributesOfPlayer.SetCharacter;
import NPCs.Enemy;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiPredicate;

import Character.*;

public class Gameos {
    protected String direction;
    private String filepath = "Rooms.txt";
    ArrayList<Room> roomes = new ArrayList<>();
    Move move = new Move();
    Scanner scanner = new Scanner(System.in);

    Postava postava;
    SetCharacter lobby = new SetCharacter(postava);
    Choosing choose;


    private Boolean exit = false;
//loads gamne

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
        move.setCurrentRoom(move.rooms.getFirst());


    }

    //sets name and cahracter
    public void setCharacter() {
postava = lobby.setNameOfCharacter();
      choose  = new Choosing(move, postava);
    }

    //main loop
    public void gameLoop() {
        //loaded game
        loadGame();

        System.out.println("Game starts in");
        int count = 10;
        for (int i = 0; i < 10; i++) {
            System.out.println("   ");

            System.out.println("   ");
            System.out.println(count--);
            System.out.println("GOOD LUCK");
        }

        System.out.println("Hra byla spravne nactena ze souboru");
        System.out.println("------------------------");

        System.out.println("GAME HAS STARTED");
        System.out.println("HAVE A FUN");
        System.out.println("*******************************");
        //game starst here


        setCharacter();
        choose.inicializace();
        System.out.println("-+-+-+-+-+-+-+-+-");
        System.out.println("napis: help  aby jsi vedel prikazy");
        System.out.println("-+-+-+-+-+-+-+-+-");
        System.out.println("  ");
        System.out.println("Pocet dulezitich npc v msitnsoti:"+move.informationAboutTalking());
        System.out.println("Aktualni msitnsot:"+move.getCurrentRoom());
        System.out.println("----------------");
        do {


            choose.doCommand();
            exit = choose.exit;
        } while (!exit);
    }


}
