package Postavy;

import Rasa.IRasa;
import Rasa.Textik;

import java.util.Scanner;

public abstract class Postava {
    protected String jmeno;
    protected double sila;
    protected double inteligence;
    protected double obratnost;
    protected double odolnost;
    protected int level;
    protected int expy;
    protected IRasa rasa;
    protected int coins;
    Scanner sc = new Scanner(System.in);


    public Postava(String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level, int expy, IRasa rasa) {
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = level;
        this.expy = expy;
        this.rasa = rasa;
    }

    public Postava(String jmeno, double sila, double inteligence, double obratnost, double odolnost, IRasa rasa) {
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = 1;
        this.expy = 0;
        this.rasa = rasa;
        this.coins = 0;
    }

    public static Postava vvberPostavy(int choosedNumber) {


        Scanner sc = new Scanner(System.in);

//set name
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("***************************");
        System.out.println("Vyber Jmeno:");
        String jmeno = sc.next();
        System.out.println("-*-*--*-*-*-*-*--");
        System.out.println("Jmeno:" + jmeno);
        System.out.println("***********************");
        System.out.println("  ");
        System.out.println("  ");

        //set Rasa
        boolean isvybrano = false;
        int vyber = 0;
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("    ");
        Textik.textForRasa();
        Object object = null;

        while (!isvybrano) {
            try {

                vyber = sc.nextInt();
                object = IRasa.vyvber(vyber);

                isvybrano = true;

                if (object == null) {
                    isvybrano = false;
                    sc.nextLine();
                    System.out.println("spatny hodnota");
                    System.out.print("Znova:");
                }


            } catch (Exception e) {
                System.out.println("spatny hodnota");
                System.out.print("Znova:");
                isvybrano = false;
                sc.nextLine();
            }

        }
        //set postava a Rasa pomoci object
        switch (choosedNumber) {
            case 1:
                System.out.println("-----------------");
                System.out.println("CHoosed Specialization: Warrior");
                System.out.println("-----------------");


                return new Bojovnik(jmeno, 10, 1, 1, 6, (IRasa) object);

            case 2:
                System.out.println("-----------------");
                System.out.println("CHoosed Specialization: Mage");
                System.out.println("-----------------");

                return new Carodej(jmeno, 5, 10, 3, 1, (IRasa) object);

            case 3:
                System.out.println("-----------------");
                System.out.println("CHoosed Specialization: Scout");
                System.out.println("-----------------");

                return new Pruzkumnik(jmeno, 3, 7, 10, 0, (IRasa) object);

            default:
                return null;
        }
    }
//specialni vlastnosti pro danou kategorii
    public double getSila() {
        return sila * rasa.bonusSila();
    }

    public double getInteligence() {
        return inteligence * rasa.bonusInteligence();
    }

    public double getObratnost() {
        return obratnost * rasa.bonusObratnost();
    }

    public double getOdolnost() {
        return odolnost * rasa.bonusOdolnost();
    }

    public double getRana(Postava obrance) {
        return getUtok() - obrance.getObrana(this);
    }

    public abstract double getUtok();

    public double getObrana(Postava protivnik) {
        return getOdolnost() + protivnik.getVlastnostKObrane(this);
    }

    public abstract double getVlastnostKObrane(Postava obrance);

    public void lvlUP(Postava levliciPostava) {
        levliciPostava.level += 1;
        levliciPostava.expy = 0;
    }

    ;

    public void upgradeInteligence(Postava inteligenceBro) {

        inteligenceBro.inteligence += 1;
        double intelig = getInteligence();
        double inteligencerounded = Math.round(intelig * 10.0) / 10.0;
        inteligenceBro.inteligence = inteligencerounded;


    }

    public void upgradeSila(Postava inteligenceBro) {
        inteligenceBro.sila += 1;
        double strenght = getSila();
        double strenghtGrounded = Math.round(strenght * 10.0) / 10.0;
        inteligenceBro.sila = strenghtGrounded;

    }

    public void upgradeOdolnost(Postava inteligenceBro) {
        inteligenceBro.odolnost += 1;
        double odolnost = getOdolnost();
        double odolnostGrounded = Math.round(odolnost * 10.0) / 10.0;
        inteligenceBro.odolnost = odolnostGrounded;

    }

    public void upgradeObratnost(Postava inteligenceBro) {
        inteligenceBro.obratnost += 1;
        double obratnost = getObratnost();
        double obratnostGrounded = Math.round(obratnost * 10.0) / 10.0;
        inteligenceBro.obratnost = obratnostGrounded;
    }


    @Override
    public String toString() {
        return "Postava{" +
                "jmeno='" + jmeno + '\'' +
                ", sila=" + sila +
                ", inteligence=" + inteligence +
                ", obratnost=" + obratnost +
                ", odolnost=" + odolnost +
                ", level=" + level +
                ", expy=" + expy +
                ", rasa=" + rasa +
                '}';
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setSila(double sila) {
        this.sila = sila;
    }

    public void setInteligence(double inteligence) {
        this.inteligence = inteligence;
    }

    public void setObratnost(double obratnost) {
        this.obratnost = obratnost;
    }

    public void setOdolnost(double odolnost) {
        this.odolnost = odolnost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExpy() {
        return expy;
    }

    public void setExpy(int expy) {
        this.expy = expy;
    }

    public IRasa getRasa() {
        return rasa;
    }

    public void setRasa(IRasa rasa) {
        this.rasa = rasa;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
