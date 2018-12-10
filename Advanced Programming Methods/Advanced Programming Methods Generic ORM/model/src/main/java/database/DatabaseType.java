package database;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public enum DatabaseType implements DatabaseTypeInterface {

    TEST {
        @Override public String getConfigurationKey() {
            return "test_database";
        }
    },

    DEFAULT {
        @Override public String getConfigurationKey() {
            return "default_database";
        }
    }
}
