package server;

import java.net.Socket;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ConcurrentServerProtocol extends ServerProtocol {

    Thread createThread(Socket socket);

}
