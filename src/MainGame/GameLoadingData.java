package MainGame;

import KeysAtributes.*;
import NPCs.*;
import Rooms.CombinationKeyForUnlock;
import Rooms.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLoadingData {
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Keys> keysArrayList = new ArrayList<>();
    ArrayList<NPC> NPCList = new ArrayList<>();
    ArrayList<Items> itemsArrayList = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<Room> loadRoomsFromFile(String filePath) {
        loadComponents();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] roomData = line.split(",");
                if (roomData.length < 15) {
                    System.out.println("Insufficient data for room: " + line);
                    continue;
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", "");
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);
                int nuberOfEnemies = Integer.parseInt(roomData[11]);
                int numberOfNPCs = Integer.parseInt(roomData[12]);
                int numberOfItems = Integer.parseInt(roomData[13]);
                int numberOfKeys = Integer.parseInt(roomData[14]);
                CombinationKeyForUnlock codeForUnlock= CombinationKeyForUnlock.valueOf(roomData[15].toUpperCase());
                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere, nuberOfEnemies, numberOfNPCs, numberOfItems, numberOfKeys,codeForUnlock);

                for (int i = 0; i < room.getNuberOfEnemies(); i++) {

                    room.getListOfEnemies().add(enemies.remove(0));

                }

                // Přidání NPC
                for (int i = 0; i < room.getNumberOfNPCs(); i++) {

                    room.getListOfNPCs().add(NPCList.remove(0));

                }

                // Přidání předmětů
                for (int i = 0; i < room.getNumberOfItems(); i++) {

                    room.getItemsList().add(itemsArrayList.remove(0));

                }

                // Přidání klíčů
                for (int i = 0; i < room.getNumberOfKeys(); i++) {

                    room.getKeysList().add(keysArrayList.remove(0));  // Oprava nesprávného seznamu

                }


                rooms.add(room);
            }

/*
            GameLoadingData gameLoadingData = new GameLoadingData();
            gameLoadingData.loadEnemies("FILESforTXt/Enemys.txt");
            System.out.println("*************************");
            System.out.println(gameLoadingData.enemies);
            System.out.println(gameLoadingData.enemies.size());
            System.out.println("*************************");

            gameLoadingData.loadKEys("FILESforTXt/Keyz.txt");
            System.out.println("*************************");
            System.out.println(gameLoadingData.keysArrayList);
            System.out.println(gameLoadingData.keysArrayList.size());
            System.out.println("*************************");

            gameLoadingData.loadNPCsFromFile("FILESforTXt/NPCs.txt");
            System.out.println("******************");
            System.out.println(gameLoadingData.NPCList);
            System.out.println(gameLoadingData.NPCList.size());
            System.out.println("*************************");

            gameLoadingData.loadITEMS("FILESforTXt/Items.txt");
            System.out.println("******************");
            System.out.println(gameLoadingData.itemsArrayList);
            System.out.println(gameLoadingData.itemsArrayList.size());
            System.out.println("*************************");



 */

//addingAtributes();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    //pridavani do arrylistu
    public void addingAtributes() {

        for (Room room : rooms) {
            // Přidání nepřátel
            for (int i = 0; i < room.getNuberOfEnemies(); i++) {
                if (!enemies.isEmpty()) {
                    room.getListOfEnemies().add(enemies.remove(0));
                }
            }

            // Přidání NPC
            for (int i = 0; i < room.getNumberOfNPCs(); i++) {
                if (!NPCList.isEmpty()) {
                    room.getListOfNPCs().add(NPCList.remove(0));
                }
            }

            // Přidání předmětů
            for (int i = 0; i < room.getNumberOfItems(); i++) {
                if (!itemsArrayList.isEmpty()) {
                    room.getItemsList().add(itemsArrayList.remove(0));
                }
            }

            // Přidání klíčů
            for (int i = 0; i < room.getNumberOfKeys(); i++) {
                if (!keysArrayList.isEmpty()) {
                    room.getKeysList().add(keysArrayList.remove(0));  // Oprava nesprávného seznamu
                }
            }


        }
    }


    public void loadComponents() {
        GameLoadingData gameLoadingData = new GameLoadingData();
        enemies = gameLoadingData.loadEnemies("FILESforTXt/Enemys.txt");
        System.out.println("*************************");
        System.out.println(gameLoadingData.enemies);
        System.out.println(gameLoadingData.enemies.size());
        System.out.println("*************************");

        keysArrayList = gameLoadingData.loadKEys("FILESforTXt/Keyz.txt");

        System.out.println("*************************");
        System.out.println(gameLoadingData.keysArrayList);
        System.out.println(gameLoadingData.keysArrayList.size());
        System.out.println("*************************");

        NPCList = gameLoadingData.loadNPCsFromFile("FILESforTXt/NPCs.txt");
        System.out.println("******************");
        System.out.println(gameLoadingData.NPCList);
        System.out.println(gameLoadingData.NPCList.size());
        System.out.println("*************************");

        itemsArrayList = gameLoadingData.loadITEMS("FILESforTXt/Items.txt");
        System.out.println("******************");
        System.out.println(gameLoadingData.itemsArrayList);
        System.out.println(gameLoadingData.itemsArrayList.size());
        System.out.println("*************************");


        System.out.println(" ");

    }

    public ArrayList<Items> loadITEMS(String filePath) {

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] attributes = line.split(",");

                if (attributes.length < 7) {
                    System.out.println("Insufficient data for item: " + line);
                    continue;
                }

                try {
                    Items item = new Items(
                            attributes[0],
                            Double.parseDouble(attributes[1]),
                            Double.parseDouble(attributes[2]),
                            Double.parseDouble(attributes[3]),
                            Double.parseDouble(attributes[4]),
                            Double.parseDouble(attributes[5]),
                            Boolean.parseBoolean(attributes[6])
                    );

                    itemsArrayList.add(item);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return itemsArrayList;

    }


    public ArrayList<NPC> loadNPCsFromFile(String filePath) {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] enemyData = line.split(",");

                if (enemyData.length < 6) {
                    System.out.println("Insufficient data for NPC: " + line);
                    continue;
                }

                try {
                    NPCEnum type = NPCEnum.valueOf(enemyData[0].toUpperCase());
                    NPC npc = null;

                    switch (type) {
                        case VILLIGER:
                            npc = new Villiger(type, enemyData[1], enemyData[2], enemyData[3], Boolean.parseBoolean(enemyData[4]), Boolean.parseBoolean(enemyData[5]));
                            break;
                        case WANDERER:
                            npc = new Wanderer(type, enemyData[1], enemyData[2], enemyData[3], Boolean.parseBoolean(enemyData[4]), Boolean.parseBoolean(enemyData[5]));
                            break;
                        case FARMER:
                            npc = new Farmer(type, enemyData[1], enemyData[2], enemyData[3], Boolean.parseBoolean(enemyData[4]), Boolean.parseBoolean(enemyData[5]));
                            break;
                        case GUARD:
                            npc = new Guard(type, enemyData[1], enemyData[2], enemyData[3], Boolean.parseBoolean(enemyData[4]), Boolean.parseBoolean(enemyData[5]));
                            break;
                        default:
                            System.out.println("Unknown NPC type: " + enemyData[0]);
                            break;
                    }

                    if (npc != null) {
                        NPCList.add(npc);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid NPC type or data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return NPCList;
    }


    public ArrayList<Enemy> loadEnemies(String filePath) {


        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] enemyData = line.split(",");

                if (enemyData.length < 7) {
                    System.out.println("Insufficient data for enemy: " + line);
                    continue;
                }

                try {
                    // Parse enemy type
                    EnemyEnum type = EnemyEnum.valueOf(enemyData[0].toUpperCase());

                    // Create enemy based on type
                    Enemy enemy = null;
                    switch (type) {
                        case SHADOW:
                            enemy = new Shadow(
                                    type,
                                    enemyData[1],
                                    Double.parseDouble(enemyData[2]),
                                    Double.parseDouble(enemyData[3]),
                                    Double.parseDouble(enemyData[4]),
                                    Double.parseDouble(enemyData[5]),
                                    Integer.parseInt(enemyData[6]),
                                    Double.parseDouble(enemyData[7])
                            );
                            break;
                        case DARKKNIGHT:
                            enemy = new DarkKnight(
                                    type,
                                    enemyData[1],
                                    Double.parseDouble(enemyData[2]),
                                    Double.parseDouble(enemyData[3]),
                                    Double.parseDouble(enemyData[4]),
                                    Double.parseDouble(enemyData[5]),
                                    Integer.parseInt(enemyData[6]),
                                    Double.parseDouble(enemyData[7])

                            );
                            break;
                        default:
                            System.out.println("Unknown enemy type: " + enemyData[0]);
                            break;
                    }

                    if (enemy != null) {
                        enemies.add(enemy);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid enemy type or data format in line: " + line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return enemies;
    }


    public ArrayList<Keys> loadKEys(String filePath) {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] attributes = line.split(",");

                if (attributes.length < 6) {
                    System.out.println("Insufficient data for key: " + line);
                    continue;
                }

                try {
                    KeyEnum type = KeyEnum.valueOf(attributes[0].toUpperCase());
                    Keys key = null;

                    switch (type) {
                        case MAP:
                            key = new Map(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]),CombinationKeyForUnlock.valueOf(attributes[6].toUpperCase()));
                            break;
                        case PAINTING:
                            key = new Painting(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]),CombinationKeyForUnlock.valueOf(attributes[6].toUpperCase()));
                            break;
                        case KEY:
                            key = new Key(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]),CombinationKeyForUnlock.valueOf(attributes[6].toUpperCase()));
                            break;
                        default:
                            System.out.println("Unknown key type: " + attributes[0]);
                            break;
                    }

                    if (key != null) {
                        keysArrayList.add(key);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid key type or data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return keysArrayList;

    }


    public static StringBuilder loadStory() {
        StringBuilder story = new StringBuilder();


        return story;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Keys> getKeysArrayList() {
        return keysArrayList;
    }

    public void setKeysArrayList(ArrayList<Keys> keysArrayList) {
        this.keysArrayList = keysArrayList;
    }

    public ArrayList<NPC> getNPCList() {
        return NPCList;
    }

    public void setNPCList(ArrayList<NPC> NPCList) {
        this.NPCList = NPCList;
    }

    public ArrayList<Items> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}