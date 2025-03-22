package MainGame;

public class Rules extends Command{
    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public String getRules(){
        StringBuilder rules = new StringBuilder();
        rules.append("Rules:\n");
        rules.append("1.You can move only by 1 on line x or y \n");
        rules.append("2.maximalni pocet veci v inventari jsou  4 \n");
        rules.append("3.pokud mas klice mistnosti se otevrou \n");
        rules.append("4.Smery jsou left,right,up,down");
        rules.append("5.Klice beres vssechny nebo s nima musis interagovat");
        rules.append("6.");
        rules.append("7.");
        rules.append("8.");
        return rules.toString();
    }
}
