package MainGame;

import Character.*;

import NPCs.Enemy;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Random;

public class Fight extends Command {
    Postava postava;
    Enemy enemy;
    Move move;
    private boolean isDead;
    ArrayList<Enemy> deadEnemies = new ArrayList<>();
    Random random = new Random();

    public Fight(Postava postava, Move move) {
        this.postava = postava;

        this.move = move;
    }

    @Override
    public String execute() {
        fighting();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }


    public void fighting() {
        //pokud  list Enemies neni empty
        if (!move.getCurrentRoom().getListOfEnemies().isEmpty()) {
            Hits();
        } else {
            System.out.println("*--------*");
            System.out.println("THERE IS NO ENEMIES ");
            System.out.println("*--------*");
        }
    }

    //sets who will start as first
    public void Hits() {
        System.out.println("FIGHT - >>>");
        //urcuje kdo zacne bojovat prvni
        int guess = random.nextInt(2) + 1;
        int whoDied = 0;

        switch (guess) {
//fight zacina hrac
            case 1:

                System.out.println("Zahledl jsi ho jako prvni a mel jsi naskok");


                for (Enemy e : move.getCurrentRoom().getListOfEnemies()) {

                    double enemieHealth = e.getHp();
                    isDead = false;

                    while (isDead == false) {
//enemie got damage
                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");

                        double luck = random.nextInt(10);
                        System.out.println(" Udelil jsi utok: " + e.getUtok() + " a bonusovy utok: " + luck);

//pokud je ubranen cely utok
                        if ((e.getObrana() - e.getUtok() - luck) <= 0) {
                            e.setHp(e.getHp() + e.getObrana() - postava.getUtok() - luck);
                        } else {
                            System.out.println(" ✾✾═════✦✧✦✧✦═════✾✾  ");
                            System.out.println(e.getJmeno() + " UBRANIL  CELY UTOK *--*-*---");
                        }

                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");

                        whoDied = howDeadAreU(e);

//postava got damage
                        luck = random.nextInt(10);
                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                        System.out.println("Dostal jsi damage: " + e.getUtok() + " a smula: " + luck);
//pokud je ubranen cely utok

                        if ((postava.getObrana(postava) - e.getUtok() - luck) <= 0) {
                            postava.setCurrentHealth(postava.getCurrentHealth() + postava.getObrana(postava) - e.getUtok() - luck);
                        } else {
                            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                            System.out.println("UBRANIL JSI CELY UTOK *--*-*---");
                        }

                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                        whoDied = howDeadAreU(e);

                    }

                    switch (whoDied) {
                        case 0:
                            e.setHp(enemieHealth);
                            return;

                        case 1:
                            deadEnemies.add(move.getCurrentRoom().getListOfEnemies().remove(0));
                        case 2:
                            break;
                    }


                }


            case 2:

                System.out.println(" Nepritel na tebe zautocil z temnot");

                for (Enemy e : move.getCurrentRoom().getListOfEnemies()) {
                    double enemieHealth = e.getHp();
                    isDead = false;

                    while (isDead == false) {


//postava got damage
                        double luck = random.nextInt(10);
                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                        System.out.println("Dostal jsi damage: " + e.getUtok() + " a smula : " + luck);

                        if ((postava.getObrana(postava) - e.getUtok() - luck) <= 0) {
                            postava.setCurrentHealth(postava.getCurrentHealth() + postava.getObrana(postava) - e.getUtok() - luck);
                        } else {
                            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                            System.out.println("UBRANIL JSI CELY UTOK *--*-*---");
                        }

                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                        whoDied = howDeadAreU(e);
//enemie got damage
                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                        luck = random.nextInt(10);
                        System.out.println(" Udelil jsi utok: " + e.getUtok() + " a bonusovy utok: " + luck);


                        if ((e.getObrana() - e.getUtok() - luck) <= 0) {
                            e.setHp(e.getHp() + e.getObrana() - postava.getUtok() - luck);
                        } else {
                            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
                            System.out.println(e.getJmeno() + " UBRANIL  CELY UTOK *--*-*---");
                        }

                        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");

                        whoDied = howDeadAreU(e);
                    }

                    switch (whoDied) {
                        case 0:
                            e.setHp(enemieHealth);
                            return;

                        case 1:
                            deadEnemies.add(move.getCurrentRoom().getListOfEnemies().remove(0));
                        case 2:
                            break;
                    }


                }


        }


    }

    //checking who will die first
    public int howDeadAreU(Enemy e) {
        //vic hp nez 0
        if (0 >= postava.getCurrentHealth()) {

            move.setCurrentRoom(move.rooms.getFirst());
            postava.setCurrentHealth(postava.getMaxHealth());
            postava.setLevel(1);

            System.out.println("  ");
            System.out.println(" ****************** ");
            System.out.println(" you died as looser");
            System.out.println(" hodnoty byly zmeneny: ");
            System.out.println("Jmeno: " + postava.getJmeno() + " HP:" + postava.getCurrentHealth() + " Level:" + postava.getLevel());
            System.out.println("**********************  ");
            isDead = true;
            return 0;


        } else if (e.getHp() <= 0 && postava.getCurrentHealth() >= 0) {


            System.out.println("  ");
            System.out.println("You killed Enemie: " + e.getJmeno());
            postava.setCurrentHealth(postava.getCurrentHealth() + 40);
            System.out.println("Byly ti pridany zivoty (+40):" + postava.getCurrentHealth());
            System.out.println("  ");

            postava.setLevel(postava.getLevel() + 1);
            isDead = true;
            return 1;

        } else {

            isDead = false;
            return 2;


        }
    }

}
