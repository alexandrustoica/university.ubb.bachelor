package request_handler;

import java.util.ResourceBundle;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

class RequestHandlerDatabase {

    private static final String dataBundle = "server";

    String getDatabaseURL() {
        return RequestHandlerDatabase.getDataFromBundle("server.database");
    }

    private static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle(dataBundle).getString(key);
    }

}
