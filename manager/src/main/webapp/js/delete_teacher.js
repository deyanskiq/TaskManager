function delete_teacher() {
    var username = $("#delete_teacher_username")[0].value;

    if (username == "") {
        alert("All fields should be specified!");
        return;
    }

    var data = {
        username : username
    }

    console.log(data);

}
