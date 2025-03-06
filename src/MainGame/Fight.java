package MainGame;
import Character.*;

import NPCs.Enemy;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Random;

public class Fight extends Command{
Postava postava;
Enemy enemy;
Move move;
ArrayList<Enemy>deadEnemies=new ArrayList<>();
Random random=new Random();
    @Override
    public String execute() {

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }





    public void fighting(){





    }
    public void Hits() {
        System.out.println("Zacina:");
        int guess = random.nextInt(2) + 1;
        boolean isEnemieStarting;
switch (guess){

    case 1:
        System.out.println("Zahledl jsi ho jako prvni a mel jsi naskok");


    case 2:
        System.out.println(" Nepritel na tebe zautocil z temnot");
        for(Enemy e:move.getCurrentRoom().getListOfEnemies()){

            double luck=random.nextDouble(10);


            postava.setCurrentHealth(postava.getCurrentHealth() - e.getUtok()-luck);



        }


}



    }

}
