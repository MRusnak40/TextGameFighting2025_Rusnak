package Rase;

public class Elf implements IRasa{
    @Override
    public double bonusSila() {
        return 1;
    }

    @Override
    public double bonusInteligence() {
        return 1.06;
    }

    @Override
    public double bonusObratnost() {
        return 1.04;
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
        return "Elf{}";
    }
}
