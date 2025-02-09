package NPCs;

import java.io.Serializable;

public abstract class NPC implements Serializable {

protected String name;
protected String dialog;
protected String welcomeText;


    public NPC(String name, String dialog, String welcomeText) {
        this.name = name;
        this.dialog = dialog;
        this.welcomeText = welcomeText;
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
}
