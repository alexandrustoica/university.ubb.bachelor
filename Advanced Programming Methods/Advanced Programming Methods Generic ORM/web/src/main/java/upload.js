


function TestUpload() {
    let url = "https://cdn.dribbble.com/users/428659/screenshots/3599133/drbbblebanner.jpg";
    let manager = new FileManager();
    manager.upload(url, "drbbblebanner.jpg", (result) => console.log(result));
}