package KeysAtributes;

import Rooms.CombinationKeyForUnlock;

public class Map extends Keys{


    public Map(KeyEnum type, String name, boolean movable, boolean visible, boolean broken, boolean used, CombinationKeyForUnlock codeToUnlock) {
        super(type, name, movable, visible, broken, used, codeToUnlock);
    }

}
