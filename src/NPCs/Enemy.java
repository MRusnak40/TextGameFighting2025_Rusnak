package NPCs;

import java.io.Serializable;

public abstract class Enemy implements Serializable {


    protected String jmeno;
    protected double sila;
    protected double inteligence;
    protected double obratnost;
    protected double odolnost;
    protected int level;

    public Enemy(String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level) {
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = level;
    }


    public  double getUtok(){
        return sila+inteligence+obratnost+level;
    };
    public  double getObrana(){
        return inteligence+obratnost+odolnost+level;

    };


    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public double getSila() {
        return sila;
    }

    public void setSila(double sila) {
        this.sila = sila;
    }

    public double getInteligence() {
        return inteligence;
    }

    public void setInteligence(double inteligence) {
        this.inteligence = inteligence;
    }

    public double getObratnost() {
        return obratnost;
    }

    public void setObratnost(double obratnost) {
        this.obratnost = obratnost;
    }

    public double getOdolnost() {
        return odolnost;
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
}
