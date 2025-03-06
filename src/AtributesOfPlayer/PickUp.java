package AtributesOfPlayer;

import MainGame.Command;
import MainGame.Move;

public class PickUp extends Command {
    Move move;
    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public void workingWithInventory(){
Invertory invertory=new Invertory();
if(invertory.veciBatoh.size()>4){
    //veci se prohodi v invenory


}else{

}


    }

}
