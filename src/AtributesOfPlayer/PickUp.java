package AtributesOfPlayer;

import KeysAtributes.Keys;
import MainGame.Command;
import MainGame.Gameos;
import MainGame.Move;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

import Character.*;

public class PickUp extends Command {
    Move move;
    Scanner scanner = new Scanner(System.in);
    Invertory invertory;

    Postava postava;

    @Override
    public String execute() {
        Invertory invertorys = new Invertory(postava, move);
        invertory = invertorys;
        invertorying();
        vypis();
        return " POCET VECI V BATOHU: " + invertory.veciBatoh.size() + "\n" + "POCET KLICU:" + invertory.kliceBatoh.size();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public PickUp(Move move, Postava postava) {
        this.move = move;
        this.postava = postava;
    }

    public void vypis() {
        System.out.println("CHCES VEDET CO MAS V BATOHU😎");
        System.out.println("ANO/NE");
        String ano = scanner.next();

        switch (ano.toLowerCase()) {
            case "ano":
                System.out.println("KLICE");
                System.out.println(invertory.kliceBatoh);
                System.out.println("VECI");
                System.out.println(invertory.veciBatoh);
                return;
            case "ne":
                System.out.println("FAJN😢");
                return;
            default:
                System.out.println("NEPOCHOPENY PRIKAZ");
                return;
        }


    }


    public void invertorying() {
        if (move.getCurrentRoom().getListOfEnemies().isEmpty() && (!move.getCurrentRoom().getKeysList().isEmpty() || !move.getCurrentRoom().getItemsList().isEmpty())) {
            System.out.println("Predmety v mistnosti");
            System.out.println(" ❖═══≪✦✧✦✧✦✧≫═══❖ ");
            System.out.println(move.getCurrentRoom().getItemsList());
            System.out.println(" ❖═══≪✦✧✦✧✦✧≫═══❖ ");
            workingWithInventory();
        } else {
            System.out.println("NEMUZES PRACOVAT S PREDMETAMA ");

        }
    }

    public void workingWithInventory() {

        int vyber;
        int vyber2;
        int size = move.getCurrentRoom().getItemsList().size();
        for (int i = 0; i < size; i++) {
            System.out.println(move.getCurrentRoom().getItemsList());
            System.out.println("Chces si vzit predmet 1=ano / 2=ne:");


            //solving all problems with scanner
            int choose = Gameos.isIncomeGoodWithScanner(2, 1);


            switch (choose) {
                case 1:
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

                        System.out.println("vyber 1 az " + invertory.veciBatoh.size());
                        System.out.println(" VYBER:");
                        vyber = scanner.nextInt();
                        System.out.println("-------------");
                        System.out.println("Vyber co si vezmes z Mistnosti");
                        System.out.println("vyber  az 1" + move.getCurrentRoom().getItemsList().size());
                        System.out.println(" VYBER:");
                        vyber2 = scanner.nextInt();
//prace v Inventar
                        invertory.replaceItem(vyber - 1, vyber2 - 1);
                        if (move.getCurrentRoom().getItemsList().size() <= 0) {
                            break;
                        }
//pokud je dost mista v inventari
                    } else {
                        System.out.println(move.getCurrentRoom().getItemsList());
                        System.out.println("Co si chces vzit do inventare");
                        System.out.println("vyber 1 az " + move.getCurrentRoom().getItemsList().size());
                        System.out.println(" ---------");


                        //testing
                        vyber = Gameos.isIncomeGoodWithScanner(move.getCurrentRoom().getItemsList().size(), 1);


                        invertory.addToInvenoty(vyber - 1);

                    }
                    if (move.getCurrentRoom().getItemsList().size() <= 0) {
                        break;
                    }
                    scanner.nextLine();
                    continue;

                case 2:
                    System.out.println("PRACE S INVENTAREM ZRUSENA");
                    invertory.leaveItems();
                    scanner.nextLine();
                    break;
            }
        }

        //ohlidani aby zadny klic nebyl vynecahn

        System.out.println("VEM SI VSECHNY KLICE DO INVENTARE");
        int count = move.getCurrentRoom().getKeysList().size();
        for (int i = 0; i < count; i++) {
            workingWithKeys();
            if (move.getCurrentRoom().getKeysList().isEmpty()) {
                break;
            }
            scanner.nextLine();
        }


        if (invertory.kliceBatoh.size() > 0 && move.getCurrentRoom().getKeysList().isEmpty()) {
            System.out.println("Super mas vsechny klice");
            invertory.activateKeys();
        } else {
            System.out.println("Dneska nic sorry");
        }


    }

    //pridani klice
    public void workingWithKeys() {
        System.out.println(move.getCurrentRoom().getKeysList());
        System.out.println("Vem si do inventare klice");
        System.out.println("vyber 1 az " + move.getCurrentRoom().getKeysList().size());


        int vyber = Gameos.isIncomeGoodWithScanner(move.getCurrentRoom().getKeysList().size(), 1);


        invertory.pickUpKeys(vyber - 1);

    }
}
