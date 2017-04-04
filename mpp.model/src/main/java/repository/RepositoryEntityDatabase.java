package repository;

import configuration.ConfigurationProtocol;
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

public class RepositoryEntityDatabase<T, ID extends Integer>
        extends DatabaseConnectionManager
        implements RepositoryEntityProtocol<T, ID> {

    private ConfigurationProtocol<T, ID> configuration;

    public RepositoryEntityDatabase(String database, ConfigurationProtocol<T, ID> configuration) {
        this.database = database;
        this.configuration = configuration;
    }

    @Override
    public int add(T element) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            statement.execute(configuration.InsertCommand(element));
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT last_insert_rowid()");
            int id = 0;
            if (result.next()) {
                id = Integer.parseInt(result.getString(1));
            }
            closeConnection();
            return id;
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
    public void update(T element, T with) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            statement.execute(configuration.UpdateCommand(element, with));
            closeConnection();
        } catch (SQLException error) {
            connection = null;
            errors.add(new Error(error.getMessage()));
        } catch (Errors error) {
            connection = null;
            errors.add(error);
        } finally {
            connection = null;
            if (!errors.empty()) {
                throw errors;
            }
        }
    }

    @Override
    public void delete(T element) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            statement.execute(configuration.DeleteCommand(element));
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
    public ArrayList<T> getAll() throws Errors {
        Errors errors = new Errors();
        ArrayList<T> data = new ArrayList<>();
        ResultSet result;
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(configuration.SelectCommand());
            while (result.next()) {
                data.add(configuration.convert(result));
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
    public T findElementById(ID id) throws Errors {
        Errors errors = new Errors();
        try {
            makeConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(configuration.FindByIDCommand(id));
            if (result.next()) {
                T element = configuration.convert(result);
                closeConnection();
                return element;
            }
            closeConnection();
            throw new Errors(new Error("Element Not Found!"));
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

}