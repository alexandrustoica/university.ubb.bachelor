
function test() {
    
    let model = new GenericModel("user", handler);
    let user = new User(0, "test", "password");
    let test = new User(0, "update", "update");

    model.get(5, (user) => console.log(user.username));
    model.insert(user, (user) => model.remove(user, (deleted) => console.log(deleted)));
    model.all((list) => console.log(list));
    model.get(5, (user) => model.update(user, test, (item) => console.log(item)));

    let many = new ManyToManyModel("project-task", handler);
    many.every((data) => console.log(data));
    many.all((data) => console.log(data));
    many.receive(1, (element) => console.log(element));

    let board = new GenericModel("board", handler);
    board.insert(new Board(1, "test", "test"), (element) => console.log(element));
    board.all((data) => console.log(data));
}