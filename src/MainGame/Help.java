package MainGame;

public class Help extends Command{
    @Override
    public String execute() {
        return getHelp();
    }

    @Override
    public boolean exit() {
        return false;
    }

  public String getHelp() {

      return "Commands:\n talk-mluveni s NPC\n walk-chozeni \n pickUp-prace s predmetem \n help-vypis commandu \n exit-opustit hru  \n fight-boj s enemzy\n rules-pravidla jak hrat ";
  }

}
