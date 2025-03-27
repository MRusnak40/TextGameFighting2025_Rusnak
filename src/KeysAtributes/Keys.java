package KeysAtributes;

import Rooms.CombinationKeyForUnlock;

public  abstract class Keys {
    protected KeyEnum type;
protected String name;
protected boolean movable;
protected boolean visible;
protected boolean broken;
protected boolean used;
protected CombinationKeyForUnlock codeToUnlock;

    public Keys(KeyEnum type, String name, boolean movable, boolean visible, boolean broken, boolean used, CombinationKeyForUnlock codeToUnlock) {
        this.type = type;
        this.name = name;
        this.movable = movable;
        this.visible = visible;
        this.broken = broken;
        this.used = used;
        this.codeToUnlock = codeToUnlock;
    }

    public KeyEnum getType() {
        return type;
    }

    public void setType(KeyEnum type) {
        this.type = type;
    }

    public CombinationKeyForUnlock getCodeToUnlock() {
        return codeToUnlock;
    }

    public void setCodeToUnlock(CombinationKeyForUnlock codeToUnlock) {
        this.codeToUnlock = codeToUnlock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Keys{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", movable=" + movable +
                ", visible=" + visible +
                ", broken=" + broken +
                ", used=" + used +
                ", codeToUnlock=" + codeToUnlock +
                '}';
    }
}
