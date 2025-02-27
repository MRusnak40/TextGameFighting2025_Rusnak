package MainGame;

public class Exit extends Command{
    @Override
    public String execute() {


        return "Program jste timto ukoncily ale vas postup se neulozi \n DEKUJI ZA HRANI";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
