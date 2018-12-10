package error;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Error extends Throwable {

    private String message;

    /** @param message: The error's message. */
    public Error(String message) {
        this.message = message;
    }

    /** @return : The error's string message. */
    public String getMessage() {
        return message;
    }

}
