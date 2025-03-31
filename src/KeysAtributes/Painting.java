package KeysAtributes;

import Rooms.CombinationKeyForUnlock;

public class Painting extends Keys {


    public Painting(KeyEnum type, String name, boolean movable, boolean visible, boolean broken, boolean used, CombinationKeyForUnlock codeToUnlock) {
        super(type, name, movable, visible, broken, used, codeToUnlock);
    }

}
