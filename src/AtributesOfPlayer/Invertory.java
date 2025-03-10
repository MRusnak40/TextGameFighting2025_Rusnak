package AtributesOfPlayer;

import KeysAtributes.Items;
import KeysAtributes.Keys;
import MainGame.Move;
import Character.*;

import java.util.ArrayList;

public class Invertory {
    protected int MaxVeci;
    protected int MaxKeys;
    Postava postava;
    ArrayList<Items> veciBatoh = new ArrayList<>();
    ArrayList<Keys> kliceBatoh = new ArrayList<>();
    Move move;

    public void addToInvenoty(int vecDoInventare) {
        System.out.println("   ");
        System.out.println(" Vec byla pridana");
        System.out.println("VEC:\n" + move.getCurrentRoom().getItemsList().get(vecDoInventare));
        veciBatoh.add(move.getCurrentRoom().getItemsList().remove(vecDoInventare));
        setBonusesForItems();

    }

    public void setBonusesForItems() {
        for (Items item : veciBatoh) {

            postava.setCurrentHealth(postava.getCurrentHealth() + item.getBonusHp());
            postava.setMaxHealth(postava.getCurrentHealth() + item.getBonusHp());
            postava.setInteligence(postava.getInteligence() + item.getBonusInteligence());
            postava.setOdolnost(postava.getOdolnost() + item.getBonusOdolnost());
            postava.setObratnost(postava.getObratnost() + item.getBonusObratnst());
            postava.setSila(postava.getSila() + item.getBonusSila());

        }

    }

    public void disableBonusesForItems() {


    }


    public void replaceItem(int numberOfItemOnInventory, int itemOfRoom) {
        System.out.println("Vec byla odstranena " + veciBatoh.get(numberOfItemOnInventory));
        veciBatoh.remove(numberOfItemOnInventory);
        System.out.println("             ");
        System.out.println("Pridal jsis do inventare");
        System.out.println(move.getCurrentRoom().getItemsList().get(itemOfRoom));
        veciBatoh.add(move.getCurrentRoom().getItemsList().get(itemOfRoom));
        setBonusesForItems();
    }

    public void leaveItems() {


    }

    public void pickUpKeys(int vyber) {
        System.out.println("*****************");
        if (vyber <= move.getCurrentRoom().getKeysList().size()) {

            kliceBatoh.add(move.getCurrentRoom().getKeysList().remove(vyber));
            System.out.println("Klic byl PRIDAN");

        } else {
            System.out.println("Klic se nenachazi na tomto miste v inventari ");
            return;
        }

    }

}
