package AtributesOfPlayer;

import KeysAtributes.Keys;
import MainGame.Command;
import MainGame.Move;

import java.util.Scanner;

public class PickUp extends Command {
    Move move;
    Scanner scanner = new Scanner(System.in);
    Invertory invertory = new Invertory();

    @Override
    public String execute() {
        workingWithInventory();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public PickUp(Move move) {
        this.move = move;


    }

    public void workingWithInventory() {

        int vyber;
        int vyber2;
        for(int i=0;i<move.getCurrentRoom().getItemsList().size();i++) {
            if (invertory.veciBatoh.size() >= 4) {
                //veci se prohodi v invenory
                System.out.println("   ");
                System.out.println("Mas plny inventar bude se muset neceho zbavit");
                System.out.println("Vyber item ktery chces odebrat");
                System.out.println("-------------");
                System.out.println("Vypis veci v inventari");
                System.out.println(invertory.veciBatoh);
                System.out.println("-------------");
                System.out.println("Vypis veci v Mistnsoti");
                System.out.println(move.getCurrentRoom().getItemsList());
                System.out.println("-------------");
                System.out.println("Vyber co odeberes ze sveho inventare");
                System.out.println("vyber 0 az " + invertory.veciBatoh.size());
                System.out.println(" VYBER:");
                vyber = scanner.nextInt();
                System.out.println("-------------");
                System.out.println("Vyber co si vezmes z Mistnosti");
                System.out.println("vyber  az 0" + move.getCurrentRoom().getItemsList().size());
                System.out.println(" VYBER:");
                vyber2 = scanner.nextInt();
//prace v Inventar
                invertory.replaceItem(vyber, vyber2);
//pokud je dost mista v inventari
            } else {
                System.out.println(move.getCurrentRoom().getItemsList());
                System.out.println("Co si chces vzit do inventare");
                System.out.println("vyber 0 az " + move.getCurrentRoom().getItemsList().size());
                System.out.println(" ---------");
                vyber = scanner.nextInt();

                invertory.addToInvenoty(vyber);
            }
        }
        //ohlidani aby zadny klic nebyl vynecahn
        System.out.println("VEM SI VSECHNY KLICE DO INVENTARE");
        for(int i=0;i<move.getCurrentRoom().getKeysList().size();i++) {
            workingWithKeys();
        }
    }
//pridani klice
    public void workingWithKeys() {
        System.out.println(move.getCurrentRoom().getKeysList());
        System.out.println("Vem si do inventare klice");
        System.out.println("vyber 0 az " + move.getCurrentRoom().getKeysList().size());
        int vyber = scanner.nextInt();
        invertory.pickUpKeys(vyber);
    }
}
