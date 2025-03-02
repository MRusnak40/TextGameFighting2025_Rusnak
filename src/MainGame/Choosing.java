package MainGame;

import AtributesOfPlayer.PickUp;
import AtributesOfPlayer.TalkToNPC;

import java.util.HashMap;

public class Choosing {

    private HashMap<String, Command> mapa = new HashMap<>();

//pridavam do has mapy
public void inicializace(){
mapa.put("talk",new TalkToNPC());
mapa.put("walk",new Move());
mapa.put("pickUp",new PickUp());
mapa.put("help",new Help());
mapa.put("exit",new Exit());


}
//zadavam coomand co budu delat
    //klic zadavam
public void doCommand(){

}
//cyklus hry volam inicializace a doCommand
public void start(){}



}
