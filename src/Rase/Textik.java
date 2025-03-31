package Rase;

public abstract class Textik {


    public static void textForRasa() {
        System.out.println("Rasa a druhy: ");
        System.out.println("1. člověk +3% síla, +3% inteligence, +4% obratnost \n" +
                "2. elf +6% inteligence, +4% obratnost \n" +
                "3. temný elf +4% Sila  +5% inteligence +5% Obratnost  \n" +
                "4. trpaslík +4% Sila +3 Inteligence +5% Odolnost");
        System.out.print("Your Choose:");

    }

    public static void characterText() {
        System.out.println("vyber si Character :1-3");
        System.out.println("1. Válečník na počátku: Síla = 7, Inteligence = 3, Obratnost = 3, Odolnost = 17");
        System.out.println("2. Mág na počátku: Síla = 17, Inteligence = 7, Obratnost = 9, Odolnost = 1");
        System.out.println("3. Průzkumník na počátku: Síla = 7, Inteligence = 17, Obratnost = 10, Odolnost = 0");
        System.out.print("Your Choose:");
    }



}
