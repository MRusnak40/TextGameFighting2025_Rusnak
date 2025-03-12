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

      return "Commands:\n talk\n walk \n pickUp \n help \n exit  \n fight ";
  }

}
