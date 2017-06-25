package controller;

import context.DirectoryContext;
import context.FileContext;
import model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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




}
