package model;

import database.ValidatorDatabase;
import error.Errors;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class ModelDatabase {

    public final static String database = "database.db";
    String preferredDatabaseSource;

    void handleErrors(Errors errors) {
        System.out.println(errors);
    }

    void checkDatabase() {
        try {
            if (preferredDatabaseSource == null) {
                ValidatorDatabase validatorDatabase = new ValidatorDatabase(database);
            } else {
                ValidatorDatabase validatorDatabase = new ValidatorDatabase(preferredDatabaseSource);
            }
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

}