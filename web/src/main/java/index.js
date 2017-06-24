const action = {
    insert: "insert/",
    update: "update/",
    delete: "delete/",
    get: "get/",
    all: "all",

    add: "add/",
    refresh: "refresh/",
    remove: "remove/",
    receive: "receive/",
    every: "every",
};

function send(url, data, success, fail, key, type,) {
    $.ajax({
        type: type,
        url: url + key,
        data: JSON.stringify(data),
        success: success,
        error: fail,
        timeout: 600000,
        contentType: "application/json",
        dataType: 'json',
    })
}

const handler = (error) => console.error(error);
const printer = (object) => console.log(object);

const get = (url, data, success, fail, key) =>
    send(url, data, success, fail, key, "GET");

const post = (url, data, success, fail, key) =>
    send(url, data, success, fail, key, "POST");

const insert = (url, success, fail, data) =>
    post(url, data, success, fail, action.insert);

const update = (url, success, fail, element, updated) =>
    post(url, {"element": element, "with": updated}, success, fail, action.update);

const remove = (url, success, fail, id) =>
    get(url, null, success, fail, action.delete + id);

const getElementById = (url, success, fail, id) =>
    get(url, null, success, fail, action.get + id);

const all = (url, success, fail) =>
    get(url, null, success, fail, action.all);

const add = (url, success, fail, data) =>
    post(url, data, success, fail, action.add);

const refresh = (url, success, fail, element, updated) =>
    post(url, {"element": element, "with": updated}, success, fail, action.refresh);

const eliminate = (url, success, fail, id) =>
    get(url, null, success, fail, action.delete + id);

const receiveElementById = (url, success, fail, id) =>
    get(url, null, success, fail, action.receive + id);

const every = (url, success, fail) =>
    get(url, null, success, fail, action.every);


function GenericModel(key, handler) {
    this.url = "http://localhost:8080/" + key + "/";
    this.handler = handler;
    this.insert = (element, func) => insert(this.url, func, this.handler, element);
    this.update = (element, updated, func) => update(this.url, func, this.handler, element, updated);
    this.remove = (id, func) => remove(this.url, func, this.handler, id);
    this.remove = (element, func) => remove(this.url, func, this.handler, element.id);
    this.get = (id, func) => getElementById(this.url, func, this.handler, id);
    this.all = (func) => all(this.url, func, this.handler);
}

function ManyToManyModel(key, handler) {
    this.url = "http://localhost:8080/" + key + "/";
    this.handler = handler;
    this.insert = (element, func) => insert(this.url, func, this.handler, element);
    this.update = (element, updated, func) => update(this.url, func, this.handler, element, updated);
    this.remove = (id, func) => remove(this.url, func, this.handler, id);
    this.remove = (element, func) => remove(this.url, func, this.handler, element.id);
    this.get = (id, func) => getElementById(this.url, func, this.handler, id);
    this.all = (func) => all(this.url, func, this.handler);
    this.add = (element, func) => add(this.url, func, this.handler, element);
    this.refresh = (element, updated, func) => refresh(this.url, func, this.handler, element, updated);
    this.delete = (id, func) => eliminate(this.url, func, this.handler, id);
    this.delete = (element, func) => eliminate(this.url, func, this.handler, element.id);
    this.receive = (id, func) => receiveElementById(this.url, func, this.handler, id);
    this.every = (func) => every(this.url, func, this.handler);
}

function User(id, username, password) {
    this.id = id;
    this.username = username;
    this.password = password;
}

function Board(id, url, text) {
    this.id = id;
    this.url = url;
    this.text = text;
}

window.onload = function () {
    let model = new GenericModel("user", handler);
    let user = new User(0, "test", "password");
    let test = new User(0, "update", "update");

    // get an element by id where id == 5
    model.get(5, (user) => console.log(user.username));

    // insert an element and then delete it from model.
    model.insert(user, (user) => model.remove(user, (deleted) => console.log(deleted)));

    // get all data from model
    model.all((list) => console.log(list));

    // get the element with id == 5 and update it
    model.get(5, (user) => model.update(user, test, (item) => console.log(item)));

    let many = new ManyToManyModel("project-task", handler);

    // get all the data from the right side of the relationship
    many.every((data) => console.log(data));

    // get all the data from the left side of the relationship
    many.all((data) => console.log(data));

    // get the data with id == 2 from the right side of the relationship
    many.receive(1, (element) => console.log(element));

    let board = new GenericModel("board", handler);

    board.insert(new Board(1, "test", "test"), (element) => console.log(element));
    board.all((data) => console.log(data));
};