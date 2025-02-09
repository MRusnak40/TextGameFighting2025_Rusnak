package Postavy;

import Rasa.IRasa;

public class Bojovnik extends Postava {


    public Bojovnik(String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level, int expy, IRasa rasa) {
        super(jmeno, sila, inteligence, obratnost, odolnost, level, expy, rasa);
    }

    public Bojovnik(String jmeno, double sila, double inteligence, double obratnost, double odolnost, IRasa rasa) {
        super(jmeno, sila, inteligence, obratnost, odolnost, rasa);
    }

    public double getUtok() {
        return sila+inteligence+obratnost ;
    }

    @Override
    public double getVlastnostKObrane(Postava obrance) {
        return obratnost+odolnost;
    }

    @Override
    public double getSila() {
        return super.getSila();
    }

    @Override
    public double getInteligence() {
        return super.getInteligence();
    }

    @Override
    public double getObratnost() {
        return super.getObratnost();
    }

    @Override
    public double getOdolnost() {
        return super.getOdolnost();
    }

    @Override
    public double getRana(Postava obrance) {
        return super.getRana(obrance);
    }

    @Override
    public double getObrana(Postava protivnik) {
        return super.getObrana(protivnik);
    }

    @Override
    public void lvlUP(Postava levliciPostava) {
        super.lvlUP(levliciPostava);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getJmeno() {
        return super.getJmeno();
    }

    @Override
    public void setJmeno(String jmeno) {
        super.setJmeno(jmeno);
    }

    @Override
    public void setSila(double sila) {
        super.setSila(sila);
    }

    @Override
    public void setInteligence(double inteligence) {
        super.setInteligence(inteligence);
    }

    @Override
    public void setObratnost(double obratnost) {
        super.setObratnost(obratnost);
    }

    @Override
    public void setOdolnost(double odolnost) {
        super.setOdolnost(odolnost);
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
    }

    @Override
    public int getExpy() {
        return super.getExpy();
    }

    @Override
    public void setExpy(int expy) {
        super.setExpy(expy);
    }

    @Override
    public IRasa getRasa() {
        return super.getRasa();
    }

    @Override
    public void setRasa(IRasa rasa) {
        super.setRasa(rasa);
    }

    @Override
    public void setCoins(int coins) {
        super.setCoins(coins);
    }

    @Override
    public int getCoins() {
        return super.getCoins();
    }
}