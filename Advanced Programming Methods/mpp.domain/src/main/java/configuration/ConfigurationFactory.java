package configuration;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConfigurationFactory {

    public static ConfigurationProtocol build(ConfigurationType type) {
        switch (type) {
            case PLAYER: return new PlayerConfiguration();
            case EVENT: return new EventConfiguration();
            case USER: return new UserConfiguration();
            default: return new PlayerConfiguration();
        }
    }

}