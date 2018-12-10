package domain;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum EventStyle implements Serializable {

    FREE,
    BACK,
    BUTTERFLY,
    MIX;

    @Override
    public String toString() {
        switch (this) {
            case FREE: return "Free";
            case BACK: return "Back";
            case BUTTERFLY: return "Butterfly";
            case MIX: return  "Mix";
            default: return "Mix";
        }
    }

    public static EventStyle fromString(String type) {
        switch (type) {
            case "Free": return FREE;
            case "Back": return BACK;
            case "Butterfly": return BUTTERFLY;
            case "Mix": return MIX;
            default: return MIX;
        }
    }

}