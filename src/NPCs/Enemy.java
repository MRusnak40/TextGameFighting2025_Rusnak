package NPCs;

import java.io.Serializable;
import java.util.Random;

public abstract class Enemy {
    Random random = new Random();
    protected EnemyEnum type;
    protected String jmeno;
    protected double sila;
    protected double inteligence;
    protected double obratnost;
    protected double odolnost;
    protected int level;
    protected double hp;

    public Enemy(EnemyEnum type, String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level, double hp) {
        this.type = type;
        this.jmeno = jmeno;
        this.sila = sila;
        this.inteligence = inteligence;
        this.obratnost = obratnost;
        this.odolnost = odolnost;
        this.level = level;
        this.hp = hp;
    }

    public double getUtok() {

        return sila + inteligence + obratnost + level;
    }

    ;

    public double getObrana() {

        return inteligence + obratnost + odolnost + level;

    }

    ;
/*
    public abstract double setInteligence();
    public abstract double setObratnost();
    public abstract double setSila();
    public abstract double setLevel();
    public abstract double setOdolnost();


 */

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

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

    @Override
    public String toString() {
        return "Enemy{" +
                "jmeno='" + jmeno + '\'' +
                ", sila=" + sila +
                ", inteligence=" + inteligence +
                ", obratnost=" + obratnost +
                ", odolnost=" + odolnost +
                ", level=" + level +
                '}';
    }
}
