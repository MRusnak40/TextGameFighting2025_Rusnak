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
    public ArrayList<Room> roomes = new ArrayList<>();
    Move move = new Move();
    Scanner scanner = new Scanner(System.in);

    Postava postava;
    SetCharacter lobby = new SetCharacter(postava);
    Choosing choose;


    private Boolean exit = false;
//loads gamne

    /**
     * loads the game Enemies Rooms items keys
     */
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
        choose = new Choosing(move, postava);
    }


    /**
     *
     *main loop where everyting working together
     */
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
        System.out.println("Pocet dulezitich npc v msitnsoti:" + move.informationAboutTalking());
        System.out.println("Aktualni msitnsot:" + move.getCurrentRoom());
        System.out.println("----------------");
        do {

            endOfGame();

            if (!exit) {
                choose.doCommand();
                exit = choose.exit;
            }


        } while (!exit);
    }

    public void endOfGame() {
        int count = 0;
        for (Room r : move.getRooms()) {
            if (r.isWasThere()) {
                count++;
            }


        }
        if (count == move.getRooms().size() && move.getCurrentRoom() == move.getRooms().getFirst()) {
            for (int i = 0; i < 5; i++) {
                System.out.println("  ");
                System.out.println("  ");
                System.out.println("  ");
            }
            System.out.println("Super Dohral jsi hru");
            System.out.println("tvoje cesta je u konce");
            System.out.println("       ");
            System.out.println("  💪😎🤌            ");
            System.out.println("         ");
            exit = true;
            Exit exit1 = new Exit();
            exit1.execute();


        }

    }

    /**
     *
     * @param income-  what you wanna test
     * @param max-max zou put on income
     * @param minimum-minimum what you can put in income
     * @return the income if is ok
     */
    public static boolean isIncomeBigEnough(int income, int max, int minimum) {

        if (income > max) {
            return false;
        } else if (income < minimum) {
            return false;
        } else if (income >= minimum && income <= max) {
            return true;
        } else {
            return false;
        }
    }

    public static int isIncomeGoodWithScanner(int max, int minimum) {
        Scanner scanner = new Scanner(System.in);
        int income;
        boolean isOkIncome = false;
        while (!isOkIncome) {
            try {
                income = scanner.nextInt();
                boolean isOutOfBounds = Gameos.isIncomeBigEnough(income, max, minimum);
                if (isOutOfBounds) {
                    isOkIncome = true;
                    return income;
                } else {
                    isOkIncome = false;
                    System.out.println(" ");
                    System.out.println("Vstup je pres velikost vyberu");
                    System.out.println(" ");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println(" ");
                System.out.println("spatny vstup");
                System.out.println(" ");
                isOkIncome = false;
                scanner.nextLine();
            }

        }

        return 0;

    }

}

