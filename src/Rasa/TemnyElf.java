package Rasa;

public class TemnyElf implements IRasa {
    @Override
    public double bonusSila() {
        return 1.04;
    }

    @Override
    public double bonusInteligence() {
        return 1.05;
    }

    @Override
    public double bonusObratnost() {
        return 1.05;
    }

    @Override
    public double bonusOdolnost() {
        return 1;
    }

    @Override
    public String getRasa() {
        return "";
    }

    @Override
    public String toString() {
        return "TemnyElf{}";
    }
}
