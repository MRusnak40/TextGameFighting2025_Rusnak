package KeysAtributes;

public class Items {

    protected String name;
    protected double bonusSila;
    protected double bonusHp;
    protected double bonusInteligence;
    protected double bonusOdolnost;
    protected double bonusObratnst;
    protected boolean visibility;

    public Items(String name, double bonusSila, double bonusHp, double bonusInteligence, double bonusOdolnost, double bonusObratnst, boolean visibility) {
        this.name = name;
        this.bonusSila = bonusSila;
        this.bonusHp = bonusHp;
        this.bonusInteligence = bonusInteligence;
        this.bonusOdolnost = bonusOdolnost;
        this.bonusObratnst = bonusObratnst;
        this.visibility = visibility;
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBonusSila() {
        return bonusSila;
    }

    public void setBonusSila(double bonusSila) {
        this.bonusSila = bonusSila;
    }

    public double getBonusHp() {
        return bonusHp;
    }

    public void setBonusHp(double bonusHp) {
        this.bonusHp = bonusHp;
    }

    public double getBonusInteligence() {
        return bonusInteligence;
    }

    public void setBonusInteligence(double bonusInteligence) {
        this.bonusInteligence = bonusInteligence;
    }

    public double getBonusOdolnost() {
        return bonusOdolnost;
    }

    public void setBonusOdolnost(double bonusOdolnost) {
        this.bonusOdolnost = bonusOdolnost;
    }

    public double getBonusObratnst() {
        return bonusObratnst;
    }

    public void setBonusObratnst(double bonusObratnst) {
        this.bonusObratnst = bonusObratnst;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", bonusSila=" + bonusSila +
                ", bonusHp=" + bonusHp +
                ", bonusInteligence=" + bonusInteligence +
                ", bonusOdolnost=" + bonusOdolnost +
                ", bonusObratnst=" + bonusObratnst +
                ", visibility=" + visibility +
                '}';
    }
}
