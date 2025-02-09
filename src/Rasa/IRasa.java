package Rasa;

import org.w3c.dom.Text;

public interface IRasa {
    public double bonusSila();

    public double bonusInteligence();

    public double bonusObratnost();

    public double bonusOdolnost();

    public String getRasa();

    public static IRasa vyvber(int rasaNumber) {

        switch (rasaNumber) {
            case 1:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Human");
                System.out.println("-----------------");
                return new Clovek();

            case 2:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Elf");
                System.out.println("-----------------");
                return new Elf();

            case 3:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Undead");
                System.out.println("-----------------");
                return new Nemrtvi();

            case 4:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Dark elf");
                System.out.println("-----------------");
                return new TemnyElf();
            case 5:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Troll");
                System.out.println("-----------------");
                return new Troll();

            case 6:
                System.out.println("-----------------");
                System.out.println("CHoosed Rase: Liliput");
                System.out.println("-----------------");
                return new Trpaslik();

            default:
                return null;
        }

    }



}
