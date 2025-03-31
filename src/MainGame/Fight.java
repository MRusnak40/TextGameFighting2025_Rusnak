package MainGame;

import Character.*;

import NPCs.Enemy;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Random;

public class Fight extends Command {


    private Postava postava;
    private Move move;
    Random random = new Random();
    private ArrayList<Enemy> deadEnemies;

    public Fight(Postava postava, Move move) {
        this.postava = postava;
        this.move = move;


    }

    @Override
    public String execute() {
        deadEnemies = new ArrayList<>();
        fight();
        return "BOJ SKONCIL";
    }

    @Override
    public boolean exit() {
        return false;
    }


    //player  attack
    public void playerAttack(Enemy e) {
        double luck = random.nextInt(10);
        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
        System.out.println("Udělal jsi útok: " + postava.getUtok() + " a bonusový útok: " + luck);
        double damage = postava.getUtok() + luck - e.getObrana();
        if (damage > 0) {
            e.setHp(Math.max(0, e.getHp() - damage));
        } else {
            System.out.println("---------------");
            System.out.println("Enemy se ti ubranil");
            System.out.println("---------------");
        }
        System.out.println(e.getJmeno() + " má " + e.getHp() + " HP.");

    }

    //enemy attack
    public void enemyAttack(Enemy e) {
        double luck = random.nextInt(10);
        System.out.println("✾✾═════✦✧✦✧✦═════✾✾");
        System.out.println("Dostal jsi damage: " + e.getUtok() + " a smula: " + luck);
        double damage = e.getUtok() + luck - postava.getObrana(postava);
        if (damage > 0) {
            postava.setCurrentHealth(Math.max(0, postava.getCurrentHealth() - damage));
        } else {
            System.out.println("--------------");
            System.out.println("Ubranil jsi ho");
            System.out.println("--------------");
        }
        System.out.println("Máš " + postava.getCurrentHealth() + " HP.");
    }

    public void fight() {
        if (!move.getCurrentRoom().getListOfEnemies().isEmpty()) {
            hits();
        } else {
            System.out.println("///++++++++++++++++++++++++++///");
            System.out.println("NEJSOU TU ENEMY");
            System.out.println("///++++++++++++++++++++++++++///");

        }
    }


    public void hits() {


        int size = move.getCurrentRoom().getListOfEnemies().size();
        for (int i = 0; i < size; i++) {

            try {

                for (Enemy enemy : move.getCurrentRoom().getListOfEnemies()) {
                    int chose = random.nextInt(2);

                    if (enemy.getHp() == 0) {
                        break;
                    }

                    boolean endFight = false;


                    while (endFight == false) {


                        switch (chose) {

                            case 0:
                                System.out.println("  ");
                                System.out.println("Enemy zapocina kolo ");
                                System.out.println("  ");

                                enemyAttack(enemy);
                                playerAttack(enemy);
                                break;
                            case 1:
                                System.out.println("  ");
                                System.out.println("Hrac zapocina kolo ");
                                System.out.println("  ");


                                playerAttack(enemy);
                                enemyAttack(enemy);
                                break;
                        }

                        //ohlasi smrt enemy
                        //prida hraci hp
                        boolean isEnemyDead = isenemyDeaths(enemy.getHp(), enemy);


                        //reset hp pro hrace
                        // nastavi 100 hp enemy

                        boolean isPDead = isPlayerDead(postava.getCurrentHealth(), enemy);

                        if (isPDead) {
                            isEnemyDead = false;
                        }

                        if (isEnemyDead && isPDead == false) {

                            deadEnemies.add(enemy);
                            move.getCurrentRoom().getListOfEnemies().remove(enemy);

                            if (move.getCurrentRoom().getListOfEnemies().isEmpty() && deadEnemies.size() == move.getCurrentRoom().getNuberOfEnemies()) {
                                System.out.println("║███████████║");
                                System.out.println("Nejsou tu dalsi enemy");
                                System.out.println("║███████████║");
                                endFight = true;
                                return;
                            } else {
                                System.out.println("Jsou tu dalsi enemy: " + move.getCurrentRoom().getListOfEnemies().size());
                                System.out.println("  ");
                                System.out.println("BOJUJ");
                                endFight = false;
                                break;
                            }

                        } else if (isPDead) {
                            System.out.println("   ");
                            System.out.println("   ");
                            System.out.println("OPOUSTENI BOJE");
                            System.out.println("   ");
                            System.out.println("   ");
                            endFight = true;
                            return;
                        } else if (!isEnemyDead && !isPDead) {

                            endFight = false;
                        } else {
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println("CHYBA BOJE");
                            endFight = true;

                        }


                    }

                }
            } catch (Exception e) {
                continue;
            }

        }


    }


    //pokud umre hrac
    //enemy se resetuje hp
    //posunu se na prvni mistnost
    //resetuje HP a Level
    public boolean isPlayerDead(double health, Enemy e) {
        if (health <= 0) {
            e.setHp(100);
            move.setCurrentRoom(move.getRooms().getFirst());
            move.setCurretX(0);
            move.setCurretY(0);
            move.setMoved(false);
            postava.setLevel(1);
            postava.setCurrentHealth(postava.getMaxHealth());
            System.out.println("┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼");
            System.out.println("YOU  DIED");
            System.out.println("BYL JSI PREMISTEN NA POCATECNI MISTNST");
            System.out.println("┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼");
            System.out.println(postava.toString());

            return true;
        } else {
            return false;
        }


    }

    //pokud umre enemy
    //odebrani enemy z current room array
    //pridani do deadEnemies
    //pridani hp pro hrace
    public boolean isenemyDeaths(double health, Enemy e) {
        if (health <= 0) {

            System.out.println("║║║║║║║║║");
            System.out.println("Enemy DIED");
            System.out.println("║║║║║║║║║");
            postava.setCurrentHealth(postava.getCurrentHealth() + 20);
            System.out.println("Bylo ti pricteno HP za zabiti:+20Hp");
            postava.setLevel(postava.getLevel() + 1);
            return true;
        } else {
            return false;
        }

    }


}
