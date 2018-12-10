package controller;

import com.google.common.io.Files;
import context.DirectoryContext;
import context.FileContext;
import context.UrlContext;
import model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private FileModel model;

    private FileContext convertFile(final File file) {
        return new FileContext(file.getName(),
                file.getAbsolutePath(), model.getLinesFromFile(file));
    }

    private DirectoryContext convertDirectory(final File directory) {
        return new DirectoryContext(directory.getName(), directory.getAbsolutePath(),
                model.getFilesFrom(directory).stream().map(this::convertFile).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public ResponseEntity<FileContext> getFileBasedOn(@PathVariable("name") String name) {
        File file = model.getFileBasedOn(name).orElseThrow(FileSystemNotFoundException::new);
        return new ResponseEntity<>(this.convertFile(file), HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<FileContext>> getFilesBasedOn(@PathVariable("name") String name) {
        return new ResponseEntity<>(model.getFilesBasedOn(name).stream()
                .map(this::convertFile)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/directory/{name}", method = RequestMethod.GET)
    public ResponseEntity<DirectoryContext> getDirectoryBasedOn(@PathVariable("name") String name) {
        File directory = model.getDirectoryBasedOn(name).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(convertDirectory(directory), HttpStatus.OK);
    }

    @RequestMapping(value = "/extension/{extension}", method = RequestMethod.GET)
    public ResponseEntity<List<FileContext>> getFilesWithExtension(@PathVariable("extension") String extension) {
        List<File> files = model.getFilesFrom(model.getRoot());
        List<File> result = model.getFilesWithExtension(files, extension);
        return new ResponseEntity<>(result.stream().map(this::convertFile).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/create/file/{name}/{extension}", method = RequestMethod.GET)
    public ResponseEntity<FileContext> createFile(@PathVariable("name") String name,
                                                  @PathVariable("extension") String extension) {
        File file = model.createFile(name + "." + extension).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(convertFile(file), HttpStatus.OK);
    }

    @RequestMapping(value = "/create/directory/{name}", method = RequestMethod.GET)
    public ResponseEntity<DirectoryContext> createDirectory(@PathVariable("name") String name) {
        File directory = model.createDirectory(name).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(convertDirectory(directory), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/directory/{name}", method = RequestMethod.GET)
    public ResponseEntity<DirectoryContext> deleteDirectory(@PathVariable("name") String name) {
        File directory = model.deleteDirectory(name).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(convertDirectory(directory), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/file/{name}", method = RequestMethod.GET)
    public ResponseEntity<FileContext> deleteFile(@PathVariable("name") String name) {
        File file = model.deleteFile(name).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(convertFile(file), HttpStatus.OK);
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ResponseEntity<FileContext> writeFile(@RequestBody FileContext context) {
        File file = model.writeToFile(model.getFileBasedOn(context.getName())
                .orElseThrow(RuntimeException::new), context.getLines());
        return new ResponseEntity<>(convertFile(file), HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<FileContext> upload(@RequestBody UrlContext context) throws IOException {
        URL dataUrl = new URL(context.getUrl());
        URLConnection connection = dataUrl.openConnection();
        InputStream in = new BufferedInputStream(connection.getInputStream());
        byte[] data = new byte[connection.getContentLength()];
        Integer offset = 0;
        while (offset < connection.getContentLength()) {
            Integer bytesRead = in.read(data, offset, data.length - offset);
            if (bytesRead == -1)
                break;
            offset += bytesRead;
        }
        in.close();
        if (!offset.equals(connection.getContentLength())) {
            throw new IOException("Only read " + offset + " bytes; Expected " + connection.getContentLength() + " bytes");
        }
        File file = model.createFile(context.getName()).orElseThrow(RuntimeException::new);
        Files.write(data, file);
        return new ResponseEntity<>(convertFile(file), HttpStatus.OK);
    }
}
