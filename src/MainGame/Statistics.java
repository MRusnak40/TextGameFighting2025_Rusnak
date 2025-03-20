package MainGame;
import Character.*;
public class Statistics extends  Command{
    Postava postava;
    @Override
    public String execute() {
        return postava.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Statistics(Postava postava) {
        this.postava = postava;
    }
}
