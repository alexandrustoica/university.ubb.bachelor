package connection;

import response.ResponseProtocol;
import java.io.IOException;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface ResponseSenderProtocol {

    void sendResponse(ResponseProtocol response) throws IOException;

}
