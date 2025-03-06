package AtributesOfPlayer;

import MainGame.Command;
import MainGame.Move;

import java.util.Scanner;

public class PickUp extends Command {
    Move move;
    Scanner scanner = new Scanner(System.in);

    @Override
    public String execute() {
        workingWithInventory();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }




    public void workingWithInventory() {
        Invertory invertory = new Invertory();
        int vyber;
        int vyber2;
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
            System.out.println("vyber 1 az "+invertory.veciBatoh.size()+1);
            System.out.println(" VYBER:");
            vyber=scanner.nextInt();
            System.out.println("-------------");
            System.out.println("Vyber co si vezmes z Mistnosti");
            System.out.println("vyber 1 az "+move.getCurrentRoom().getItemsList().size()+1);
            System.out.println(" VYBER:");
            vyber2= scanner.nextInt();

            invertory.replaceItem(vyber-1,vyber2-1);

        } else {
            System.out.println("Co si chces vzit do inventare");
            System.out.println("vyber 1 az "+move.getCurrentRoom().getItemsList().size()+1);
            System.out.println(" ---------");
            vyber=scanner.nextInt();
            invertory.addToInvenoty(vyber);
        }


    }


}
