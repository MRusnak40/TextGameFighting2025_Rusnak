package Character;

import Rase.IRasa;
import Rase.Textik;

import java.util.Scanner;

public abstract class Postava {
    protected String jmeno;
    protected double sila;
    protected double inteligence;
    protected double obratnost;
    protected double odolnost;
    protected int level;
    protected IRasa rasa;
    protected double maxHealth;
    protected double currentHealth;
    Scanner sc = new Scanner(System.in);

    public Postava(String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level, IRasa rasa, double maxHealth, double currentHealth) {
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = level;
        this.rasa = rasa;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;

    }

    public Postava(String jmeno, double sila, double inteligence, double obratnost, double odolnost, IRasa rasa) {
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = 1;
        this.rasa = rasa;
        this.maxHealth = 100;
        this.currentHealth = 100;
    }

    /**
     * @param choosedNumber- number to choose a cahracter
     * @return postava with added stats
     */
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


                return new Bojovnik(jmeno, 100, 3, 3, 17, (IRasa) object);

            case 2:
                System.out.println("-----------------");
                System.out.println("CHoosed Specialization: Mage");
                System.out.println("-----------------");

                return new Carodej(jmeno, 17, 7, 9, 1, (IRasa) object);

            case 3:
                System.out.println("-----------------");
                System.out.println("CHoosed Specialization: Scout");
                System.out.println("-----------------");

                return new Pruzkumnik(jmeno, 7, 17, 10, 0, (IRasa) object);

            default:
                return null;
        }
    }

    //specialni vlastnosti pro danou kategorii
    public double getSila() {
        double strenght = sila * rasa.bonusSila();
        double strenghtGrounded = Math.round(strenght * 10.0) / 10.0;
        sila = strenghtGrounded;
        return sila;
    }

    public double getInteligence() {
        double intelig = inteligence * rasa.bonusInteligence();
        double inteligencerounded = Math.round(intelig * 10.0) / 10.0;
        inteligence = inteligencerounded;
        return inteligence;
    }

    public double getObratnost() {
        double strenght = obratnost * rasa.bonusObratnost();
        double strenghtGrounded = Math.round(strenght * 10.0) / 10.0;
        obratnost = strenghtGrounded;
        return obratnost;
    }

    public double getOdolnost() {

        double strenght = odolnost * rasa.bonusOdolnost();
        double strenghtGrounded = Math.round(strenght * 10.0) / 10.0;
        odolnost = strenghtGrounded;
        return odolnost;
    }


    public abstract double getUtok();

    public double getObrana(Postava protivnik) {
        return protivnik.getVlastnostKObrane(this);
    }

    public abstract double getVlastnostKObrane(Postava obrance);


/*
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

 */


    @Override
    public String toString() {
        return String.format(
                "Postava:%n" +
                        "  Jméno: %s%n" +
                        "  Úroveň: %d%n" +
                        "  Rasa: %s%n" +
                        "  Zdraví: %.0f / %.0f%n" +
                        "  Síla: %.1f%n" +
                        "  Inteligence: %.1f%n" +
                        "  Obratnost: %.1f%n" +
                        "  Odolnost: %.1f",
                jmeno, level, rasa != null ? rasa.toString() : "Neurčeno", currentHealth, maxHealth, sila, inteligence, obratnost, odolnost
        );
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
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


    public IRasa getRasa() {
        return rasa;
    }

    public void setRasa(IRasa rasa) {
        this.rasa = rasa;
    }


}
