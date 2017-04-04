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

public interface ResponseProtocol extends Serializable {
    ResponseType getType();
}
