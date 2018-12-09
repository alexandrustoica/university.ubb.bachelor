package view;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ViewType implements ViewTypeInterface {

    LOGIN {

        /** Application's Root Frame */

        @Override
        public String getTitle() {
            return "Evently Login";
        }

        @Override
        public String getFXMLFile() {
            return "/fxml/Login.fxml";
        }

    },

    SIGNUP {

        @Override
        public String getTitle() {
            return "Evently Sign Up";
        }

        @Override
        public String getFXMLFile() {
            return "/fxml/SignUp.fxml";
        }

    },

    HOME {

        @Override
        public String getTitle() {
            return "Evently";
        }

        @Override
        public String getFXMLFile() {
            return "/fxml/Application.fxml";
        }

    }

}