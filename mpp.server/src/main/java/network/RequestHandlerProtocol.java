package network;

import transferable.RequestProtocol;
import transferable.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RequestHandlerProtocol {

    ResponseProtocol handleRequest(RequestProtocol request);

}
