
const createInput = (value) => {
    let input = document.createElement("input");
    input.placeholder = value;
    input.id = value;
    return input;
};

const getDataFrom = (form, list) => {
    let inputs = form.getElementsByTagName("input");
    let data = {};
    list.forEach(item => data[item] = inputs[list.indexOf(item)].value);
    return data;
};

const setButtonFunction = (button, func, form, list) => {
    button.onclick = () => func(getDataFrom(form, list));
    return button;
};

const createButton = (value) => {
    let button = document.createElement("button");
    button.innerHTML = value;
    button.id = value.toLowerCase();
    return button;
};

const buildButton = (text, func, form, list) =>
    setButtonFunction(createButton(text), func, form, list);

const buildForm = (list, func) => {
    let form = document.createElement("div");
    form.id = "form-input";
    list.forEach(item => form.appendChild(createInput(item)));
    form.appendChild(buildButton("Submit", func, form, list));
    return form;
};

const buildBiForm = (list, func, alternative) => {
    let form = buildForm(list, func);
    form.appendChild(buildButton("Update", alternative, form, list));
    return form;
};


