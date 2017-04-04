package configuration;

import domain.Event;
import domain.EventStyle;
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


public class EventConfiguration implements ConfigurationProtocol<Event, Integer> {

    @Override
    public String InsertCommand(Event element) {
        return String.format("INSERT INTO Event (DISTANCE, STYLE) VALUES (%d, '%s');",
                element.getDistance(),
                element.getStyle().toString());
    }

    @Override
    public String UpdateCommand(Event element, Event with) {
        return String.format("UPDATE Event SET DISTANCE = %d, STYLE = '%s' WHERE ID= %d;",
                with.getDistance(),
                with.getStyle().toString(),
                element.getID());
    }

    @Override
    public String DeleteCommand(Event element) {
        return String.format("DELETE FROM Event WHERE ID = %d;", element.getID());
    }

    @Override
    public String SelectCommand() {
        return "SELECT * FROM Event";
    }

    @Override
    public String FindByIDCommand(Integer id) {
        return String.format("SELECT * FROM Event WHERE ID = %d", id);
    }

    @Override
    public Event convert(ResultSet result) throws SQLException {
        Integer id = result.getInt("ID");
        Integer distance = result.getInt("DISTANCE");
        EventStyle style = EventStyle.fromString(result.getString("STYLE"));
        return new Event(id, distance, style);
    }

}