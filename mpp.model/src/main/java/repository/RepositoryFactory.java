package repository;

import configuration.ConfigurationProtocol;
import domain.Event;
import domain.Player;
import domain.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RepositoryFactory {

    public static RepositoryEntityProtocol getRepository(RepositoryType type,
                                                         String database,
                                                         ConfigurationProtocol configuration) {
        switch (type) {
            case EVENT: return new RepositoryEntityDatabase<Event, Integer>(database, configuration);
            case USER: return new RepositoryEntityDatabase<User, Integer>(database, configuration);
            case PLAYER: return new RepositoryEntityDatabase<Player, Integer>(database, configuration);
            default: return null;
        }
    }

}