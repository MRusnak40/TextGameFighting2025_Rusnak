package KeysAtributes;

import Rooms.CombinationKeyForUnlock;

public class Key extends Keys {


    public Key(KeyEnum type, String name, boolean movable, boolean visible, boolean broken, boolean used, CombinationKeyForUnlock codeToUnlock) {
        super(type, name, movable, visible, broken, used, codeToUnlock);
    }

    public void setUnlocked() {

    }

    public void ThrowableFromBag() {

    }
}
