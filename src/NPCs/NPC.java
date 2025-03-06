package NPCs;

import java.io.Serializable;

public abstract class NPC   {
protected NPCEnum type;
protected String name;
protected String dialog;
protected String welcomeText;
protected boolean isSpoken;
protected boolean isImportant;


    public NPC(NPCEnum type, String name, String dialog, String welcomeText, boolean isSpoken, boolean isImportant) {
        this.type = type;
        this.name = name;
        this.dialog = dialog;
        this.welcomeText = welcomeText;
        this.isSpoken = isSpoken;
        this.isImportant = isImportant;
    }

    public NPCEnum getType() {
        return type;
    }

    public void setType(NPCEnum type) {
        this.type = type;
    }

    public boolean isSpoken() {
        return isSpoken;
    }

    public void setSpoken(boolean spoken) {
        isSpoken = spoken;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", dialog='" + dialog + '\'' +
                ", welcomeText='" + welcomeText + '\'' +
                ", isSpoken=" + isSpoken +
                '}';
    }
}
