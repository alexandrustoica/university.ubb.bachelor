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

function TableView() {
    this.selected = document.createElement("td");
    this.selectedId = () => this.selected.parentNode
        .getElementsByClassName("id")[0].innerHTML;

    this.table = (data) => buildTable(data, (cell) => this.updateSelected(cell));
    this.view = (data) => document.getElementById("body")
        .replaceChild(this.table(data), document.getElementById("table"));

    this.updateSelected = (newValue) => {
        // TODO: Improve
        this.selected.style.background = "white";
        this.selected = newValue;
        this.selected.style.background = "red";
    };
}

let tableView = new TableView();

const view = (model) => model.all((data) => tableView.view(data));
const addAndView = (item, model) => model.insert(item, (inserted) => view(model));
const updateAndView = (element, updated, model) => model.update(element, updated, (data) => view(model));

window.onload = function () {
    let board = new GenericModel("board", handler);
    view(board);
    let container = buildBiForm(["id", "url", "text"],
        (item) => addAndView(item, board),
        (item) => board.get(tableView.selectedId(), (selected) => updateAndView(selected, item, board)));
    document.getElementById("body").appendChild(container);
};