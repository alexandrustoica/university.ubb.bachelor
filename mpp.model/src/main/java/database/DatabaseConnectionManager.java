package database;

import error.Errors;
import error.Error;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class DatabaseConnectionManager {

    protected Connection connection;
    protected String database;

    /**
     * Effect: Closes the model's connection to the model's database.
     * @throws SQLException : if the connection cannot be closed.
     */
    protected void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Effect: Sets a new connection to the model's database.
     * @throws Error : If database or JDBC class is not found!
     */
    private void setConnection() throws Error {
        try{
            Class.forName("org.sqlite.JDBC");
            File databaseFile = new File(database);
            connection = DriverManager.getConnection("jdbc:sqlite:" +
                    databaseFile.getPath());
        } catch (SQLException | ClassNotFoundException error) {
            throw new Error(error.getMessage());
        }
    }

    /**
     * Effect: Checks if the model is connected to the model's database.
     * @throws SQLException: If the model can't connect to the database.
     * returns: true if the model is connected to the database, false otherwise.
     */
    private Boolean isConnected() throws SQLException {
        return (connection != null) && (!connection.isClosed());
    }

    /**
     * Effect: Makes sure that the model is connected to the database.
     *         by creating a new connection if it's needed.
     * @throws Errors : If the model can't connect to the database.
     */
    protected void makeConnection() throws Errors {
        Errors errors = new Errors();
        try {
            if (!isConnected()) {
                setConnection();
            }
        } catch (SQLException error) {
            errors.add(new Error(error.getMessage()));
        } catch (Error error) {
            errors.add(error);
        }
    }

}