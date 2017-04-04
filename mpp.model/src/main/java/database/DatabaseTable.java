package database;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DatabaseTable {

    private String name;
    private HashMap<String, ColumnType> columns;
    private ArrayList<String> references;

    public DatabaseTable(String name) {
        this.name = name;
        this.columns = new HashMap<>();
        this.references = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addColumn(String name, ColumnType type) {
        columns.put(name, type);
    }

    public void addReference(String reference) {
        references.add(reference);
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public HashMap<String, ColumnType> getColumns() {
        return columns;
    }

}