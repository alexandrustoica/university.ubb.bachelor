package exception;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConnectionException extends Throwable {

    private String message;

    public ConnectionException(String errorMessage) {
        message = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
