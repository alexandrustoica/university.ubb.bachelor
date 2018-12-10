package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class FileModelTest {

    private FileModel model;

    @Before
    public void setUp() throws Exception {
        model = new FileModel(new File("src/main/resources"));
    }

    @Test
    public void isGettingFilesFromDirectory() throws Exception {
        File directory = model.getDirectoryBasedOn("database_configurations").orElseThrow(Exception::new);
        List<File> files = model.getFilesFrom(directory);
        assertEquals(files.get(0).getName(), "default_database.properties");
    }

    @Test
    public void isGettingLinesFromFile() throws Exception {
        Optional<File> file = model.getFileBasedOn("default_database.properties");
        assertTrue(file.isPresent());
        List<String> lines = model.getLinesFromFile(file.get());
        assertEquals(lines.get(0), "hibernate.connection.driver_class = com.mysql.jdbc.Driver");
    }

    @Test
    public void isGettingFilesWithExtension() throws Exception {
        File directory = model.getDirectoryBasedOn("database_configurations").orElseThrow(Exception::new);
        List<File> files = model.getFilesFrom(directory);
        List<File> props = model.getFilesWithExtension(files, "properties");
        List<File> audio = model.getFilesWithExtension(files, "mp3");
        assertEquals(props.size(), 2);
        assertEquals(audio.size(), 0);
    }

    @Test
    public void isCreatingFile() throws Exception {
        Optional<File> file = model.createFile("test.txt");
        assertTrue(file.isPresent());
        file.ifPresent(data -> assertEquals(data.getName(), "test.txt"));
        file.ifPresent(data -> model.deleteFile(data.getName()));
    }

    @Test
    public void isCreatingDirectory() throws Exception {
        Optional<File> directory = model.createDirectory("test");
        assertTrue(directory.isPresent());
        directory.ifPresent(data -> assertEquals(data.getName(), "test"));
        directory.ifPresent(data -> model.deleteDirectory(data.getName()));
    }

    @Test
    public void isDeletingFile() throws Exception {
        Optional<File> file = model.createFile("test.txt");
        System.out.print(file);
        file.ifPresent(data -> {
               Optional<File> deleted = model.deleteFile(data.getName());
        });
    }

    @Test
    public void isDeletingDirectory() throws Exception {
        Optional<File> directory = model.createDirectory("test");
        directory.ifPresent(data -> {
            Optional<File> deleted = model.deleteDirectory(data.getName());
            assertTrue(deleted.isPresent());
        });
    }

    @Test
    public void isWritingFile() throws Exception {
        Optional<File> file = model.createFile("test.txt");
        List<String> data = asList("test", "test");
        file.ifPresent(item -> model.writeToFile(item, data));
        file.ifPresent(item -> assertEquals(model.getLinesFromFile(item), data));
        file.ifPresent(item -> model.deleteFile(item.getName()));
    }
}