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


    // pridani veci
    public void addToInvenoty(int vecDoInventare) {

        System.out.println("   ");
        System.out.println(" Vec byla pridana");
        System.out.println("VEC:\n" + move.getCurrentRoom().getItemsList().get(vecDoInventare));
        veciBatoh.add(move.getCurrentRoom().getItemsList().remove(vecDoInventare));
        setBonusesForItems();

    }

    public void setBonusesForItems() {
        for (Items item : veciBatoh) {

            //pridani hodnot z itemu
            postava.setCurrentHealth(postava.getCurrentHealth() + item.getBonusHp());
            postava.setMaxHealth(postava.getCurrentHealth() + item.getBonusHp());
            postava.setInteligence(postava.getInteligence() + item.getBonusInteligence());
            postava.setOdolnost(postava.getOdolnost() + item.getBonusOdolnost());
            postava.setObratnost(postava.getObratnost() + item.getBonusObratnst());
            postava.setSila(postava.getSila() + item.getBonusSila());


        }
        //pocitani bonusu
        postava.getRasa().bonusInteligence();
        postava.getRasa().bonusObratnost();
        postava.getRasa().bonusSila();
        postava.getRasa().bonusOdolnost();

    }

    public void disableBonusesForItems(int num) {
        //pridani hodnot z itemu
        postava.setCurrentHealth(postava.getCurrentHealth() - veciBatoh.get(num).getBonusHp());
        postava.setMaxHealth(postava.getCurrentHealth() - veciBatoh.get(num).getBonusHp());
        postava.setInteligence(postava.getInteligence() - veciBatoh.get(num).getBonusInteligence());
        postava.setOdolnost(postava.getOdolnost() - veciBatoh.get(num).getBonusOdolnost());
        postava.setObratnost(postava.getObratnost() - veciBatoh.get(num).getBonusObratnst());
        postava.setSila(postava.getSila() - veciBatoh.get(num).getBonusSila());


        //pocitani bonusu
        postava.getRasa().bonusInteligence();
        postava.getRasa().bonusObratnost();
        postava.getRasa().bonusSila();
        postava.getRasa().bonusOdolnost();


    }

    //vymenei veci pokud mam plny inventar
    //numberOfItemOnInvenotry - co odeberu z inventory
    //itemOfRoom - co si vezmes do  inventaere

    public void replaceItem(int numberOfItemOnInventory, int itemOfRoom) {
        System.out.println("Vec byla odstranena " + veciBatoh.get(numberOfItemOnInventory));

        //pridani codu pro to aby to odecetlo hodnoty odebranych predmetu
        disableBonusesForItems(numberOfItemOnInventory);

        veciBatoh.remove(numberOfItemOnInventory);
        System.out.println("             ");
        System.out.println("Pridal jsis do inventare");
        System.out.println(move.getCurrentRoom().getItemsList().get(itemOfRoom));
        veciBatoh.add(move.getCurrentRoom().getItemsList().remove(itemOfRoom));
        setBonusesForItems();
    }

    //odstraneni
    public void leaveItems() {


    }

    public void pickUpKeys(int vyber) {
        System.out.println("*****************");
        if (vyber <= move.getCurrentRoom().getKeysList().size() && vyber >=0) {

            kliceBatoh.add(move.getCurrentRoom().getKeysList().remove(vyber));
            System.out.println("Klic byl PRIDAN");

        } else {
            System.out.println("Klic se nenachazi na tomto miste v inventari ");
            return;
        }

    }

}
