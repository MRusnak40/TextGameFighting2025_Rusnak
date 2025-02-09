package Rasa;

public abstract class Textik {


    public static void textForRasa() {
        System.out.println("Rasa a druhy: ");
        System.out.println("1. člověk +3% síla, +3% inteligence, +4% obratnost \n" +
                "2. elf +6% inteligence, +4% obratnost \n" +
                "3. nemrtví +6% Sila  +10% Odolnost \n" +
                "4. temný elf +4% Sila  +5% inteligence +5% Obratnost  \n" +
                "5. troll +5% Sila  +7% odolnost \n" +
                "6. trpaslík +4% Sila +3 Inteligence +5% Odolnost");
        System.out.print("Your Choose:");

    }

    public static void characterText() {
        System.out.println("vyber si Character :1-3");
        System.out.println("1. Válečník na počátku: Síla = 10, Inteligence = 1, Obratnost = 1, Odolnost = 6");
        System.out.println("2. Mág na počátku: Síla = 5, Inteligence = 10, Obratnost = 3, Odolnost = 1");
        System.out.println("3. Průzkumník na počátku: Síla = 3, Inteligence = 7, Obratnost = 10, Odolnost = 0");
        System.out.print("Your Choose:");
    }


    public static void movePosibilites() {
        System.out.println("3x Rooms");
        System.out.println("1* Arena you will be fighting enemies");
        System.out.println("2* Shop where you can buy upgrades");
        System.out.println("3* Deafaultly you are in lobby");

        System.out.println("-*--*--*------*-*-*-*-*--*");
        System.out.println(" choosing by numbers  1-3");

    }
}
