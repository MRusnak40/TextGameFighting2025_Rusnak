package MainGame;

import KeysAtributes.*;
import NPCs.*;
import Rooms.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLoading {

    public static ArrayList<Room> loadRoomsFromFile(String filePath) {
        ArrayList<Room> rooms = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] roomData = line.split(",");
                if (roomData.length < 11) {
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

                Room room = new Room(nameOfRoom, x, y, isPosibleGoLeft, isPosibleGoRight, isPosibleGoUp, isPosibleGoDown, description, isUnlocked, isVisible, wasThere);
/*
                // Load Enemies
                if (roomData[11].startsWith("listOfEnemies")) {
                    if (roomData.length > 11 && !roomData[11].equals("null")) {
                        for (String enemyData : roomData[11].split(";")) {
                            String[] attributes = enemyData.split(",");
                            try {
                                EnemyEnum type = EnemyEnum.valueOf(attributes[0].toUpperCase());
                                Enemy enemy = null;
                                switch (type) {
                                    case SHADOW:
                                        enemy = new Shadow(type, attributes[1], Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5]), Integer.parseInt(attributes[6]));
                                        break;
                                    case DARKKNIGHT:
                                        enemy = new DarkKnight(type, attributes[1], Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5]), Integer.parseInt(attributes[6]));
                                        break;
                                    default:
                                        System.out.println("Unknown enemy type: " + attributes[0]);
                                        break;
                                }
                                if (enemy != null) {
                                    room.getListOfEnemies().add(enemy);
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Unknown enemy type: " + attributes[0]);
                            }
                        }
                    }
                }
                // Load NPCs
                if (roomData[11].startsWith("listOfNPCs")) {
                    if (roomData.length > 12 && !roomData[12].equals("null")) {
                        for (String npcData : roomData[12].split(";")) {
                            String[] attributes = npcData.split(",");
                            try {
                                NPCEnum type = NPCEnum.valueOf(attributes[0].toUpperCase());
                                NPC npc = null;
                                switch (type) {
                                    case VILLIGER:
                                        npc = new Villiger(type, attributes[1], attributes[2], attributes[3], Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    case WANDERER:
                                        npc = new Wanderer(type, attributes[1], attributes[2], attributes[3], Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    case FARMER:
                                        npc = new Farmer(type, attributes[1], attributes[2], attributes[3], Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    case GUARD:
                                        npc = new Guard(type, attributes[1], attributes[2], attributes[3], Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    default:
                                        System.out.println("Unknown NPC type: " + attributes[0]);
                                        break;
                                }
                                if (npc != null) {
                                    room.getListOfNPCs().add(npc);
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Unknown NPC type: " + attributes[0]);
                            }
                        }
                    }
                }
                // Load Items
                if (roomData[11].startsWith("itemList")) {
                    if (roomData.length > 13 && !roomData[13].equals("null")) {
                        for (String itemData : roomData[13].split(";")) {
                            String[] attributes = itemData.split(",");
                            Items item = new Items(attributes[0], Double.parseDouble(attributes[1]), Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5]), Boolean.parseBoolean(attributes[6]));
                            room.getItemsList().add(item);
                        }
                    }
                }
                // Load Keys
                if (roomData[11].startsWith("keysList")) {
                    if (roomData.length > 14 && !roomData[14].equals("null")) {
                        for (String keyData : roomData[14].split(";")) {
                            String[] attributes = keyData.split(",");
                            try {
                                KeyEnum type = KeyEnum.valueOf(attributes[0].toUpperCase());
                                Keys key = null;
                                switch (type) {
                                    case MAP:
                                        key = new Map(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    case PAINTING:
                                        key = new Painting(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    case KEY:
                                        key = new Key(type, attributes[1], Boolean.parseBoolean(attributes[2]), Boolean.parseBoolean(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]));
                                        break;
                                    default:
                                        System.out.println("Unknown key type: " + attributes[0]);
                                        break;
                                }
                                if (key != null) {
                                    room.getKeysList().add(key);
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("Unknown key type: " + attributes[0]);
                            }
                        }
                    }
                }
                */
                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }


}


