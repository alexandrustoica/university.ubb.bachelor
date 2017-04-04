package configuration;

import domain.User;
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

public class UserConfiguration implements ConfigurationProtocol<User, Integer> {

    @Override
    public String InsertCommand(User element) {
        return String.format("INSERT INTO User (NAME, PASSWORD) VALUES ('%s', '%s');",
                element.getName(),
                element.getPassword());
    }

    @Override
    public String UpdateCommand(User element, User with) {
        return String.format("UPDATE User SET NAME = '%s', PASSWORD = '%s' WHERE ID= %d;",
                with.getName(),
                with.getPassword(),
                element.getID());
    }

    @Override
    public String DeleteCommand(User element) {
        return String.format("DELETE FROM User WHERE ID = %d;", element.getID());
    }

    @Override
    public String SelectCommand() {
        return "SELECT * FROM User";
    }

    @Override
    public String FindByIDCommand(Integer id) {
        return String.format("SELECT * FROM User WHERE ID = %d", id);
    }

    @Override
    public User convert(ResultSet result) throws SQLException {
        Integer id = result.getInt("ID");
        String name = result.getString("NAME");
        String password = result.getString("PASSWORD");
        return new User(id, name, password);
    }

}