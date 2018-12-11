package context;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class FileContext {

    private String name;
    private String url;
    private List<String> lines;

    public FileContext() { }

    public FileContext(final String name, final String url, final List<String> lines) {
        this.name = name;
        this.url = url;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getLines() {
        return lines;
    }
}
