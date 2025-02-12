package NPCs;

public class Shadow extends Enemy {
    public Shadow(String jmeno, double sila, double inteligence, double obratnost, double odolnost, int level) {
        super(jmeno, sila, inteligence, obratnost, odolnost, level);
    }

    @Override
    public double setInteligence() {
        return 0;
    }

    @Override
    public double setObratnost() {
        return 0;
    }

    @Override
    public double setSila() {
        return 0;
    }

    @Override
    public double setLevel() {
        return 0;
    }

    @Override
    public double setOdolnost() {
        return 0;
    }


}
