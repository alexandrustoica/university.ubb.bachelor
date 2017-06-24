
const replaceDataInTable = (data) => document.getElementById("body")
        .replaceChild(buildTable(data), document.getElementById("table"));

const createTable = () => {
    this.table = document.createElement("table");
    this.table.id = "table";
    return this.table;
};

const loadCell = (row, value) => {
    let cell = row.insertCell(row.length);
    cell.innerHTML = value;
    return cell;
};

const loadRow = (table, item) => {
    let row = table.insertRow();
    for (let prop in item) {
        loadCell(row, item[prop]);
    }
    return table;
};

const loadTable = (emptyTable, list) => {
    list.forEach(item => loadRow(emptyTable, item));
    return emptyTable;
};

const buildTable = (list) => {
    return loadTable(createTable(), list);
};