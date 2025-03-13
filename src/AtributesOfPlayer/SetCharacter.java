package AtributesOfPlayer;

import Character.Postava;
import Rase.Textik;

import java.util.Scanner;

public class SetCharacter {
    Postava postava;

    Scanner sc = new Scanner(System.in);

    public Postava setNameOfCharacter() {
        //vyber postavy
        boolean vyber = true;
        int vyberPsotava = 0;
        Textik.characterText();

        while (vyber == true) {
            try {
                vyberPsotava = sc.nextInt();
                vyber = false;
                if (vyberPsotava > 3 || vyberPsotava < 1) {
                    vyber = true;
                    System.out.println("spatny vyber");
                    System.out.print("Znovu:");
                    sc.nextLine();
                }
            } catch (Exception e) {

                System.out.println("spatny vyber");
                System.out.print("Znovu:");
                vyber = true;
                sc.nextLine();
            }
        }

        postava = Postava.vvberPostavy(vyberPsotava);
        System.out.println("AKTUALNI STAV:");
        System.out.println(postava);

        sc.nextLine();
        return postava;
    }

    public SetCharacter(Postava postava) {
        this.postava = postava;
    }
}
