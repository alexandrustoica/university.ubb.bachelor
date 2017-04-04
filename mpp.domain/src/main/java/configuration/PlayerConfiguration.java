package configuration;

import domain.Player;
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

public class PlayerConfiguration implements ConfigurationProtocol<Player, Integer> {

    @Override
    public String InsertCommand(Player element) {
        return String.format("INSERT INTO Player (NAME, AGE) VALUES ('%s', %d);",
                element.getName(),
                element.getAge());
    }

    @Override
    public String UpdateCommand(Player element, Player with) {
        return String.format("UPDATE Player SET NAME = '%s', AGE = %d WHERE ID= %d;",
                with.getName(),
                with.getAge(),
                element.getID());
    }

    @Override
    public String DeleteCommand(Player element) {
        return String.format("DELETE FROM Player WHERE ID = %d;", element.getID());
    }

    @Override
    public String SelectCommand() {
        return "SELECT * FROM Player";
    }

    @Override
    public String FindByIDCommand(Integer id) {
        return String.format("SELECT * FROM Player WHERE ID = %d", id);
    }

    @Override
    public Player convert(ResultSet result) throws SQLException {
        Integer id = result.getInt("ID");
        String name = result.getString("NAME");
        Integer age = result.getInt("AGE");
        return new Player(id, name, age);
    }

}