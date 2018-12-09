package database;

import error.Errors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ValidatorDatabase {

    private String database;

    public ValidatorDatabase(String fileName) throws Errors {
        this.database = fileName;
        checkPlayerTable();
        checkEventTable();
        checkUserTable();
        checkPlayerEventTable();
    }

    private void checkPlayerTable() throws Errors {
        DatabaseTable PlayerTable = new DatabaseTable("Player");
        PlayerTable.addColumn("ID", ColumnType.ID);
        PlayerTable.addColumn("NAME", ColumnType.STRING);
        PlayerTable.addColumn("AGE", ColumnType.INT);
        DatabaseTableManager manager = new DatabaseTableManager(database, PlayerTable);
        manager.makeTable();
    }

    private void checkEventTable() throws Errors {
        DatabaseTable EventTable = new DatabaseTable("Event");
        EventTable.addColumn("ID", ColumnType.ID);
        EventTable.addColumn("DISTANCE", ColumnType.INT);
        EventTable.addColumn("STYLE", ColumnType.STRING);
        DatabaseTableManager manager = new DatabaseTableManager(database, EventTable);
        manager.makeTable();
    }

    private void checkUserTable() throws Errors {
        DatabaseTable UserTable = new DatabaseTable("User");
        UserTable.addColumn("ID", ColumnType.ID);
        UserTable.addColumn("NAME", ColumnType.STRING);
        UserTable.addColumn("PASSWORD", ColumnType.STRING);
        DatabaseTableManager manager = new DatabaseTableManager(database, UserTable);
        manager.makeTable();
    }

    private void checkPlayerEventTable() throws Errors {
        DatabaseTable PlayerEventTable = new DatabaseTable("PlayerEvent");
        PlayerEventTable.addColumn("ID_PLAYER", ColumnType.INT);
        PlayerEventTable.addColumn("ID_EVENT", ColumnType.INT);
        DatabaseTableManager manager = new DatabaseTableManager(database, PlayerEventTable);
        manager.makeTable();
    }

}