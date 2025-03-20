package AtributesOfPlayer;

import MainGame.Command;
import MainGame.Move;
import NPCs.NPC;
import Rooms.Room;

import java.util.Random;
import java.util.Scanner;

public class TalkToNPC extends Command {
    Move move;
    protected String dialog;
    Scanner scanner = new Scanner(System.in);
    Random rd = new Random();

    @Override
    public String execute() {
        chooseToTalk();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
    public TalkToNPC(Move move) {
        this.move = move;

    }
/*
    private void chooseToTalk() {
        int importantTalks = move.informationAboutTalking();
        int totalNPCs = move.getCurrentRoom().getNumberOfNPCs();

        if (totalNPCs > importantTalks) {
            System.out.println("--------***---------");
            System.out.println("Chces si jentak pokecat? 1/0  \n1=ano \n0=ne");

            if (scanner.nextInt() == 1) {
                System.out.println("SUPER✨✨✨");
                talking();
            } else {
                System.out.println("--------***----***------");
                System.out.println("Oni s tebou taky ne👌");
            }
            scanner.nextLine();
        }else {
            System.out.println("Nelze");
        }


    }

    private void talking() {
        var npcs = move.getCurrentRoom().getListOfNPCs();
        if (npcs.isEmpty()) {
            System.out.println(" ✶*•̩̩͙✧•̩̩͙✦✧✦✧✦✧•̩̩͙✧•̩̩͙*✶");
            System.out.println("Neni tu zadny vesnican😉");
            return;
        }

        for (NPC npc : npcs) {
            if (npc.isImportant() || npc.isSpoken()) continue;

            System.out.println("   ");
            System.out.println("/=================/");
            System.out.println("NPC " + npc.getName());
            System.out.println("*===================*");

            if (!npc.isImportant()) {
                System.out.println(npc.getDialog());
                npc.setSpoken(true);
            } else {
                System.out.println(getRandomResponse());
            }
            System.out.println("   ");
        }
    }

    private void inevitableTalking() {
        for (NPC npc : move.getCurrentRoom().getListOfNPCs()) {
            if (npc.isImportant() && !npc.isSpoken()) {
                System.out.println("   ");
                System.out.println("/=================/");
                System.out.println("Mluvis s: " + npc.getName());
                System.out.println("*===================*");
                System.out.println(npc.getDialog());
                npc.setSpoken(true);
                System.out.println("   ");
            }else if(npc.isImportant() && npc.isSpoken()){

            }
        }
    }

    private String getRandomResponse() {
        String[] responses = {
                "Nemam cas ted",
                "Nemam co rict!",
                "Nekdy priste ti to povim",
                "Ja to svoje rekl",
                "TAHNI"
        };
        return responses[rd.nextInt(responses.length)];
    }




 */

    //vyber toho jestli bude mluvit i s nedulezitima
    public void chooseToTalk() {
        System.out.println("**--------------**");
        System.out.println("DULEZITY DIALOG");
        System.out.println("**--------------**");
        inevitableTalking();

        int number = move.informationAboutTalking();
        int notIm = move.getCurrentRoom().getNumberOfNPCs();

        if( (notIm-number)>0 ) {
            System.out.println("--------***---------");
            System.out.println("Chces mluvit i s ostatnimi? 1/0  \n1=ano \n0=ne");
            int confirm = scanner.nextInt();
            switch (confirm) {

                case 1:
                    System.out.println("SUPER✨✨✨");
                    talking();
                    return;
                case 0:
                    System.out.println("--------***----***------");
                    System.out.println("Oni s tebou taky ne👌");
                    return;
            }
        }
    }



    public void talking() {
        //mluveni s nedulezityma osobama
        if (!move.getCurrentRoom().getListOfNPCs().isEmpty()) {
            for (NPC npCs : move.getCurrentRoom().getListOfNPCs()) {
                System.out.println("   ");
                System.out.println("   ");

                if (!npCs.isSpoken() && !npCs.isImportant()) {
                    System.out.println("/=================/");
                    System.out.println("NPC " + npCs.getName());
                    System.out.println("*===================*");

                    System.out.println(npCs.getDialog());
                    npCs.setSpoken(true);

                } else {

                    if (!npCs.isImportant()) {

                        System.out.println("/=================/");
                        System.out.println("NPC " + npCs.getName());
                        System.out.println("*===================*");

                        System.out.println(getRandomResponse());

                    }
                }
            }

        } else {
            System.out.println(" ✶*•̩̩͙✧•̩̩͙✦✧✦✧✦✧•̩̩͙✧•̩̩͙*✶");
            System.out.println("Neni tu zadny vesnican😉");
        }
    }

    private String getRandomResponse() {
        String[] responses = {
                "Nemam cas ted",
                "Nemam co rict!",
                "Nekdy priste ti to povim",
                "Ja to svoje rekl",
                "TAHNI",
                "Opakovat ti to nebudu"
        };
        return responses[rd.nextInt(responses.length)];
    }

    //mluveni s dulezityma osobama
    public void inevitableTalking() {

        for (NPC npCs : move.getCurrentRoom().getListOfNPCs()) {

            System.out.println("   ");
            System.out.println("   ");
            System.out.println("/=================/");
            System.out.println("Mluvis s: " + npCs.getName());
            System.out.println("*===================*");


            if (npCs.isImportant() && !npCs.isSpoken()) {

                System.out.println(npCs.getDialog());
                npCs.setSpoken(true);
            }else if(npCs.isImportant() && npCs.isSpoken()){
                System.out.println(getRandomResponse());

            }
        }


    }




}
