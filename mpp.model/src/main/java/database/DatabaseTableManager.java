package database;

import error.Errors;
import error.Error;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class DatabaseTableManager extends DatabaseConnectionManager {

    private DatabaseTable table;

    /**
     *  @param database: The database's name / file name.
     */
    public DatabaseTableManager(String database, DatabaseTable table) {
        this.table = table;
        this.database = database;
    }

    private void createTable() throws Errors {
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            String createTableCommand = String.format("CREATE TABLE %s (", table.getName());
            for (String name : table.getColumns().keySet()) {
                createTableCommand += name + " " + table.getColumns().get(name).toString() + ", " + System.lineSeparator();
            }
            createTableCommand = createTableCommand.substring(0, createTableCommand.length() - 3);
            createTableCommand += ")";
            statement.execute(createTableCommand);
            closeConnection();
        } catch (SQLException error) {
            throw new Errors(new Error(error.getMessage()));
        }
    }

    private Boolean existsTable() throws Errors {
        try {
            makeConnection();
            DatabaseMetaData data = connection.getMetaData();
            ResultSet result = data.getTables(null,
                    null, table.getName(), null);
            closeConnection();
            return result.next();
        } catch (SQLException error) {
            throw new Errors(new Error(error.getMessage()));
        }
    }

    public void makeTable() throws Errors {
        if (!existsTable()) {
            createTable();
        }
    }
}