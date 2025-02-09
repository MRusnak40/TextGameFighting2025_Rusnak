package Rasa;

import Postavy.Carodej;
import Postavy.Pruzkumnik;

public abstract class Vyvber {


    public static IRasa vyvber(int rasaNumber){
        switch (rasaNumber) {
            case 1:
                System.out.println("CHoosed: Warrior");

                return new Clovek();

            case 2:
                System.out.println("CHoosed: Mage");
                return new Elf();

            case 3:
                System.out.println("CHoosed: Scout");
                return new Nemrtvi();

            default:
                return null;
        }

    }
}
