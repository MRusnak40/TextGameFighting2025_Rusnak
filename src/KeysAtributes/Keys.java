package KeysAtributes;

public  abstract class Keys {
    protected KeyEnum type;
protected String name;
protected boolean movable;
protected boolean visible;
protected boolean broken;
protected boolean used;

    public Keys(KeyEnum type, String name, boolean movable, boolean visible, boolean broken, boolean used) {
        this.type = type;
        this.name = name;
        this.movable = movable;
        this.visible = visible;
        this.broken = broken;
        this.used = used;
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
                "name='" + name + '\'' +
                ", movable=" + movable +
                ", visible=" + visible +
                ", broken=" + broken +
                ", used=" + used +
                '}';
    }
}
