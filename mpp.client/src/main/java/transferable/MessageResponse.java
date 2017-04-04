package transferable;

import java.io.Serializable;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MessageResponse implements ResponseProtocol {

    private final ResponseType type = ResponseType.MESSAGE;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    @Override
    public ResponseType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
