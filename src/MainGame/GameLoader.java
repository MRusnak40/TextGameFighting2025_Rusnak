package MainGame;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import NPCs.Enemy;
import Rooms.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public  class GameLoader {

/*
    public Enemy loaderEnemy() {


        return Enemy;
    }


    public Keys keysLoader() {

        return Keys;
    }

    public Items itemsLoader() {
    }


 */

    public static ArrayList<Room> loadRoomsFromTXT(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Přeskočit první řádek s hlavičkou
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Parsování jednotlivých hodnot
                String nameOfRoom = values[0];
                int x = Integer.parseInt(values[1]);
                int y = Integer.parseInt(values[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(values[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(values[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(values[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(values[6]);
                String description = values[7].replace("\"", ""); // Odstranění uvozovek
                boolean isUnlocked = Boolean.parseBoolean(values[8]);
                boolean isVisible = Boolean.parseBoolean(values[9]);
boolean wasThere=Boolean.parseBoolean(values[10]);









                // Vytvoření místnosti a přidání do seznamu
                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible,wasThere);
                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

        /*
        Room room = new Room();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            room.setNameOfRoom(br.readLine()); // Načtení názvu místnosti
            room.setUnlocekd(Boolean.parseBoolean(br.readLine())); // Načtení stavu odemčení
            room.setVisible(Boolean.parseBoolean(br.readLine())); // Načtení stavu viditelnosti

            // Načtení sousedních místností
            Room leftRoom = new Room();
            leftRoom.setNameOfRoom(br.readLine()); // Načtení názvu levé místnosti
            room.setLeftRoom(leftRoom);

            Room rightRoom = new Room();
            rightRoom.setNameOfRoom(br.readLine()); // Načtení názvu pravé místnosti
            room.setRightRoom(rightRoom);

            Room downRoom = new Room();
            downRoom.setNameOfRoom(br.readLine()); // Načtení názvu dolní místnosti
            room.setDownRoom(downRoom);

            Room upRoom = new Room();
            upRoom.setNameOfRoom(br.readLine()); // Načtení názvu horní místnosti
            room.setUpRoom(upRoom);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return room; // Vrátí načtenou místnost

         */





/*

    ArrayList<Room> rooms = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
        for (int i = 0; i < 9; i++) { // Načtení 9 místností
            Room room = new Room();
            room.setNameOfRoom(br.readLine()); // Načtení názvu místnosti
            room.setUnlocekd(Boolean.parseBoolean(br.readLine())); // Načtení stavu odemčení
            room.setVisible(Boolean.parseBoolean(br.readLine())); // Načtení stavu viditelnosti

            // Načtení sousedních místností
            room.setLeftRoom(new Room(br.readLine()));
            room.setRightRoom(new Room(br.readLine()));
            room.setDownRoom(new Room(br.readLine()));
            room.setUpRoom(new Room(br.readLine()));

            // Načtení seznamu nepřátel
            int enemyCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < enemyCount; j++) {
                // Předpokládáme, že Enemy má konstruktor, který přijímá potřebné parametry
                Enemy enemy = new Enemy(br.readLine()); // Načtení nepřítele
                room.getListOfEnemies().add(enemy);
            }

            // Načtení seznamu NPC
            int npcCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < npcCount; j++) {
                // Předpokládáme, že NPC má konstruktor, který přijímá potřebné parametry
                NPC npc = new NPC(br.readLine()); // Načtení NPC
                room.getListOfNPCs().add(npc);
            }

            // Načtení seznamu položek
            int itemCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < itemCount; j++) {
                // Předpokládáme, že Items má konstruktor, který přijímá potřebné parametry
                Items item = new Items(br.readLine()); // Načtení položky
                room.getItemsList().add(item);
            }

            // Načtení seznamu klíčů
            int keyCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < keyCount; j++) {
                // Předpokládáme, že Keys má konstruktor, který přijímá potřebné parametry
                Keys key = new Keys(br.readLine()); // Načtení klíče
                room.getKeysList().add(key);
            }

            rooms.add(room); // Přidání místnosti do seznamu
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

        return rooms; // Vrátí seznam načtených místností
}



 */






}
