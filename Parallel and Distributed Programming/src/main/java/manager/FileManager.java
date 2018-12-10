package manager;

import io.Reader;
import io.Writer;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class FileManager implements Reader<String>, Writer<String> {

    private String fileName;
    private static Logger logger;

    /**
     * Helps the FileManager to performOn operations and log their errors.
     */
    private PerformerManager performer;

    /**
     * Manages a file in the system.
     *
     * @param fileName - the file's name.
     */
    public FileManager(String fileName) {
        logger = Logger.getLogger(FileManager.class);
        this.fileName = fileName;
        this.performer = new PerformerManager
                (exception -> logger.error(exception.getMessage()));
    }

    /**
     * Reads the data from a file.
     *
     * @return - the data as String
     * @apiNote - logs the errors, if exceptions occur
     */
    @Override
    public String read() {
        return performer.perform(FileReader::new, fileName)
                .map(reader -> read(new BufferedReader(reader)))
                .orElse(System.lineSeparator());
    }

    /**
     * Writes the data to a file.
     *
     * @param data - the data [type String]
     * @apiNote - logs the errors
     */
    @Override
    public void write(String data) {
        performer.perform(FileWriter::new, fileName)
                .ifPresent(writer -> write(new BufferedWriter(writer), data));
    }

    /**
     * Writes the data to a file using a BufferedWriter
     *
     * @param writer - the BufferedWriter
     * @param data   - the data [type String]
     */
    private void write(BufferedWriter writer, String data) {
        performer.consume(writer::write, data);
        performer.consume(BufferedWriter::close, writer);
    }

    /**
     * Reads the data from a file using a BufferedReader.
     *
     * @param reader - the BufferedReader
     * @return - the data as String
     */
    private String read(BufferedReader reader) {
        return reader.lines().reduce("", (acc, item) -> acc += item + System.lineSeparator());
    }
}
