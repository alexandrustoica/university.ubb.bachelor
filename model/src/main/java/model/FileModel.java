package model;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class FileModel {

    private static final String PATH = new File("src/main/resources").getAbsolutePath();

    private static Logger logger;
    private final File rootDirectory;

    public FileModel() {
        rootDirectory = new File(PATH);
        logger = Logger.getLogger(FileModel.class);
    }

    @SuppressWarnings("WeakerAccess")
    public File getFileBasedOn(String name) {
        return new File(rootDirectory.getAbsolutePath() + "/" + name);
    }

    public File getDirectoryBasedOn(String name) {
        return getFileBasedOn(name);
    }

    public List<File> getFilesFrom(final File directory) {
        List<File> result = new ArrayList<>();
        Files.fileTreeTraverser().preOrderTraversal(directory).forEach(result::add);
        result.remove(0);
        return result;
    }

    public List<File> getFilesWithExtension(final List<File> files, final String extension) {
        return files.stream().filter(file -> file.getName().matches("(.*)" + extension)).collect(Collectors.toList());
    }

    public List<String> getLinesFromFile(final File file) {
        try {
            return Files.readLines(file, StandardCharsets.US_ASCII);
        } catch (IOException exception) {
            logger.error(exception);
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unused")
    public Optional<File> moveFile(File file, File directory) {
        try {
            Files.move(file, directory);
            return Optional.of(getFileBasedOn(file.getName()));
        } catch (IOException exception) {
            logger.error(exception);
            return Optional.empty();
        }
    }

    public Optional<File> createFile(final String name) {
        try {
            Boolean result = new File(rootDirectory.getAbsolutePath() +"/" + name).createNewFile();
            return (result.equals(Boolean.TRUE)) ? Optional.of(getFileBasedOn(name)) : Optional.empty();
        } catch (IOException exception) {
            logger.error(exception);
            return Optional.empty();
        }
    }

    public Optional<File> createDirectory(final String name) {
        Boolean result = new File(rootDirectory.getAbsolutePath() + "/" + name).mkdir();
        return (result.equals(Boolean.TRUE)) ? Optional.of(getDirectoryBasedOn(name)) : Optional.empty();
    }

    public Optional<File> deleteFile(final String name) {
        try {
            java.nio.file.Files.deleteIfExists(Paths.get(rootDirectory.getAbsolutePath() +  "/" + name));
            return Optional.of(getFileBasedOn(name));
        } catch (IOException exception) {
            logger.error(exception);
            return Optional.empty();
        }
    }

    public Optional<File> deleteDirectory(final String name) {
        try {
            FileUtils.deleteDirectory(new File(rootDirectory.getAbsolutePath()+ "/" + name));
            return Optional.of(getFileBasedOn(name));
        } catch (IOException exception) {
            logger.error(exception);
            return Optional.empty();
        }
    }

    public File writeToFile(final File file, final List<String> lines) {
        try {
            FileUtils.writeLines(file, lines);
        } catch (IOException exception) {
            logger.error(exception);
        }
        return file;
    }
}
