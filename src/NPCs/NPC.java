package NPCs;

import java.io.Serializable;

public abstract class NPC   {

protected String name;
protected String dialog;
protected String welcomeText;
protected boolean isSpoken;
protected boolean isImportant;


    public NPC(String name, String dialog, String welcomeText, boolean isSpoken, boolean isImportant) {
        this.name = name;
        this.dialog = dialog;
        this.welcomeText = welcomeText;
        this.isSpoken = isSpoken;
        this.isImportant = isImportant;
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
