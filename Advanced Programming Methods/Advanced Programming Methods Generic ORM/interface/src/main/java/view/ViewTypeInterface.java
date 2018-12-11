package view;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ViewTypeInterface {

    String getFXMLFile();

    default String getTitle() {
        return ViewType.getDataFromBundle("application.title");
    }
}
