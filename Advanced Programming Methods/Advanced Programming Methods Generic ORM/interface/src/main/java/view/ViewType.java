package view;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ViewType implements ViewTypeInterface {

    LOGIN {
        @Override public String getFXMLFile() {
            return "/fxml/LoginView.fxml";
        }
    },

    SIGN_UP {
        @Override public String getFXMLFile() {
            return "/fxml/SignUpView.fxml";
        }
    },

    GENERIC_VIEW {
        @Override public String getFXMLFile() {
            return "/fxml/GenericView.fxml";
        }
    };

    @SuppressWarnings("all")
    protected static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("application").getString(key);
    }
}