package Arena;

import Postavy.Postava;
import Rasa.Textik;

import java.util.Scanner;

public class Lobby {
    Postava postava;

    Scanner sc = new Scanner(System.in);

    public void setNameOfCharacter() {
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


    }
/*
//Pohyb
    public void moveTo(int movingg) {

        switch (movingg) {
            case 1:
                FigthtingZone figthtingZone = new FigthtingZone();

                return;
            case 2:
                Shop shop = new Shop();
                shop.shoping(postava);
                return;

            case 3:
                Lobby lobby = new Lobby();

                return;
            default:
                System.out.println("spatny vyber");
        }


    }

    public void vyberPohybu() {

        System.out.println("********************");
        Textik.movePosibilites();


        int move = 0;
        boolean moveTo = true;
        while (moveTo == true) {

            try {
                move = sc.nextInt();
                moveTo = false;

                if (move < 1 || move > 3) {
                    moveTo = true;
                    System.out.println("spatny vyber");
                    System.out.println("Znovu:");
                }
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("spatny vyber");
                System.out.println("Znovu:");
                moveTo = true;
                sc.nextLine();


            }
        }


        moveTo(move);
    }


 */
}
