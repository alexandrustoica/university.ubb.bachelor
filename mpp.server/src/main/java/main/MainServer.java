package main;

import server.ServerProtocol;
import server.ServerConnectionManager;
import java.io.IOException;
import java.util.Properties;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MainServer {

    private static final Integer port = 55555;
    private static final String propertiesURL = "/server.properties";

    public static void main(String[] args) {
        Properties properties = getProperties(propertiesURL);
        Integer serverPort = getPort(properties);
        ServerProtocol server = new ServerConnectionManager(serverPort);
        server.start();
    }

    /**
     * Effect: Returns the port from the application's properties
     */
    private static Integer getPort(Properties properties) {
        Integer serverPort = port;
        try {
            serverPort = Integer.parseInt(properties.getProperty("server.port"));
        } catch (NumberFormatException error) {
            handleErrors(error);
        }
        return serverPort;
    }

    /**
     * Effect: Returns the list of properties from external file.
     * @param propertiesURL: the file's URL
     * @return the list of properties
     */
    private static Properties getProperties(String propertiesURL) {
        Properties properties = new Properties();
        try {
            properties.load(MainServer.class.getResourceAsStream(propertiesURL));
        } catch (IOException error) {
            handleErrors(error);
        }
        return properties;
    }

    /**
     * Effect: Handles exceptions in our client class.
     */
    private static void handleErrors(Exception error) {
        System.out.println(error.getMessage());
    }


}
