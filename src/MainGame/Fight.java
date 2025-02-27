package MainGame;
import Character.*;

import NPCs.Enemy;

public class Fight extends Command{
    Game game;
    @Override
    public String execute() {
        game.play();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }





    public void fighting(Postava p, Enemy e){

    }
    public void Hits(){}


}
