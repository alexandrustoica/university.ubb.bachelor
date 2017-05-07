package error;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Errors extends Throwable implements Iterable<Error> {

    private ArrayList<Error> errors;

    /** Empty collection of errors. */
    public Errors() {
        errors = new ArrayList<>();
    }

    /** @param error: The initial error that occurs in system. */
    public Errors(Error error) {
        errors = new ArrayList<>();
        errors.add(error);
    }
    
    /** @return : All the errors from the system. */
    public ArrayList<Error> getErrors() {
        return errors;
    }

    /** Adds an 'Error' object to internal collection. */
    public void add(Error error) {
        errors.add(error);
    }


    /** Adds a list of 'Error' objects to internal collection. */
    public void add(Errors containsOf) {
        containsOf.getErrors().forEach(error -> errors.add(error));
    }


    /** Checks if collection is empty. */
    public Boolean empty() {
        return errors.isEmpty();
    }

    /** Returns all the error messages into a single string */
    public String getMessage() {
        String result = "";
        for (Error error : errors) {
            result += error.getMessage();
        }
        return result;
    }

    @Override
    public Iterator<Error> iterator() {
        return errors.iterator();
    }

}