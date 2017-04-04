package configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ConfigurationProtocol<T, ID> {

    String InsertCommand(T element);
    String UpdateCommand(T element, T with);
    String DeleteCommand(T element);
    String SelectCommand();
    String FindByIDCommand(ID id);
    T convert(ResultSet result) throws SQLException;

}