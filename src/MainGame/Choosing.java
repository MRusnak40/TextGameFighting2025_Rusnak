package MainGame;

import AtributesOfPlayer.PickUp;
import AtributesOfPlayer.TalkToNPC;

import java.util.HashMap;
import java.util.Scanner;
import Character.*;
import NPCs.Enemy;

public class Choosing {
Scanner scanner = new Scanner(System.in);
     boolean exit = false;
    private HashMap<String, Command> mapa = new HashMap<>();
Postava postava;
Enemy enemy;
Move move;
//pridavam do has mapy
public void inicializace(){
mapa.put("talk",new TalkToNPC(move));
mapa.put("walk", move);
mapa.put("pickup",new PickUp(move,postava));
mapa.put("help",new Help());
mapa.put("exit",new Exit());
mapa.put("fight",new Fight(postava,move));
mapa.put("rules",new Rules());
mapa.put("statistics",new Statistics(postava));

}

    public Choosing(Move move, Postava postava) {
        this.move = move;

        this.postava = postava;
    }

    /**
     * puts commands here for commander
     */
    //zadavam coomand co budu delat
    //klic zadavam
public void doCommand(){

    System.out.print("ZADEJ AKCI-->>");
    String prikaz = scanner.nextLine();
    prikaz = prikaz.trim();
    prikaz = prikaz.toLowerCase();

    if(mapa.containsKey(prikaz)){
        System.out.println(">> "+mapa.get(prikaz).execute());
        exit = mapa.get(prikaz).exit();
    }else{
        System.out.println(">> Nedefinovany prikaz");
    }
}




}
