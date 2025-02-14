package MainGame;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import NPCs.Enemy;
import Rooms.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    public Room loadRoom(String filepath) {
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
    }




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
