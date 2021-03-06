package database;

import org.hibernate.Session;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface DatabaseGateway {
    Session openSession();
}
