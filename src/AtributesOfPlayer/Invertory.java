package AtributesOfPlayer;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import MainGame.Move;

import java.util.ArrayList;

public class Invertory {
    protected int MaxVeci;
    protected int MaxKeys;
    ArrayList<Items> veciBatoh;
    ArrayList<Keys> kliceBatoh;
    Move move;

    public void addToInvenoty(int vecDoInventare) {
        System.out.println("   ");
        System.out.println(" Vec byla pridana");
        System.out.println("VEC:\n"+move.getCurrentRoom().getItemsList().get(vecDoInventare));
        veciBatoh.add(move.getCurrentRoom().getItemsList().remove(vecDoInventare));


    }

    public void setBonusesForItems() {
    }


    public void replaceItem(int numberOfItemOnInventory, int itemOfRoom) {
        System.out.println("Vec byla odstranena " + veciBatoh.get(numberOfItemOnInventory));
        veciBatoh.remove(numberOfItemOnInventory);
        System.out.println("             ");
        System.out.println("Pridal jsis do inventare");
        System.out.println(move.getCurrentRoom().getItemsList().get(itemOfRoom));
        veciBatoh.add(move.getCurrentRoom().getItemsList().get(itemOfRoom));
    }


}
