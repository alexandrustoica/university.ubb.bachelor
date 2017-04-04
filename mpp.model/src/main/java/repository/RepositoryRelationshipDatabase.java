package repository;

import configuration.ConfigurationRelationProtocol;
import database.DatabaseConnectionManager;
import error.Errors;
import error.Error;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RepositoryRelationshipDatabase<T, E, TID, EID>
        extends DatabaseConnectionManager
        implements RepositoryRelationshipProtocol<T, E, TID, EID> {

    private ConfigurationRelationProtocol<T, E, TID, EID> configuration;

    public RepositoryRelationshipDatabase(String database,
                                          ConfigurationRelationProtocol<T, E, TID, EID> configuration) {
        this.database = database;
        this.configuration = configuration;
    }

    @Override
    public void add(T element, E other) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            statement.execute(configuration.InsertCommand(element, other));
            closeConnection();
        } catch (SQLException error) {
            connection = null;
            errors.add(new Error(error.getMessage()));
            throw errors;
        } catch (Errors error) {
            connection = null;
            errors.add(error);
            throw errors;
        }
    }

    @Override
    public void delete(T element, E other) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            statement.execute(configuration.DeleteCommand(element, other));
            closeConnection();
        } catch (SQLException error) {
            connection = null;
            errors.add(new Error(error.getMessage()));
            throw errors;
        } catch (Errors error) {
            connection = null;
            errors.add(error);
            throw error;
        }
    }

    @Override
    public ArrayList<T> getTObjectsByID(EID id) throws Errors {
        Errors errors = new Errors();
        ArrayList<T> data = new ArrayList<>();
        ResultSet result;
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(configuration.SelectTCommand(id));
            while (result.next()) {
                data.add(configuration.convertT(result));
            }
            closeConnection();
        } catch (SQLException error) {
            connection = null;
            errors.add(new Error(error.getMessage()));
            throw errors;
        } catch (Errors error) {
            connection = null;
            errors.add(error);
            throw errors;
        }
        return data;
    }

    @Override
    public ArrayList<E> getEObjectsByID(TID id) throws Errors {
        Errors errors = new Errors();
        ArrayList<E> data = new ArrayList<>();
        ResultSet result;
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(configuration.SelectECommand(id));
            while (result.next()) {
                data.add(configuration.convertE(result));
            }
            closeConnection();
        } catch (SQLException error) {
            connection = null;
            errors.add(new Error(error.getMessage()));
            throw errors;
        } catch (Errors error) {
            connection = null;
            errors.add(error);
            throw errors;
        }
        return data;
    }

}