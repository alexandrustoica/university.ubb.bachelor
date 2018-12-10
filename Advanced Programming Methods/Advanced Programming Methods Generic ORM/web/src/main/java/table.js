

const createTable = () => {
    this.table = document.createElement("table");
    this.table.id = "table";
    return this.table;
};

const loadCell = (row, value, type) => {
    let cell = row.insertCell(row.length);
    if (typeof value === "function") {
        value(cell);
    } else {
        cell.innerHTML = value;
    }
    cell.className = type;
    return cell;
};

const loadRow = (table, item) => {
    let row = table.insertRow();
    for (let property in item) {
        loadCell(row, item[property], property);
    }
    return table;
};

const loadTable = (emptyTable, list) => {
    list.forEach(item => loadRow(emptyTable, item));
    return emptyTable;
};

const buildTable = (list, onCellClick) => {
    let table = loadTable(createTable(), list);
    table.onclick = (event) => onCellClick(event.srcElement);
    return table;
};