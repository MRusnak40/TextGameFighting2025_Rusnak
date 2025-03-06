package AtributesOfPlayer;

import MainGame.Command;
import MainGame.Move;
import NPCs.NPC;
import Rooms.Room;

public class TalkToNPC extends Command {
    Move move;
    protected String dialog;

    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }


    public void talking() {
        for(int i=0;i<move.getCurrentRoom().getListOfNPCs().size();i++){
            for(NPC npCs:move.getCurrentRoom().getListOfNPCs()){
                System.out.println("Mluvis s "+npCs.getName());
                System.out.println(npCs.getDialog());
                npCs.setSpoken(true);
            }

        }
    }


}
