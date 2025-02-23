package MainGame;

import KeysAtributes.*;
import NPCs.*;
import Rooms.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLoader {


    public static ArrayList<Room> loadRoomsFromF(String path) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {

                String[] roomData = line.split(",");
                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);


                String enemiesData = roomData[11]; // Předpokládáme, že toto je správný index pro nepřátele


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile001(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Přeskočit prázdné řádky
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",", -1); // Použití záporné hodnoty pro split zajišťuje, že prázdné položky nejsou ztraceny
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Zkontrolujte, zda má roomData dostatek prvků
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                }

                // Načtení základních dat místnosti
                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Načíst nepřátele
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Předpokládáme, že toto je správný index pro nepřátele
                    if (!"null".equals(enemiesData)) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String enemyType = enemyAttributes[0].trim(); // Odstranit mezery
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            try {
                                switch (enemyType) {
                                    case "Shadow":
                                        enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                        break;

                                    case "DarkKnight": // Odstranit mezery
                                        enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                        break;

                                    // Přidat případy pro další typy nepřátel
                                    default:
                                        System.out.println("Unknown enemy type: " + enemyType);
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Error creating enemy: " + e.getMessage());
                            }

                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Načíst NPC
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Předpokládáme, že toto je správný index pro NPC
                    if (!"null".equals(npcsData)) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String npcType = npcAttributes[0].trim(); // Odstranit mezery
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // Nová vlastnost
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            try {
                                switch (npcType) {
                                    case "Villager":
                                        npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                        break;
                                    case "Guard":
                                        npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                        break;
                                    // Přidat případy pro další typy NPC
                                    default:
                                        System.out.println("Unknown NPC type: " + npcType);
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Error creating NPC: " + e.getMessage());
                            }

                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Přidat NPC do seznamu NPC místnosti
                            }
                        }
                    }
                }

                // Načíst položky
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Předpokládáme, že toto je správný index pro položky
                    if (!"null".equals(itemsData)) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Načíst klíče
                if (roomData.length > 14) {
                    String keysData = roomData[14]; // Předpokládáme, že toto je správný index pro klíče

                    if (!"null".equals(keysData)) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String keyType = keyAttributes[0].trim(); // Odstranit mezery
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            try {
                                switch (keyType) {
                                    case "Map":
                                        key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                        break;
                                    case "Painting":
                                        key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                        break;
                                    case "Key":
                                        key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                        break;
                                    // Přidat případy pro další typy klíčů
                                    default:
                                        System.out.println("Unknown key type: " + keyType);
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Error creating key: " + e.getMessage());
                            }

                            if (key != null) {
                                room.getKeysList().add(key); // Přidat klíč do seznamu klíčů místnosti
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


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
/*
    public static ArrayList<Room> loadRoomsFromTXT(String filePath) {

        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] roomData = line.split(",");
                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Remove quotes
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Load enemies
                String[] enemiesData = roomData[11].split(";");
                for (String enemyData : enemiesData) {
                    String[] enemyAttributes = enemyData.split(",");
                    String enemyName = enemyAttributes[0];
                    double enemySila = Double.parseDouble(enemyAttributes[1]);
                    double enemyInteligence = Double.parseDouble(enemyAttributes[2]);
                    double enemyObratnost = Double.parseDouble(enemyAttributes[3]);
                    double enemyOdolnost = Double.parseDouble(enemyAttributes[4]);
                    int enemyLevel = Integer.parseInt(enemyAttributes[5]);
                    Enemy enemy = new Enemy(enemyName, enemySila, enemyInteligence, enemyObratnost, enemyOdolnost, enemyLevel);
                    room.getListOfEnemies().add(enemy);
                }

                // Load NPCs
                String[] npcsData = roomData[12].split(";");
                for (String npcData : npcsData) {
                    String[] npcAttributes = npcData.split(",");
                    String npcName = npcAttributes[0];
                    String npcDialog = npcAttributes[1];
                    NPC npc = new NPC(npcName, npcDialog);
                    room.getListOfNPCs().add(npc);
                }

                // Load items
                String[] itemsData = roomData[13].split(";");
                for (String itemData : itemsData) {
                    String[] itemAttributes = itemData.split(",");
                    String itemName = itemAttributes[0];
                    double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                    double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                    double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                    double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                    double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                    boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                    Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                    room.getItemsList().add(item);
                }

                // Load keys
                String[] keysData = roomData[14].split(";");
                for (String keyData : keysData) {
                    String[] keyAttributes = keyData.split(",");
                    String keyName = keyAttributes[0];
                    boolean keyMovable = Boolean.parseBoolean(keyAttributes[1]);
                    boolean keyVisible = Boolean.parseBoolean(keyAttributes[2]);
                    boolean keyBroken = Boolean.parseBoolean(keyAttributes[3]);
                    boolean keyUsed = Boolean.parseBoolean(keyAttributes[4]);
                    Key key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                    room.getKeysList().add(key);
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


 */
    /*
    public static ArrayList<Room> loadRoomsFromFile(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
               // String[] roomData = line.split(",");
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Check if roomData has enough elements
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Skip this iteration if data is insufficient
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Remove quotes
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Load enemies
                String enemiesData = roomData[11]; // Assuming this is the correct index for enemies
                if (!enemiesData.equals("null")) {
                    String[] enemiesArray = enemiesData.split(";");
                    for (String enemyData : enemiesArray) {
                        String[] enemyAttributes = enemyData.split(":");

                        if (enemyAttributes.length < 2) {
                            System.out.println("Insufficient data for enemy: " + enemyData);
                            continue; // Skip this iteration if data is insufficient
                        }
                        String enemyType = enemyAttributes[0];
                        String[] attributes = enemyAttributes[1].split(",");

                        Enemy enemy = null;
                        switch (enemyType) {
                            case "Shadow":
                                enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                break;

                            case " DarkKnight":
                                enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));


                                // Add cases for other enemy types here
                            default:
                                break;
                        }
                        if (enemy != null) {
                            room.getListOfEnemies().add(enemy);
                        }
                    }
                }

                // Load NPCs
                String npcsData = roomData[12]; // Assuming this is the correct index for NPCs
                if (!npcsData.equals("null")) {
                    String[] npcsArray = npcsData.split(";");
                    for (String npcData : npcsArray) {
                        String[] npcAttributes = npcData.split(":");

                        if (npcAttributes.length < 6) {
                            System.out.println("Insufficient data for NPC: " + npcData);
                            continue; // Skip this iteration if data is insufficient
                        }
                        String npcType = npcAttributes[0];
                        String npcName = npcAttributes[1];
                        String npcDialog = npcAttributes[2];
                        String npcWelcomeText = npcAttributes[3]; // New property
                        boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                        boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                        NPC npc = null;
                        switch (npcType) {
                            case "Villager":
                                npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                break;
                            case "Guard":
                                npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                break;
                            // Add cases for other NPC types here
                            default:
                                break;
                        }
                        if (npc != null) {
                            room.getListOfNPCs().add(npc); // Add the NPC to the room's NPC list
                        }
                    }
                }

                // Load items
                String itemsData = roomData[13]; // Assuming this is the correct index for items
                if (!itemsData.equals("null")) {
                    String[] itemsArray = itemsData.split(";");
                    for (String itemData : itemsArray) {
                        String[] itemAttributes = itemData.split(",");

                        if (itemAttributes.length < 7) {
                            System.out.println("Insufficient data for item: " + itemData);
                            continue; // Skip this iteration if data is insufficient
                        }

                        String itemName = itemAttributes[0];
                        double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                        double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                        double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                        double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                        double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                        boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                        Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                        room.getItemsList().add(item);
                    }
                }

                // Load keys
                String keysData = roomData[14]; // Assuming this is the correct index for keys
                if (!keysData.equals("null")) {
                    String[] keysArray = keysData.split(";");
                    for (String keyData : keysArray) {
                        String[] keyAttributes = keyData.split(":");

                        if (keyAttributes.length < 6) {
                            System.out.println("Insufficient data for item: " + keyData);
                            continue; // Skip this iteration if data is insufficient
                        }

                        String keyType = keyAttributes[0];
                        String keyName = keyAttributes[1];

                        boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                        boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                        boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                        boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                        Keys key = null;
                        switch (keyType) {
                            case "Map":
                                key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                break;
                            case "Painting":
                                key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                break;

                            case"Key":
                                key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                            // Add cases for other key types here
                            default:
                                break;
                        }
                        if (key != null) {
                            room.getKeysList().add(key); // Add the key to the room's key list
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;

    }






    public static ArrayList<Room> loadRoomsFromFiles(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Check if roomData has enough elements
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Skip this iteration if data is insufficient
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Remove quotes
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Load enemies
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Assuming this is the correct index for enemies
                    if (!enemiesData.equals("null")) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Skip this iteration if data is insufficient
                            }
                            String enemyType = enemyAttributes[0];
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType.trim()) { // Use trim() to avoid leading/trailing spaces
                                case "Shadow":
                                    enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                case "DarkKnight": // Removed leading space
                                    enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                // Add cases for other enemy types here
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }
                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Load NPCs
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Assuming this is the correct index for NPCs
                    if (!npcsData.equals("null")) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Skip this iteration if data is insufficient
                            }
                            String npcType = npcAttributes[0];
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // New property
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            switch (npcType.trim()) { // Use trim() to avoid leading/trailing spaces
                                case "Villager":
                                    npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                case "Guard":
                                    npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                // Add cases for other NPC types here
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }
                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Add the NPC to the room's NPC list
                            }
                        }
                    }
                }

                // Load items
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Assuming this is the correct index for items
                    if (!itemsData.equals("null")) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Skip this iteration if data is insufficient
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Load keys
                if (roomData.length > 14) {
                    String keysData = roomData[14]; // Assuming this is the correct index for keys
                    if (!keysData.equals("null")) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Skip this iteration if data is insufficient
                            }

                            String keyType = keyAttributes[0];
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            switch (keyType.trim()) { // Use trim() to avoid leading/trailing spaces
                                case "Map":
                                    key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Painting":
                                    key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Key":
                                    key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                // Add cases for other key types here
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }
                            if (key != null) {
                                room.getKeysList().add(key); // Add the key to the room's key list
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
*//*
    public static ArrayList<Room> loadRoomsFromFile04(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Přeskočit prázdné řádky
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Zkontrolujte, zda má roomData dostatek prvků
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Načíst nepřátele
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Předpokládáme, že toto je správný index pro nepřátele
                    if (!enemiesData.equals("null")) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String enemyType = enemyAttributes[0].trim(); // Odstranit mezery
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType) {
                                case "Shadow":
                                    enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                case "DarkKnight": // Odstranit mezery
                                    enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                // Přidat případy pro další typy nepřátel
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }
                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Načíst NPC
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Předpokládáme, že toto je správný index pro NPC
                    if (!npcsData.equals("null")) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String npcType = npcAttributes[0].trim(); // Odstranit mezery
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // Nová vlastnost
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            switch (npcType) {
                                case "Villager":
                                    npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                case "Guard":
                                    npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                // Přidat případy pro další typy NPC
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }
                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Přidat NPC do seznamu NPC místnosti
                            }
                        }
                    }
                }

                // Načíst položky
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Předpokládáme, že toto je správný index pro položky
                    if (!itemsData.equals("null")) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Načíst klíče
                if (roomData.length > 14) {
                    String keysData = roomData[14]; // Předpokládáme, že toto je správný index pro klíče
                    if (!keysData.equals("null")) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String keyType = keyAttributes[0].trim(); // Odstranit mezery
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            switch (keyType) {
                                case "Map":
                                    key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Painting":
                                    key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Key":
                                    key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                // Přidat případy pro další typy klíčů
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }
                            if (key != null) {
                                room.getKeysList().add(key); // Přidat klíč do seznamu klíčů místnosti
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
*/
/*
    public static ArrayList<Room> loadRoomsFromFileos(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Check if roomData has enough elements
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Skip this iteration if data is insufficient
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Remove quotes
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Load enemies
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Assuming this is the correct index for enemies
                    if (!enemiesData.equals("null")) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Skip this iteration if data is insufficient
                            }
                            String enemyType = enemyAttributes[0].trim(); // Trim leading/trailing spaces
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType) {
                                case "Shadow":
                                    enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                case "DarkKnight": // Removed leading space
                                    enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                // Add cases for other enemy types here
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }
                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Load NPCs
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Assuming this is the correct index for NPCs
                    if (!npcsData.equals("null")) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Skip this iteration if data is insufficient
                            }
                            String npcType = npcAttributes[0].trim(); // Trim leading/trailing spaces
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // New property
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            switch (npcType) {
                                case "Villager":
                                    npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                case "Guard":
                                    npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                // Add cases for other NPC types here
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }
                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Add the NPC to the room's NPC list
                            }
                        }
                    }
                }

                // Load items
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Assuming this is the correct index for items
                    if (!itemsData.equals("null")) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Skip this iteration if data is insufficient
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Load keys
                if (roomData.length > 14) {
                    String keysData = roomData[14]; // Assuming this is the correct index for keys
                    if (!keysData.equals("null")) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Skip this iteration if data is insufficient
                            }

                            String keyType = keyAttributes[0].trim(); // Use trim() to avoid leading/trailing spaces
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            switch (keyType) {
                                case "Map":
                                    key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Painting":
                                    key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Key":
                                    key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                // Add cases for other key types here
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }
                            if (key != null) {
                                room.getKeysList().add(key); // Add the key to the room's key list
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


 */
    public static ArrayList<Room> loadRoomsFromFile06(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Přeskočit prázdné řádky
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Zkontrolujte, zda má roomData dostatek prvků
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Načíst nepřátele
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Předpokládáme, že toto je správný index pro nepřátele
                    if (!enemiesData.equals("null")) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String enemyType = enemyAttributes[0].trim(); // Odstranit mezery
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType) {
                                case "Shadow":
                                    enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                case "DarkKnight": // Odstranit mezery
                                    enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                // Přidat případy pro další typy nepřátel
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }
                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Načíst NPC
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Předpokládáme, že toto je správný index pro NPC
                    if (!npcsData.equals("null")) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String npcType = npcAttributes[0].trim(); // Odstranit mezery
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // Nová vlastnost
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            switch (npcType) {
                                case "Villager":
                                    npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                case "Guard":
                                    npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                // Přidat případy pro další typy NPC
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }
                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Přidat NPC do seznamu NPC místnosti
                            }
                        }
                    }
                }

                // Načíst položky
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Předpokládáme, že toto je správný index pro položky
                    if (!itemsData.equals("null")) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Načíst klíče
                if (roomData.length > 14) {
                    String keysData = roomData[14]; // Předpokládáme, že toto je správný index pro klíče
                    if (!keysData.equals("null")) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String keyType = keyAttributes[0].trim(); // Odstranit mezery
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            switch (keyType) {
                                case "Map":
                                    key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Painting":
                                    key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Key":
                                    key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                // Přidat případy pro další typy klíčů
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }
                            if (key != null) {
                                room.getKeysList().add(key); // Přidat klíč do seznamu klíčů místnosti
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile05(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Přeskočit prázdné řádky
                }

                System.out.println("Reading line: " + line); // Debugging line
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length); // Debugging length

                // Zkontrolujte, zda má roomData dostatek prvků
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                }

                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Načíst nepřátele
                if (roomData.length > 11) {
                    String enemiesData = roomData[11]; // Předpokládáme, že toto je správný index pro nepřátele
                    if (!enemiesData.equals("null")) {
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyData : enemiesArray) {
                            String[] enemyAttributes = enemyData.split(":");

                            if (enemyAttributes.length < 2) {
                                System.out.println("Insufficient data for enemy: " + enemyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String enemyType = enemyAttributes[0].trim(); // Odstranit mezery
                            String[] attributes = enemyAttributes[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType) {
                                case "Shadow":
                                    enemy = new Shadow(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                case "DarkKnight": // Odstranit mezery
                                    enemy = new DarkKnight(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Integer.parseInt(attributes[5]));
                                    break;

                                // Přidat případy pro další typy nepřátel
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }
                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    }
                }

                // Načíst NPC
                if (roomData.length > 12) {
                    String npcsData = roomData[12]; // Předpokládáme, že toto je správný index pro NPC
                    if (!npcsData.equals("null")) {
                        String[] npcsArray = npcsData.split(";");
                        for (String npcData : npcsArray) {
                            String[] npcAttributes = npcData.split(":");

                            if (npcAttributes.length < 6) {
                                System.out.println("Insufficient data for NPC: " + npcData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }
                            String npcType = npcAttributes[0].trim(); // Odstranit mezery
                            String npcName = npcAttributes[1];
                            String npcDialog = npcAttributes[2];
                            String npcWelcomeText = npcAttributes[3]; // Nová vlastnost
                            boolean isSpoken = Boolean.parseBoolean(npcAttributes[4]);
                            boolean isImportant = Boolean.parseBoolean(npcAttributes[5]);

                            NPC npc = null;
                            switch (npcType) {
                                case "Villager":
                                    npc = new Villiger(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                case "Guard":
                                    npc = new Guard(npcName, npcDialog, npcWelcomeText, isSpoken, isImportant);
                                    break;
                                // Přidat případy pro další typy NPC
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }
                            if (npc != null) {
                                room.getListOfNPCs().add(npc); // Přidat NPC do seznamu NPC místnosti
                            }
                        }
                    }
                }

                // Načíst položky
                if (roomData.length > 13) {
                    String itemsData = roomData[13]; // Předpokládáme, že toto je správný index pro položky
                    if (!itemsData.equals("null")) {
                        String[] itemsArray = itemsData.split(";");
                        for (String itemData : itemsArray) {
                            String[] itemAttributes = itemData.split(",");

                            if (itemAttributes.length < 7) {
                                System.out.println("Insufficient data for item: " + itemData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String itemName = itemAttributes[0];
                            double itemBonusSila = Double.parseDouble(itemAttributes[1]);
                            double itemBonusHp = Double.parseDouble(itemAttributes[2]);
                            double itemBonusInteligence = Double.parseDouble(itemAttributes[3]);
                            double itemBonusOdolnost = Double.parseDouble(itemAttributes[4]);
                            double itemBonusObratnost = Double.parseDouble(itemAttributes[5]);
                            boolean itemVisibility = Boolean.parseBoolean(itemAttributes[6]);
                            Items item = new Items(itemName, itemBonusSila, itemBonusHp, itemBonusInteligence, itemBonusOdolnost, itemBonusObratnost, itemVisibility);
                            room.getItemsList().add(item);
                        }
                    }
                }

                // Načíst klíče
                if (roomData.length > 14) {
                    String keysData = roomData[14];// Předpokládáme, že toto je správný index pro klíče

                    if (!keysData.equals("null")) {
                        String[] keysArray = keysData.split(";");
                        for (String keyData : keysArray) {
                            String[] keyAttributes = keyData.split(":");

                            if (keyAttributes.length < 6) {
                                System.out.println("Insufficient data for key: " + keyData);
                                continue; // Přeskočit tuto iteraci, pokud jsou data nedostatečná
                            }

                            String keyType = keyAttributes[0].trim(); // Odstranit mezery
                            String keyName = keyAttributes[1];
                            boolean keyMovable = Boolean.parseBoolean(keyAttributes[2]);
                            boolean keyVisible = Boolean.parseBoolean(keyAttributes[3]);
                            boolean keyBroken = Boolean.parseBoolean(keyAttributes[4]);
                            boolean keyUsed = Boolean.parseBoolean(keyAttributes[5]);

                            Keys key = null;
                            switch (keyType) {
                                case "Map":
                                    key = new Map(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Painting":
                                    key = new Painting(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                case "Key":
                                    key = new Key(keyName, keyMovable, keyVisible, keyBroken, keyUsed);
                                    break;
                                // Přidat případy pro další typy klíčů
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }
                            if (key != null) {
                                room.getKeysList().add(key); // Přidat klíč do seznamu klíčů místnosti
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

































/*
    public static ArrayList<Room> loadRoomsFromFile12(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // Zpracování základních informací o místnosti
                String[] roomData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split s ohledem na uvozovky

                if (roomData.length < 11) {
                    System.out.println("Chybějící data pro místnost: " + line);
                    continue;
                }

                Room room = createBasicRoom(roomData);

                // Zpracování všech sekcí
                for (int i = 11; i < roomData.length; i++) {
                    String section = roomData[i].trim();
                    if (section.startsWith("listOfEnemies:")) {
                        processEnemies(section, room);
                    }
                    else if (section.startsWith("listOfNPCs:")) {
                        processNPCs(section, room);
                    }
                    else if (section.startsWith("itemList:")) {
                        processItems(section, room);
                    }
                    else if (section.startsWith("keysList:")) {
                        processKeys(section, room);
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    private static Room createBasicRoom(String[] roomData) {
        return new Room(
                roomData[0],
                Integer.parseInt(roomData[1]),
                Integer.parseInt(roomData[2]),
                Boolean.parseBoolean(roomData[3]),
                Boolean.parseBoolean(roomData[4]),
                Boolean.parseBoolean(roomData[5]),
                Boolean.parseBoolean(roomData[6]),
                roomData[7].replace("\"", ""),
                Boolean.parseBoolean(roomData[8]),
                Boolean.parseBoolean(roomData[9]),
                Boolean.parseBoolean(roomData[10])
        );
    }

    private static void processEnemies(String section, Room room) {
        String enemiesData = section.substring("listOfEnemies:".length());
        if (enemiesData.equals("null")) return;

        for (String enemyEntry : enemiesData.split(";")) {
            if (enemyEntry.isEmpty()) continue;

            String[] parts = enemyEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát nepřítele: " + enemyEntry);
                continue;
            }

            String[] attributes = parts[1].split(",");
            Enemy enemy = createEnemy(parts[0], attributes);
            if (enemy != null) room.getListOfEnemies().add(enemy);
        }
    }

    private static Enemy createEnemy(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Shadow":
                    return new Shadow(
                            attributes[0],
                            Double.parseDouble(attributes[1]),
                            Double.parseDouble(attributes[2]),
                            Double.parseDouble(attributes[3]),
                            Double.parseDouble(attributes[4]),
                            Integer.parseInt(attributes[5])
                    );
                case "DarkKnight":
                    return new DarkKnight(
                            attributes[0],
                            Double.parseDouble(attributes[1]),
                            Double.parseDouble(attributes[2]),
                            Double.parseDouble(attributes[3]),
                            Double.parseDouble(attributes[4]),
                            Integer.parseInt(attributes[5])
                    );
                default:
                    System.out.println("Neznámý typ nepřítele: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření nepřítele: " + e.getMessage());
            return null;
        }
    }

    private static void processNPCs(String section, Room room) {
        String npcsData = section.substring("listOfNPCs:".length());
        if (npcsData.equals("null")) return;

        for (String npcEntry : npcsData.split(";")) {
            if (npcEntry.isEmpty()) continue;

            String[] parts = npcEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát NPC: " + npcEntry);
                continue;
            }

            String[] attributes = parts[1].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split s ohledem na uvozovky
            NPC npc = createNPC(parts[0], attributes);
            if (npc != null) room.getListOfNPCs().add(npc);
        }
    }

    private static NPC createNPC(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Villager":
                case "Villiger": // Pro případ překlepu
                    return new Villiger(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Guard":
                    return new Guard(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Wanderer":
                case "Wandarer": // Pro případ překlepu
                    return new Wanderer(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                default:
                    System.out.println("Neznámý typ NPC: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření NPC: " + e.getMessage());
            return null;
        }
    }

    private static void processItems(String section, Room room) {
        String itemsData = section.substring("itemList:".length());
        if (itemsData.equals("null")) return;

        for (String itemEntry : itemsData.split(";")) {
            if (itemEntry.isEmpty()) continue;

            String[] attributes = itemEntry.split(",");
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
                room.getItemsList().add(item);
            } catch (Exception e) {
                System.out.println("Chyba při vytváření předmětu: " + e.getMessage());
            }
        }
    }

    private static void processKeys(String section, Room room) {
        String keysData = section.substring("keysList:".length());
        if (keysData.equals("null")) return;

        for (String keyEntry : keysData.split(";")) {
            if (keyEntry.isEmpty()) continue;

            String[] parts = keyEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát klíče: " + keyEntry);
                continue;
            }

            String[] attributes = parts[1].split(",");
            Keys key = createKey(parts[0], attributes);
            if (key != null) room.getKeysList().add(key);
        }
    }

    private static Keys createKey(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Map":
                    return new Map(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Painting":
                    return new Painting(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Key":
                    return new Key(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                default:
                    System.out.println("Neznámý typ klíče: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření klíče: " + e.getMessage());
            return null;
        }
    }

 */


    public static ArrayList<Room> loadRoomsFromFile13(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // Zpracování základních informací o místnosti
                String[] roomData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split s ohledem na uvozovky

                if (roomData.length < 11) {
                    System.out.println("Chybějící data pro místnost: " + line);
                    continue;
                }

                Room room = createBasicRoom(roomData);

                // Zpracování všech sekcí
                for (int i = 11; i < roomData.length; i++) {
                    String section = roomData[i].trim();
                    if (section.startsWith("listOfEnemies:")) {
                        processEnemies(section, room);
                    } else if (section.startsWith("listOfNPCs:")) {
                        processNPCs(section, room);
                    } else if (section.startsWith("itemList:")) {
                        processItems(section, room);
                    } else if (section.startsWith("keysList:")) {
                        processKeys(section, room);
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    private static Room createBasicRoom(String[] roomData) {
        return new Room(
                roomData[0],
                Integer.parseInt(roomData[1]),
                Integer.parseInt(roomData[2]),
                Boolean.parseBoolean(roomData[3]),
                Boolean.parseBoolean(roomData[4]),
                Boolean.parseBoolean(roomData[5]),
                Boolean.parseBoolean(roomData[6]),
                roomData[7].replace("\"", ""),
                Boolean.parseBoolean(roomData[8]),
                Boolean.parseBoolean(roomData[9]),
                Boolean.parseBoolean(roomData[10])
        );
    }

    private static void processEnemies(String section, Room room) {
        String enemiesData = section.substring("listOfEnemies:".length());
        if (enemiesData.equals("null")) return;

        for (String enemyEntry : enemiesData.split(";")) {
            if (enemyEntry.isEmpty()) continue;

            String[] parts = enemyEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát nepřítele: " + enemyEntry);
                continue;
            }

            String[] attributes = parts[1].split(",");
            Enemy enemy = createEnemy(parts[0], attributes);
            if (enemy != null) room.getListOfEnemies().add(enemy);
        }
    }

    private static Enemy createEnemy(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Shadow":
                    return new Shadow(
                            attributes[0],
                            Double.parseDouble(attributes[1]),
                            Double.parseDouble(attributes[2]),
                            Double.parseDouble(attributes[3]),
                            Double.parseDouble(attributes[4]),
                            Integer.parseInt(attributes[5])
                    );
                case "DarkKnight":
                    return new DarkKnight(
                            attributes[0],
                            Double.parseDouble(attributes[1]),
                            Double.parseDouble(attributes[2]),
                            Double.parseDouble(attributes[3]),
                            Double.parseDouble(attributes[4]),
                            Integer.parseInt(attributes[5])
                    );
                default:
                    System.out.println("Neznámý typ nepřítele: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření nepřítele: " + e.getMessage());
            return null;
        }
    }

    private static void processNPCs(String section, Room room) {
        String npcsData = section.substring("listOfNPCs:".length());
        if (npcsData.equals("null")) return;

        for (String npcEntry : npcsData.split(";")) {
            if (npcEntry.isEmpty()) continue;

            String[] parts = npcEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát NPC: " + npcEntry);
                continue;
            }

            String[] attributes = parts[1].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split s ohledem na uvozovky
            NPC npc = createNPC(parts[0], attributes);
            if (npc != null) room.getListOfNPCs().add(npc);
        }
    }

    private static NPC createNPC(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Villager":
                case "Villiger": // Pro případ překlepu
                    return new Villiger(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Guard":
                    return new Guard(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Wanderer":
                case "Wandarer": // Pro případ překlepu
                    return new Wanderer(
                            attributes[0],
                            attributes[1],
                            attributes[2],
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                default:
                    System.out.println("Neznámý typ NPC: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření NPC: " + e.getMessage());
            return null;
        }
    }

    private static void processItems(String section, Room room) {
        String itemsData = section.substring("itemList:".length());
        if (itemsData.equals("null")) return;

        for (String itemEntry : itemsData.split(";")) {
            if (itemEntry.isEmpty()) continue;

            String[] attributes = itemEntry.split(",");
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
                room.getItemsList().add(item);
            } catch (Exception e) {
                System.out.println("Chyba při vytváření předmětu: " + e.getMessage());
            }
        }
    }

    private static void processKeys(String section, Room room) {
        String keysData = section.substring("keysList:".length());
        if (keysData.equals("null")) return;

        for (String keyEntry : keysData.split(";")) {
            if (keyEntry.isEmpty()) continue;

            String[] parts = keyEntry.split(":", 2);
            if (parts.length != 2) {
                System.out.println("Neplatný formát klíče: " + keyEntry);
                continue;
            }

            String[] attributes = parts[1].split(",");
            Keys key = createKey(parts[0], attributes);
            if (key != null) room.getKeysList().add(key);
        }
    }

    private static Keys createKey(String type, String[] attributes) {
        try {
            switch (type.trim()) {
                case "Map":
                    return new Map(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Painting":
                    return new Painting(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                case "Key":
                    return new Key(
                            attributes[0],
                            Boolean.parseBoolean(attributes[1]),
                            Boolean.parseBoolean(attributes[2]),
                            Boolean.parseBoolean(attributes[3]),
                            Boolean.parseBoolean(attributes[4])
                    );
                default:
                    System.out.println("Neznámý typ klíče: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Chyba při vytváření klíče: " + e.getMessage());
            return null;
        }
    }


    public static ArrayList<Room> loadRoomsFromFile11(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Přeskočit prázdné řádky
                }

                System.out.println("Reading line: " + line);
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length);

                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue;
                }

                // Získání základních informací o místnosti
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

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);

                // Zpracování nepřátel
                if (roomData.length > 11) {
                    String enemiesData = roomData[11];
                    if (enemiesData.startsWith("listOfEnemies:")) {
                        enemiesData = enemiesData.substring("listOfEnemies:".length());
                        String[] enemiesArray = enemiesData.split(";");
                        for (String enemyEntry : enemiesArray) {
                            if (enemyEntry.isEmpty()) continue;

                            String[] enemyParts = enemyEntry.split(":", 2);
                            if (enemyParts.length < 2) {
                                System.out.println("Invalid enemy format: " + enemyEntry);
                                continue;
                            }

                            String enemyType = enemyParts[0].trim();
                            String[] attributes = enemyParts[1].split(",");

                            Enemy enemy = null;
                            switch (enemyType) {
                                case "Shadow":
                                    if (attributes.length >= 6) {
                                        enemy = new Shadow(
                                                attributes[0],
                                                Double.parseDouble(attributes[1]),
                                                Double.parseDouble(attributes[2]),
                                                Double.parseDouble(attributes[3]),
                                                Double.parseDouble(attributes[4]),
                                                Integer.parseInt(attributes[5])
                                        );
                                    }
                                    break;
                                case "DarkKnight":
                                    if (attributes.length >= 6) {
                                        enemy = new DarkKnight(
                                                attributes[0],
                                                Double.parseDouble(attributes[1]),
                                                Double.parseDouble(attributes[2]),
                                                Double.parseDouble(attributes[3]),
                                                Double.parseDouble(attributes[4]),
                                                Integer.parseInt(attributes[5])
                                        );
                                    }
                                    break;
                                default:
                                    System.out.println("Unknown enemy type: " + enemyType);
                                    break;
                            }

                            if (enemy != null) {
                                room.getListOfEnemies().add(enemy);
                            }
                        }
                    } else {
                        System.out.println("Invalid enemies section: " + enemiesData);
                    }
                }

                // Zpracování NPC
                if (roomData.length > 12) {
                    String npcsData = roomData[12];
                    if (npcsData.startsWith("listOfNPCs:")) {
                        npcsData = npcsData.substring("listOfNPCs:".length());
                        String[] npcsArray = npcsData.split(";");
                        for (String npcEntry : npcsArray) {
                            if (npcEntry.isEmpty()) continue;

                            String[] npcParts = npcEntry.split(":", 2);
                            if (npcParts.length < 2) {
                                System.out.println("Invalid NPC format: " + npcEntry);
                                continue;
                            }

                            String npcType = npcParts[0].trim();
                            String[] attributes = npcParts[1].split(",");

                            NPC npc = null;
                            switch (npcType) {
                                case "Villager":
                                    if (attributes.length >= 6) {
                                        npc = new Villiger(
                                                attributes[0],
                                                attributes[1],
                                                attributes[2],
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                    break;
                                case "Guard":
                                    if (attributes.length >= 6) {
                                        npc = new Guard(
                                                attributes[0],
                                                attributes[1],
                                                attributes[2],
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                    break;
                                case "Wandarer":
                                    if (attributes.length >= 6) {
                                        npc = new Wanderer(attributes[0],
                                                attributes[1],
                                                attributes[2],
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                default:
                                    System.out.println("Unknown NPC type: " + npcType);
                                    break;
                            }

                            if (npc != null) {
                                room.getListOfNPCs().add(npc);
                            }
                        }
                    } else {
                        System.out.println("Invalid NPC section: " + npcsData);
                    }
                }

                // Zpracování položek
                if (roomData.length > 13) {
                    String itemsData = roomData[13];
                    if (itemsData.startsWith("itemList:")) {
                        itemsData = itemsData.substring("itemList:".length());
                        String[] itemsArray = itemsData.split(";");
                        for (String itemEntry : itemsArray) {
                            if (itemEntry.isEmpty()) continue;

                            String[] itemAttributes = itemEntry.split(",");
                            if (itemAttributes.length < 7) {
                                System.out.println("Invalid item format: " + itemEntry);
                                continue;
                            }

                            Items item = new Items(
                                    itemAttributes[0],
                                    Double.parseDouble(itemAttributes[1]),
                                    Double.parseDouble(itemAttributes[2]),
                                    Double.parseDouble(itemAttributes[3]),
                                    Double.parseDouble(itemAttributes[4]),
                                    Double.parseDouble(itemAttributes[5]),
                                    Boolean.parseBoolean(itemAttributes[6])
                            );
                            room.getItemsList().add(item);
                        }
                    } else {
                        System.out.println("Invalid items section: " + itemsData);
                    }
                }

                // Zpracování klíčů
                if (roomData.length > 14) {
                    String keysData = roomData[14];
                    if (keysData.startsWith("keysList:")) {
                        keysData = keysData.substring("keysList:".length());
                        String[] keysArray = keysData.split(";");
                        for (String keyEntry : keysArray) {
                            if (keyEntry.isEmpty()) continue;

                            String[] keyParts = keyEntry.split(":", 2);
                            if (keyParts.length < 2) {
                                System.out.println("Invalid key format: " + keyEntry);
                                continue;
                            }

                            String keyType = keyParts[0].trim();
                            String[] attributes = keyParts[1].split(",");

                            Keys key = null;
                            switch (keyType) {
                                case "Map":
                                    if (attributes.length >= 5) {
                                        key = new Map(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                    break;
                                case "Painting":
                                    if (attributes.length >= 5) {
                                        key = new Painting(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                    break;
                                case "Key":
                                    if (attributes.length >= 5) {
                                        key = new Key(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                    }
                                    break;
                                default:
                                    System.out.println("Unknown key type: " + keyType);
                                    break;
                            }

                            if (key != null) {
                                room.getKeysList().add(key);
                            }
                        }
                    } else {
                        System.out.println("Invalid keys section: " + keysData);
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile07(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] roomData = line.split(",");
                if (roomData.length < 11) continue;

                // ... (načtení základních dat místnosti)
                String nameOfRoom = roomData[0];
                int x = Integer.parseInt(roomData[1]);
                int y = Integer.parseInt(roomData[2]);
                boolean isPosibleGoLeft = Boolean.parseBoolean(roomData[3]);
                boolean isPosibleGoRight = Boolean.parseBoolean(roomData[4]);
                boolean isPosibleGoUp = Boolean.parseBoolean(roomData[5]);
                boolean isPosibleGoDown = Boolean.parseBoolean(roomData[6]);
                String description = roomData[7].replace("\"", ""); // Odstranit uvozovky
                boolean isUnlocked = Boolean.parseBoolean(roomData[8]);
                boolean isVisible = Boolean.parseBoolean(roomData[9]);
                boolean wasThere = Boolean.parseBoolean(roomData[10]);

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);


                // Vytvořte Room s potřebnými parametry

                // --- Načtení nepřátel ---
                if (roomData.length > 11) {
                    String enemiesSection = roomData[11];
                    if (enemiesSection.startsWith("listOfEnemies:")) {
                        String enemiesData = enemiesSection.split(":")[1]; // Ignorujte "listOfEnemies:"
                        if (!enemiesData.equals("null")) {
                            String[] enemiesArray = enemiesData.split(";");
                            for (String enemyData : enemiesArray) {
                                // Zpracujte každého nepřítele
                                String[] enemyAttributes = enemyData.split(":");
                                String enemyType = enemyAttributes[0].trim();
                                String[] attributes = enemyAttributes[1].split(",");


                                // Vytvořte Enemy a přidejte do room.getListOfEnemies()
                            }
                        }
                    }
                }

                // --- Načtení NPC ---
                if (roomData.length > 12) {
                    String npcsSection = roomData[12];
                    if (npcsSection.startsWith("listOfNPCs:")) {
                        String npcsData = npcsSection.split(":")[1]; // Ignorujte "listOfNPCs:"
                        if (!npcsData.equals("null")) {
                            String[] npcsArray = npcsData.split(";");
                            for (String npcData : npcsArray) {
                                // Zpracujte každé NPC
                                String[] npcAttributes = npcData.split(":");
                                // Vytvořte NPC a přidejte do room.getListOfNPCs()
                            }
                        }
                    }
                }

                // --- Načtení položek ---
                if (roomData.length > 13) {
                    String itemsSection = roomData[13];
                    if (itemsSection.startsWith("itemList:")) {
                        String itemsData = itemsSection.split(":")[1]; // Ignorujte "itemList:"
                        if (!itemsData.equals("null")) {
                            String[] itemsArray = itemsData.split(";");
                            for (String itemData : itemsArray) {
                                // Zpracujte každou položku
                                String[] itemAttributes = itemData.split(",");
                                // Vytvořte Item a přidejte do room.getItemsList()
                            }
                        }
                    }
                }

                // --- Načtení klíčů ---
                if (roomData.length > 14) {
                    String keysSection = roomData[14];
                    if (keysSection.startsWith("keysList:")) {
                        String keysData = keysSection.split(":")[1]; // Ignorujte "keysList:"
                        if (!keysData.equals("null")) {
                            String[] keysArray = keysData.split(";");
                            for (String keyData : keysArray) {
                                // Zpracujte každý klíč
                                String[] keyAttributes = keyData.split(":");
                                // Vytvořte Key a přidejte do room.getKeysList()
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile08(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                System.out.println("Reading line: " + line);
                String[] roomData = line.split(",");
                System.out.println("Room data length: " + roomData.length);

                // Základní atributy místnosti
                if (roomData.length < 11) {
                    System.out.println("Insufficient data for room: " + line);
                    continue;
                }

                Room room = new Room(
                        roomData[0],
                        Integer.parseInt(roomData[1]),
                        Integer.parseInt(roomData[2]),
                        Boolean.parseBoolean(roomData[3]),
                        Boolean.parseBoolean(roomData[4]),
                        Boolean.parseBoolean(roomData[5]),
                        Boolean.parseBoolean(roomData[6]),
                        roomData[7].replace("\"", ""),
                        Boolean.parseBoolean(roomData[8]),
                        Boolean.parseBoolean(roomData[9]),
                        Boolean.parseBoolean(roomData[10])
                );

                // Zpracování nepřátel
                if (roomData.length > 11) {
                    String enemiesSection = roomData[11];
                    if (enemiesSection.startsWith("listOfEnemies:")) {
                        String enemiesData = enemiesSection.split(":", 2)[1];
                        if (!enemiesData.equals("null")) {
                            for (String enemyEntry : enemiesData.split(";")) {
                                if (enemyEntry.isEmpty()) continue;

                                String[] parts = enemyEntry.split(":", 2);
                                if (parts.length != 2) {
                                    System.out.println("Invalid enemy entry: " + enemyEntry);
                                    continue;
                                }

                                String enemyType = parts[0].trim();
                                String[] attributes = parts[1].split(",");

                                try {
                                    Enemy enemy = switch (enemyType) {
                                        case "Shadow" -> new Shadow(
                                                attributes[0],
                                                Double.parseDouble(attributes[1]),
                                                Double.parseDouble(attributes[2]),
                                                Double.parseDouble(attributes[3]),
                                                Double.parseDouble(attributes[4]),
                                                Integer.parseInt(attributes[5])
                                        );
                                        case "DarkKnight" -> new DarkKnight(
                                                attributes[0],
                                                Double.parseDouble(attributes[1]),
                                                Double.parseDouble(attributes[2]),
                                                Double.parseDouble(attributes[3]),
                                                Double.parseDouble(attributes[4]),
                                                Integer.parseInt(attributes[5])
                                        );
                                        default -> {
                                            System.out.println("Unknown enemy type: " + enemyType);
                                            yield null;
                                        }
                                    };
                                    if (enemy != null) room.getListOfEnemies().add(enemy);
                                } catch (Exception e) {
                                    System.out.println("Error parsing enemy: " + enemyEntry);
                                }
                            }
                        }
                    }
                }

                // Zpracování NPC
                if (roomData.length > 12) {
                    String npcsSection = roomData[12];
                    if (npcsSection.startsWith("listOfNPCs:")) {
                        String npcsData = npcsSection.split(":", 2)[1];
                        if (!npcsData.equals("null")) {
                            for (String npcEntry : npcsData.split(";")) {
                                if (npcEntry.isEmpty()) continue;

                                String[] parts = npcEntry.split(":", 6);
                                if (parts.length != 6) {
                                    System.out.println("Invalid NPC entry: " + npcEntry);
                                    continue;
                                }

                                try {
                                    NPC npc = switch (parts[0].trim()) {
                                        case "Villager" -> new Villiger(
                                                parts[1],
                                                parts[2],
                                                parts[3],
                                                Boolean.parseBoolean(parts[4]),
                                                Boolean.parseBoolean(parts[5])
                                        );
                                        case "Guard" -> new Guard(
                                                parts[1],
                                                parts[2],
                                                parts[3],
                                                Boolean.parseBoolean(parts[4]),
                                                Boolean.parseBoolean(parts[5])
                                        );
                                        default -> {
                                            System.out.println("Unknown NPC type: " + parts[0]);
                                            yield null;
                                        }
                                    };
                                    if (npc != null) room.getListOfNPCs().add(npc);
                                } catch (Exception e) {
                                    System.out.println("Error parsing NPC: " + npcEntry);
                                }
                            }
                        }
                    }
                }

                // Zpracování předmětů
                if (roomData.length > 13) {
                    String itemsSection = roomData[13];
                    if (itemsSection.startsWith("itemList:")) {
                        String itemsData = itemsSection.split(":", 2)[1];
                        if (!itemsData.equals("null")) {
                            for (String itemEntry : itemsData.split(";")) {
                                if (itemEntry.isEmpty()) continue;

                                String[] attributes = itemEntry.split(",");
                                if (attributes.length != 7) {
                                    System.out.println("Invalid item entry: " + itemEntry);
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
                                    room.getItemsList().add(item);
                                } catch (Exception e) {
                                    System.out.println("Error parsing item: " + itemEntry);
                                }
                            }
                        }
                    }
                }

                // Zpracování klíčů
                if (roomData.length > 14) {
                    String keysSection = roomData[14];
                    if (keysSection.startsWith("keysList:")) {
                        String keysData = keysSection.split(":", 2)[1];
                        if (!keysData.equals("null")) {
                            for (String keyEntry : keysData.split(";")) {
                                if (keyEntry.isEmpty()) continue;

                                String[] parts = keyEntry.split(":", 2);
                                if (parts.length != 2) {
                                    System.out.println("Invalid key entry: " + keyEntry);
                                    continue;
                                }

                                String[] attributes = parts[1].split(",");
                                if (attributes.length != 6) {
                                    System.out.println("Invalid key attributes: " + keyEntry);
                                    continue;
                                }

                                try {
                                    Keys key = switch (parts[0].trim()) {
                                        case "Map" -> new Map(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                        case "Painting" -> new Painting(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                        case "Key" -> new Key(
                                                attributes[0],
                                                Boolean.parseBoolean(attributes[1]),
                                                Boolean.parseBoolean(attributes[2]),
                                                Boolean.parseBoolean(attributes[3]),
                                                Boolean.parseBoolean(attributes[4])
                                        );
                                        default -> {
                                            System.out.println("Unknown key type: " + parts[0]);
                                            yield null;
                                        }
                                    };
                                    if (key != null) room.getKeysList().add(key);
                                } catch (Exception e) {
                                    System.out.println("Error parsing key: " + keyEntry);
                                }
                            }
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile09(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] roomData = line.split(",");
                if (roomData.length < 11) continue;

                // Základní vytvoření místnosti
                Room room = new Room(
                        roomData[0],
                        Integer.parseInt(roomData[1]),
                        Integer.parseInt(roomData[2]),
                        Boolean.parseBoolean(roomData[3]),
                        Boolean.parseBoolean(roomData[4]),
                        Boolean.parseBoolean(roomData[5]),
                        Boolean.parseBoolean(roomData[6]),
                        roomData[7].replace("\"", ""),
                        Boolean.parseBoolean(roomData[8]),
                        Boolean.parseBoolean(roomData[9]),
                        Boolean.parseBoolean(roomData[10])
                );

                // Vylepšené parsování nepřátel
                if (roomData.length > 11 && roomData[11].startsWith("listOfEnemies:")) {
                    String enemiesData = roomData[11].split(":", 2)[1];
                    if (!enemiesData.equals("null")) {
                        for (String enemyEntry : enemiesData.split(";")) {
                            if (enemyEntry.contains(":")) {
                                String[] parts = enemyEntry.split(":", 2);
                                String enemyType = parts[0].trim();
                                String[] attributes = parts[1].split(",");

                                if (attributes.length >= 6) {
                                    try {
                                        Enemy enemy = switch (enemyType) {
                                            case "Shadow", "DarkKnight" -> new Shadow(
                                                    attributes[0],
                                                    Double.parseDouble(attributes[1]),
                                                    Double.parseDouble(attributes[2]),
                                                    Double.parseDouble(attributes[3]),
                                                    Double.parseDouble(attributes[4]),
                                                    Integer.parseInt(attributes[5])
                                            );
                                            default -> null;
                                        };
                                        if (enemy != null) room.getListOfEnemies().add(enemy);
                                    } catch (Exception e) {
                                        System.out.println("Chyba v záznamu nepřítele: " + enemyEntry);
                                    }
                                }
                            }
                        }
                    }
                }

                // Vylepšené parsování NPC
                if (roomData.length > 12 && roomData[12].startsWith("listOfNPCs:")) {
                    String npcsData = roomData[12].split(":", 2)[1];
                    if (!npcsData.equals("null")) {
                        for (String npcEntry : npcsData.split(";")) {
                            if (npcEntry.split(":").length >= 6) {
                                String[] parts = npcEntry.split(":", 6);
                                try {
                                    NPC npc = new Villiger(
                                            parts[1],
                                            parts[2],
                                            parts[3],
                                            Boolean.parseBoolean(parts[4]),
                                            Boolean.parseBoolean(parts[5])
                                    );
                                    room.getListOfNPCs().add(npc);
                                } catch (Exception e) {
                                    System.out.println("Chyba v záznamu NPC: " + npcEntry);
                                }
                            }
                        }
                    }
                }

                // Zbytek metody pro items a keys zůstává stejný
                // ...

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public static ArrayList<Room> loadRoomsFromFile10(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] roomData = line.split(",");
                if (roomData.length < 11) {
                    System.out.println("Neplatný formát místnosti: " + line);
                    continue;
                }

                // Základní atributy místnosti
                Room room = new Room(
                        roomData[0],
                        Integer.parseInt(roomData[1]),
                        Integer.parseInt(roomData[2]),
                        Boolean.parseBoolean(roomData[3]),
                        Boolean.parseBoolean(roomData[4]),
                        Boolean.parseBoolean(roomData[5]),
                        Boolean.parseBoolean(roomData[6]),
                        roomData[7].replace("\"", ""),
                        Boolean.parseBoolean(roomData[8]),
                        Boolean.parseBoolean(roomData[9]),
                        Boolean.parseBoolean(roomData[10])
                );

                // Parsování nepřátel
                if (roomData.length > 11 && roomData[11].startsWith("listOfEnemies:")) {
                    String[] enemies = roomData[11].split(":", 2)[1].split(";");
                    for (String enemyEntry : enemies) {
                        if (enemyEntry.equals("null") || enemyEntry.isEmpty()) continue;

                        String[] parts = enemyEntry.split(":", 2);
                        if (parts.length != 2) {
                            System.out.println("Neplatný záznam nepřítele: " + enemyEntry);
                            continue;
                        }

                        String[] attributes = parts[1].split(",");
                        if (attributes.length < 6) {
                            System.out.println("Chybějící atributy u nepřítele: " + enemyEntry);
                            continue;
                        }

                        try {
                            Enemy enemy = switch (parts[0].trim()) {
                                case "Shadow" -> new Shadow(
                                        attributes[0],
                                        Double.parseDouble(attributes[1]),
                                        Double.parseDouble(attributes[2]),
                                        Double.parseDouble(attributes[3]),
                                        Double.parseDouble(attributes[4]),
                                        Integer.parseInt(attributes[5])
                                );
                                case "DarkKnight" -> new DarkKnight(
                                        attributes[0],
                                        Double.parseDouble(attributes[1]),
                                        Double.parseDouble(attributes[2]),
                                        Double.parseDouble(attributes[3]),
                                        Double.parseDouble(attributes[4]),
                                        Integer.parseInt(attributes[5])
                                );
                                default -> {
                                    System.out.println("Neznámý typ nepřítele: " + parts[0]);
                                    yield null;
                                }
                            };
                            if (enemy != null) room.getListOfEnemies().add(enemy);
                        } catch (Exception e) {
                            System.out.println("Chyba parsování nepřítele: " + enemyEntry);
                        }
                    }
                }

                // Parsování NPC
                if (roomData.length > 12 && roomData[12].startsWith("listOfNPCs:")) {
                    String[] npcs = roomData[12].split(":", 2)[1].split(";");
                    for (String npcEntry : npcs) {
                        if (npcEntry.equals("null") || npcEntry.isEmpty()) continue;

                        String[] parts = npcEntry.split(":", 6);
                        if (parts.length != 6) {
                            System.out.println("Neplatný formát NPC: " + npcEntry);
                            continue;
                        }

                        try {
                            NPC npc = switch (parts[0].trim()) {
                                case "Villager" -> new Villiger(
                                        parts[1],
                                        parts[2],
                                        parts[3],
                                        Boolean.parseBoolean(parts[4]),
                                        Boolean.parseBoolean(parts[5])
                                );
                                case "Guard" -> new Guard(
                                        parts[1],
                                        parts[2],
                                        parts[3],
                                        Boolean.parseBoolean(parts[4]),
                                        Boolean.parseBoolean(parts[5])
                                );
                                case "Wandarer" -> new Guard(
                                        parts[1],
                                        parts[2],
                                        parts[3],
                                        Boolean.parseBoolean(parts[4]),
                                        Boolean.parseBoolean(parts[5])
                                );
                                default -> {
                                    System.out.println("Neznámý typ NPC: " + parts[0]);
                                    yield null;
                                }
                            };
                            if (npc != null) room.getListOfNPCs().add(npc);
                        } catch (Exception e) {
                            System.out.println("Chyba parsování NPC: " + npcEntry);
                        }
                    }
                }

                // Parsování předmětů
                if (roomData.length > 13 && roomData[13].startsWith("itemList:")) {
                    String[] items = roomData[13].split(":", 2)[1].split(";");
                    for (String itemEntry : items) {
                        if (itemEntry.equals("null") || itemEntry.isEmpty()) continue;

                        String[] attributes = itemEntry.split(",");
                        if (attributes.length != 7) {
                            System.out.println("Neplatný formát předmětu: " + itemEntry);
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
                            room.getItemsList().add(item);
                        } catch (Exception e) {
                            System.out.println("Chyba parsování předmětu: " + itemEntry);
                        }
                    }
                }

                // Parsování klíčů
                if (roomData.length > 14 && roomData[14].startsWith("keysList:")) {
                    String[] keys = roomData[14].split(":", 2)[1].split(";");
                    for (String keyEntry : keys) {
                        if (keyEntry.equals("null") || keyEntry.isEmpty()) continue;

                        String[] parts = keyEntry.split(":", 2);
                        if (parts.length != 2) {
                            System.out.println("Neplatný formát klíče: " + keyEntry);
                            continue;
                        }

                        String[] attributes = parts[1].split(",");
                        if (attributes.length != 5) {
                            System.out.println("Chybějící atributy klíče: " + keyEntry);
                            continue;
                        }

                        try {
                            Keys key = switch (parts[0].trim()) {
                                case "Map" -> new Map(
                                        attributes[0],
                                        Boolean.parseBoolean(attributes[1]),
                                        Boolean.parseBoolean(attributes[2]),
                                        Boolean.parseBoolean(attributes[3]),
                                        Boolean.parseBoolean(attributes[4])
                                );
                                case "Painting" -> new Painting(
                                        attributes[0],
                                        Boolean.parseBoolean(attributes[1]),
                                        Boolean.parseBoolean(attributes[2]),
                                        Boolean.parseBoolean(attributes[3]),
                                        Boolean.parseBoolean(attributes[4])
                                );
                                case "Key" -> new Key(
                                        attributes[0],
                                        Boolean.parseBoolean(attributes[1]),
                                        Boolean.parseBoolean(attributes[2]),
                                        Boolean.parseBoolean(attributes[3]),
                                        Boolean.parseBoolean(attributes[4])
                                );
                                default -> {
                                    System.out.println("Neznámý typ klíče: " + parts[0]);
                                    yield null;
                                }
                            };
                            if (key != null) room.getKeysList().add(key);
                        } catch (Exception e) {
                            System.out.println("Chyba parsování klíče: " + keyEntry);
                        }
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }


}






