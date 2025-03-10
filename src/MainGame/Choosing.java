package MainGame;

import AtributesOfPlayer.PickUp;
import AtributesOfPlayer.TalkToNPC;

import java.util.HashMap;
import java.util.Scanner;

public class Choosing {
Scanner scanner = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> mapa = new HashMap<>();

//pridavam do has mapy
public void inicializace(){
mapa.put("talk",new TalkToNPC());
mapa.put("walk",new Move());
mapa.put("pickup",new PickUp());
mapa.put("help",new Help());
mapa.put("exit",new Exit());


}
//zadavam coomand co budu delat
    //klic zadavam
public void doCommand(){
    System.out.print(">>");
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
//cyklus hry volam inicializace a doCommand
public void start(){
    inicializace();
    try{
        do{
            doCommand();
        }while(!exit);

    }catch (Exception e){
        System.out.println(e.getMessage());
    }
}



}
