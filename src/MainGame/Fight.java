package MainGame;

import Character.*;

import NPCs.Enemy;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Random;

public class Fight extends Command {









        private Postava postava;
        private Move move;
        private Random random = new Random();
        private ArrayList<Enemy> deadEnemies = new ArrayList<>();

        public Fight(Postava postava, Move move) {
            this.postava = postava;
            this.move = move;


        }

    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }



        public void playerAttack(Enemy e){
            double luck = random.nextInt(10);
            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
            System.out.println("Udělal jsi útok: " + postava.getUtok() + " a bonusový útok: " + luck);
            double damage = postava.getUtok() + luck - e.getObrana();
            e.setHp(Math.max(0, e.getHp() - damage));
            System.out.println("---------------");
            System.out.println(e.getJmeno() + " má " + e.getHp() + " HP.");

        }


        public void enemyAttack(Enemy e){
            double luck = random.nextInt(10);
            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
            System.out.println("Dostal jsi damage: " + e.getUtok() + " a smula: " + luck);
            double damage = e.getUtok() + luck - postava.getObrana(postava);
            postava.setCurrentHealth(Math.max(0, postava.getCurrentHealth() - damage));
            System.out.println("Máš " + postava.getCurrentHealth() + " HP.");
        }

    public void fight(){
            if(!move.getCurrentRoom().getListOfEnemies().isEmpty()){
                hits();
            }else {
                System.out.println("///++++++++++++++++++++++++++///");
                System.out.println("NEJSOU TU ENEMY");
                System.out.println("///++++++++++++++++++++++++++///");

            }
    }
public void hits(){

EFF
}

    public void handleDeaths(){}


    public void  enemyDeaths(){}












/*
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
            if (move.getCurrentRoom().getListOfEnemies() == null || move.getCurrentRoom().getListOfEnemies().isEmpty()) {
                System.out.println("*--------*");
                System.out.println("THERE ARE NO ENEMIES");
                System.out.println("*--------*");
                return;
            }
            Hits();
        }

        public void Hits() {
            System.out.println("FIGHT - >>>");
            int guess = random.nextInt(2) + 1;

            switch (guess) {
                case 1:
                    System.out.println("Zahledl jsi ho jako prvni a mel jsi naskok");
                    playerStarts();
                    break;
                case 2:
                    System.out.println("Nepritel na tebe zautocil z temnot");
                    enemyStarts();
                    break;
            }
        }

        private void playerStarts() {
            for (Enemy e : move.getCurrentRoom().getListOfEnemies()) {
                if (e == null) continue; // Ochrana proti null
                battleWithEnemy(e);
            }
        }

        private void enemyStarts() {
            for (Enemy e : move.getCurrentRoom().getListOfEnemies()) {
                if (e == null) continue; // Ochrana proti null
                enemyAttack(e);
                battleWithEnemy(e);
            }
        }

        private void battleWithEnemy(Enemy e) {
            while (postava.getCurrentHealth() > 0 && e.getHp() > 0) {
                playerAttack(e);
                if (e.getHp() > 0) {
                    enemyAttack(e);
                }
            }
            if (postava.getCurrentHealth() <= 0) {
                System.out.println("Umřel jsi! Obnovuji životy posledního nepřítele.");
                e.setHp(e.getMaxHp()); // Obnovit životy posledního nepřítele
                postava.setCurrentHealth(postava.getMaxHealth()); // Obnovit zdraví hráče
                postava.setLevel(1); // Reset levelu
            } else if (e.getHp() <= 0) {
                handleEnemyDeath(e);
            }
        }

        private void playerAttack(Enemy e) {
            double luck = random.nextInt(10);
            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
            System.out.println("Udělal jsi útok: " + postava.getUtok() + " a bonusový útok: " + luck);
            double damage = postava.getUtok() + luck - e.getObrana();
            e.setHp(Math.max(0, e.getHp() - damage));
            System.out.println(e.getJmeno() + " má " + e.getHp() + " HP.");
        }

        private void enemyAttack(Enemy e) {
            double luck = random.nextInt(10);
            System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
            System.out.println("Dostal jsi damage: " + e.getUtok() + " a smula: " + luck);
            double damage = e.getUtok() + luck - postava.getObrana(postava);
            postava.setCurrentHealth(Math.max(0, postava.getCurrentHealth() - damage));
            System.out.println("Máš " + postava.getCurrentHealth() + " HP.");
        }

        private void handleEnemyDeath(Enemy e) {
            System.out.println("Zabil jsi nepřítele: " + e.getJmeno());
            postava.setCurrentHealth(postava.getCurrentHealth() + 40);
            System.out.println("Získal jsi 40 HP! Aktuální zdraví: " + postava.getCurrentHealth());
            move.getCurrentRoom().getListOfEnemies().remove(e); // Odebrat nepřítele ze seznamu
            deadEnemies.add(e); // Přidat nepřítele do seznamu mrtvých
        }










 */













    /*
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
                        controllingDeath(whoDied, e, enemieHealth);
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
                        controllingDeath(whoDied, e, enemieHealth);
                    }

                    if (move.getCurrentRoom().getListOfEnemies().isEmpty()) {
                        return;
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
                        controllingDeath(whoDied, e, enemieHealth);
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
                        controllingDeath(whoDied, e, enemieHealth);

                    }

                    if (move.getCurrentRoom().getListOfEnemies().isEmpty()) {
                        return;
                    }
                }


        }


    }

    public void controllingDeath(int number, Enemy e, double live) {
        switch (number) {
            case 0:
                e.setHp(live);
                return;

            case 1:
                if (!move.getCurrentRoom().getListOfEnemies().isEmpty())
                    deadEnemies.add(move.getCurrentRoom().getListOfEnemies().removeFirst());
                return;
            case 2:
                break;
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
            System.out.println("Byly ti pridany zivoty (+40)--->>>>" + postava.getCurrentHealth());
            System.out.println("  ");

            postava.setLevel(postava.getLevel() + 1);
            isDead = true;
            return 1;

        } else {

            isDead = false;
            return 2;


        }
    }

     */



}
