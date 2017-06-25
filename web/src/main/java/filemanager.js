function File(name, lines, url) {
    this.name = name;
    this.lines = lines;
    this.url = url;
}

function FileManager(handler) {

    this.url = "http://localhost:8080/file/";
    this.handler = handler;

    this.actions = {
        get: "get/",
        write: "write/",
        all: "all/",
        file: "file/",
        create: "create/",
        delete: "delete/",
        directory: "directory/",
        extension: "extension/",
        upload: "upload",
    };

    this.get = (fileName, func) => get(this.url, null, func, this.handler, this.actions.get + fileName);

    this.all = (fileName, func) => get(this.url, null, func, this.handler, this.actions.all + fileName);

    this.directory = (name, func) => get(this.url, null, func, this.handler, this.actions.directory + name);

    this.getWithExtension = (extension, func) =>
        get(this.url, null, func, this.handler, this.actions.extension + extension);

    this.createFile = (fileName, extension, func) => get(this.url, null, func, this.handler,
        this.actions.create + this.actions.file + fileName + "/" + extension);

    this.deleteFile = (fileName, func) => get(this.url, null, func, this.handler,
        this.actions.delete + this.actions.file + fileName);

    this.createDirectory = (name, func) => get(this.url, null, func, this.handler,
        this.actions.create + this.actions.directory + name);

    this.deleteDirectory = (name, func) => get(this.url, null, func, this.handler,
        this.actions.delete + this.actions.directory + name);

    this.write = (file, func) => post(this.url, file, func, this.handler, this.actions.write);

    this.removeExtension = (name) => name.substr(0, name.lastIndexOf('.'));

    this.upload = (url, name, func) => post(this.url, {"name": name , "url": url}, func,
        this.handler, this.actions.upload);
}

function TestFileManger() {

    let manager = new FileManager();
    manager.get("test", (file) => console.log(file));
    manager.all("test", (files) => console.log(files));
    manager.directory("test", (directory) => console.log(directory));
    manager.getWithExtension("txt", (files) => console.log(files));

    manager.createFile("testing", "txt", (file) =>
        manager.deleteFile(manager.removeExtension(file.name), (result) => console.log(result)));

    let file = new File("test.txt", ["works", "next_line"], null);
    manager.write(file, (file) => console.log(file));

    manager.createDirectory("testable", (directory) =>
        manager.deleteDirectory(directory.name, (result) => console.log(result)));
}