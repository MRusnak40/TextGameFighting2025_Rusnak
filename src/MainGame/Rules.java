package MainGame;

public class Rules extends Command {
    @Override
    public String execute() {
        return getRules()
                ;
    }

    @Override
    public boolean exit() {
        return false;
    }

    public String getRules() {
        StringBuilder rules = new StringBuilder();
        rules.append("Rules:\n");
        rules.append("1.Muzes se pohybovat pouze od nasledujici mistnsti \n");
        rules.append("2.maximalni pocet veci v inventari jsou  4 \n");
        rules.append("3.pokud mas klice mistnosti se otevrou \n");
        rules.append("4.Smery jsou left,right,up,down\n");
        rules.append("5.Pri zvednuti klice se odemkne dana mistnsot\n");
        rules.append("6.Musis projit vsechny mistnsoti\n");
        rules.append("7.Pokud si nezvednes itemy ze zeme zmizi");
        rules.append("8.");
        return rules.toString();
    }
}
