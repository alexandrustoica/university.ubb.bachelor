package configuration;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
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