package configuration;

import domain.Event;
import domain.EventStyle;
import domain.Player;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PlayerEventConfiguration implements
        ConfigurationRelationProtocol<Player, Event, Integer, Integer> {

    @Override
    public String InsertCommand(Player element, Event other) {
        return String.format("INSERT INTO PlayerEvent (ID_PLAYER, ID_EVENT) VALUES (%d, %d);",
                element.getID(),
                other.getID());
    }



    @Override
    public String DeleteCommand(Player element, Event other) {
        return String.format("DELETE FROM PlayerEvent WHERE ID_PLAYER = %d AND ID_EVENT = %d;",
                element.getID(),
                other.getID());
    }

    @Override
    public String SelectTCommand(Integer id) {
        return String.format("SELECT * FROM Player INNER JOIN PlayerEvent ON Player.ID = PlayerEvent.ID_PLAYER AND PlayerEvent.ID_EVENT = %d", id);
    }

    @Override
    public String SelectECommand(Integer id) {
        return String.format("SELECT * FROM Event INNER JOIN PlayerEvent ON Event.ID = PlayerEvent.ID_EVENT AND PlayerEvent.ID_PLAYER = %d", id);
    }

    @Override
    public Player convertT(ResultSet result) throws SQLException {
        Integer id = result.getInt("ID");
        String name = result.getString("NAME");
        Integer age = result.getInt("AGE");
        return new Player(id, name, age);
    }

    @Override
    public Event convertE(ResultSet result) throws SQLException {
        Integer id = result.getInt("ID");
        Integer distance = result.getInt("DISTANCE");
        EventStyle style = EventStyle.fromString(result.getString("STYLE"));
        return new Event(id, distance, style);
    }
}