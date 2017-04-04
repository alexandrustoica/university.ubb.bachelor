package database;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ColumnType {

    ID,
    INT,
    STRING;

    /**
     * Converts the column's type into sql data type.
     * @return varchar(140) by default.
     */
    @Override
    public String toString() {
        switch (this) {
            case ID: return "INTEGER PRIMARY KEY AUTOINCREMENT";
            case INT: return "INTEGER";
            case STRING: return "VARCHAR(140)";
            default: return "VARCHAR(140)";
        }
    }
}