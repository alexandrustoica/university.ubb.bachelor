package context;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UrlContext {

    private String name;
    private String url;

    public UrlContext() {}

    public UrlContext(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}

