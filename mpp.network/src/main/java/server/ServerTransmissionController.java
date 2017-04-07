package server;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ServerTransmissionController implements ServerTransmissionProtocol {

    private ServerConnectionManager connectionManager;

    public ServerTransmissionController(ServerConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

}
