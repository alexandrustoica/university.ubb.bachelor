package model;

import configuration.ConfigurationFactory;
import configuration.ConfigurationProtocol;
import configuration.ConfigurationType;
import domain.User;
import repository.RepositoryFactory;
import repository.RepositoryType;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelUser extends Model<User, Integer> {

    public ModelUser(String database) {
        this.preferredDatabaseSource = database;
        this.checkDatabase();
        ConfigurationProtocol configuration =
                ConfigurationFactory.build(ConfigurationType.USER);
        this.repository = RepositoryFactory.getRepository(
                RepositoryType.USER,
                preferredDatabaseSource,
                configuration
        );
    }

    public ModelUser() {
        this(database);
    }
}