package configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ConfigurationRelationProtocol<T, E, TID, EID> {

    String InsertCommand(T element, E other);
    String DeleteCommand(T element, E other);
    String SelectTCommand(EID id);
    String SelectECommand(TID id);
    T convertT(ResultSet result) throws SQLException;
    E convertE(ResultSet result) throws SQLException;

}