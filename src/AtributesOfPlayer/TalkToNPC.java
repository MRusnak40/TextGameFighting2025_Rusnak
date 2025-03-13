package AtributesOfPlayer;

import MainGame.Command;
import MainGame.Move;
import NPCs.NPC;
import Rooms.Room;

import java.util.Scanner;

public class TalkToNPC extends Command {
    Move move;
    protected String dialog;
Scanner scanner=new Scanner(System.in);
    @Override
    public String execute() {
        talking();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }



    public TalkToNPC(Move move) {
        this.move = move;

    }

    public void talking() {
        //mluveni s nedulezityma osobama
        if (!move.getCurrentRoom().getListOfNPCs().isEmpty()) {
            for (NPC npCs : move.getCurrentRoom().getListOfNPCs()) {
                System.out.println("-------------");
                System.out.println("NPC " + npCs.getName());
                System.out.println("-----------------");
                if (!npCs.isSpoken() && !npCs.isImportant()) {
                    System.out.println(npCs.getDialog());
                    npCs.setSpoken(true);
                }
            }

        } else {
            System.out.println("Neni tu zadny vesnican😉");
        }
    }

    //mluveni s dulezityma osobama
    public void inevitableTalking() {


        for (NPC npCs : move.getCurrentRoom().getListOfNPCs()) {
            if (npCs.isImportant() && !npCs.isSpoken()) {
                System.out.println("----------------");
                System.out.println("Mluvis s: " + npCs.getName());
                System.out.println(" *********************  ");
                System.out.println(npCs.getDialog());
                npCs.setSpoken(true);
            }
        }


    }

    public void inevedibleTalkToNPC() {
        System.out.println("davej pozor at vis co mas delat");
        System.out.println("MLUVI NA TEBE NPC");
        System.out.println("----------");

      inevitableTalking();
        System.out.println("+++++++++++++++");
        System.out.println("CHAPES TO? 1/0  \n1=ano \n0=ne");
        int confirm = scanner.nextInt();
        switch (confirm) {

            case 1:
                System.out.println("SUPER✨✨✨");
                return;
            case 0:
                System.out.println("****************");
                System.out.println("PROSTE PROJDI vsechny MISTNOSTI A VRAT SE NA NAMESTI");
                System.out.println("****************");
                System.out.println("    ");
        }
    }
}
