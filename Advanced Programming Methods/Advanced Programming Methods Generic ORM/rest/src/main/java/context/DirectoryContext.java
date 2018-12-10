package context;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DirectoryContext {

    private String name;
    private String url;
    private List<FileContext> files;

    public DirectoryContext() { }

    public DirectoryContext(final String name, final String url, final List<FileContext> files) {
        this.name = name;
        this.url = url;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<FileContext> getFiles() {
        return files;
    }
}
